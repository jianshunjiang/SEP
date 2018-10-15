package com.loan.uts.controller;

import com.loan.uts.exception.AttachFailException;
import com.loan.uts.exception.TooManyAppException;
import com.loan.uts.model.Application;
import com.loan.uts.model.Draft;
import com.loan.uts.model.Student;
import com.loan.uts.service.AttachmentService;
import com.loan.uts.service.ManagerService;
import com.loan.uts.service.StudentService;
import com.loan.uts.util.PDFUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;

import static com.loan.uts.controller.AttachmentController.getUploadPath;
import static com.loan.uts.controller.LoginController.STUDENT;
import static com.loan.uts.model.Application.SUBMITTED;

/**
 * Handle the requests that from the student operation in the front end..
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);
    public static final String APPLICATIONS = "applications";
    public static final String DRAFT = "draft";
    public static final int DATE = 1;
    public static final int TITLE = 0;
    public static final String ATTACHMENTS = "attachments";

    @Autowired
    StudentService studentService;

    @Autowired
    ManagerService managerService;

    @Autowired
    AttachmentService attachmentService;

    /**
     * Go to the homepage of the student.
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String student() {
        return "student/home";
    }

    /**
     * Search for the student's application records within the student.
     * And go to the view application page.
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/applications"}, method = RequestMethod.GET)
    public String applications(HttpSession session, ModelMap modelMap) {
        Student student = (Student) session.getAttribute(STUDENT);
        Set<Application> applications = studentService.getUnFinishedApplication(student);
        modelMap.addAttribute(APPLICATIONS, applications);
        return "student/applications";
    }

    /**
     * Return the historical application page.
     * Prepare the historical application datas.
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String history(HttpSession session, ModelMap modelMap){
        Student student = (Student)session.getAttribute(STUDENT);
        modelMap.addAttribute("applications", studentService.getHistoricalApplication(student));
        return "student/history";
    }

    /**
     * Search for the historical applications by title or date.
     * @param session
     * @param modelMap
     * @param type
     * @param title
     * @param month
     * @return
     */
    @RequestMapping(value = {"/history"}, method = RequestMethod.POST)
    public String history(HttpSession session, ModelMap modelMap,
                          @RequestParam("type") Integer type, @RequestParam("title") String title, @RequestParam("month") String month){
        Student student = (Student)session.getAttribute(STUDENT);
        Set<Application> applications;
        if (type == DATE) {
            applications = studentService.searchByMonth(student, month);
        }
        else {
            applications = studentService.searchByTitle(student, title);
        }
        logger.info("Application history searched");
        modelMap.addAttribute(APPLICATIONS, applications);
        return "student/history";
    }

    /**
     * Go to the homepage of the adding new applications.
     * Load the draft information if the application is created from the draft.
     * @return
     */
    @RequestMapping(value = {"/applications/add"}, method = RequestMethod.GET)
    public String newApplication(@RequestParam(name = "draftId", required = false) Integer draftId,
                                 @RequestParam(name = "error", required = false) String error,
                                 ModelMap modelMap) {
        logger.info("Request for creating new application.");
        if(draftId != null) {
            Draft draft = studentService.getDraft(draftId);
            modelMap.addAttribute(DRAFT, draft);
            modelMap.addAttribute(ATTACHMENTS, attachmentService.getAttachments(draft));
            if (error != null) modelMap.addAttribute("error", error);
            logger.info("Load draft: " + draftId);
        }
        return "student/newApplication";
    }

    /**
     * Submit the application and go to the homepage of the student applications.
     * Delete the draft if the application is created from the draft.
     * @return
     */
    @RequestMapping(value = {"/applications/add"}, method = RequestMethod.POST)
    public String submitApplication(@RequestParam("title") String title, @RequestParam("content") String content,
                                    @RequestParam("draft_id") Integer draftId,
                                    @RequestParam("amount") Double amount,
                                    @RequestParam("years") Integer paybackYears,
                                    @RequestParam("sum") Double sum,
                                    @RequestParam(name = "delId", required = false) Integer[] delIDs,
                                    @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                                    HttpSession session, ModelMap modelMap) {
        Student student = (Student)session.getAttribute(STUDENT);
        Application application = new Application(title, content, new Date(), SUBMITTED, student, paybackYears, sum, amount);
        Application savedApp = null;
        try {
            savedApp = studentService.submitApplication(application, attachments, getUploadPath(session));//Create the application in the database.
            if (draftId != null){
                Draft draft = studentService.getDraft(draftId);
                attachmentService.deleteAttachments(delIDs, getUploadPath(session)); //Delete the deleted attachments.
                attachmentService.moveAttachments(draft, savedApp);//move the attachments from draft to applications.
                studentService.deleteDraft(draft);
                student.setDraft(null);
                session.setAttribute(STUDENT, student);
            }
        } catch (AttachFailException e) {
            e.printStackTrace();
        } catch (TooManyAppException e) {
            modelMap.addAttribute("error", e.getMessage());
            if (draftId != null) modelMap.addAttribute("draftId", draftId);
            return "redirect:/student/applications/add";
        }
        managerService.AssignApplication(savedApp);
        logger.info("Application submitted.");
        modelMap.addAttribute("filetype", "application");
        return "success";
    }

    /**
     * Save the draft for student.
     * @param title
     * @param content
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/draft/save"}, method = RequestMethod.POST)
    public String saveDraft(@RequestParam("title") String title, @RequestParam("draft_id") Integer draftId,
                            @RequestParam("amount") Double amount, @RequestParam("years") Integer paybackYears,
                            @RequestParam("sum") Double sum, @RequestParam("content") String content,
                            @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                            @RequestParam(name = "delId", required = false) Integer[] delIDs,
                            HttpSession session, ModelMap modelMap){
        Student student = (Student)session.getAttribute(STUDENT);
        Draft draft = new Draft(title, content, student, paybackYears, amount, sum);
        if (draftId != null) draft.setId(draftId);
        String path = getUploadPath(session);
        draft = studentService.saveDraft(student, draft, attachments, path);
        attachmentService.deleteAttachments(delIDs, path);
        student.setDraft(draft);
        session.setAttribute(STUDENT, student);
        modelMap.addAttribute("filetype", "draft");
        logger.info("Save draft.");
        return "success";
    }

    /**
     * Delete the draft for students.
     * @param session
     * @return
     */
    @RequestMapping(value = {"/draft/delete"}, method = RequestMethod.GET)
    public String deleteDraft(HttpSession session){
        Student student = (Student) session.getAttribute(STUDENT);
        Draft draft = student.getDraft();
        attachmentService.deleteAttachmentsByDraft(draft, getUploadPath(session));
        studentService.deleteDraft(draft);
        student.setDraft(null);
        session.setAttribute(STUDENT, student);
        logger.info("Delete draft");
        return "redirect:/student/applications";
    }

    /**
     * Go to the page of viewing application in detail.
     * @return
     */
    @RequestMapping(value = {"/applications/detail"}, method = RequestMethod.GET)
    public String detail(@RequestParam(name = "id") Integer id, ModelMap modelMap) {
        logger.info("Application detail");
        Application application = studentService.getApplication(id);
        modelMap.addAttribute("application", application);
        modelMap.addAttribute(ATTACHMENTS, attachmentService.getAttachments(application));
        return "student/appDetail";
    }
    /**
     * Go to page for responsing the application.
     * @return
     */
    @RequestMapping(value = {"/applications/response"}, method = RequestMethod.GET)
    public String responseApp(@RequestParam(name = "id") Integer id, ModelMap modelMap) {
        logger.info("Request for response application");
        modelMap.addAttribute("application", studentService.getApplication(id));
        return "student/responseApp";
    }
    /**
     * Handle the response storage of the application, storing new attachments.
     * @return
     */
    @RequestMapping(value = {"/applications/response"}, method = RequestMethod.POST)
    public String responseApp(@RequestParam(name = "id") Integer id, @RequestParam(name = "result") String result,
                              @RequestParam(name = "result") String content,
                              @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                              HttpSession session) {
        logger.info("Request for response application");
        String uploadPath = session.getServletContext().getRealPath("/").split("target")[0] + "upload/";
        try {
            studentService.responseApplication(id, result, content, attachments, uploadPath);
        } catch (AttachFailException e) {
            e.printStackTrace();
        }
        return "redirect:/student/applications";
    }

    /**
     * Display student account information.
     * @return
     */
    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public String getStudentProfile() {
        logger.info("Student: profile");
        return "student/profile";
    }

    /**
     * Go to the student update account page.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.GET)
    public String editStudentAccount(@RequestParam(required = false, name = "error") String error) {
        return "student/editAccount";
    }

    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.POST)
    public String saveStudentChanges(@RequestParam("id")Integer id,
                                     @RequestParam("email")String email,
                                     @RequestParam("bankaccount")String bankaccount,
                                     @RequestParam("phone")String phone,
                                     @RequestParam("faculty")String faculty,
                                     @RequestParam("course")String course,
                                     HttpSession session) {

        Student student = studentService.update(id, email, bankaccount, phone, faculty, course);
        session.setAttribute(STUDENT, student);
        return "redirect:/student/account";
    }

    /**
     * Go to the student update account page.
     * @param error
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword(@RequestParam(required = false, name = "error") String error) {
        return "student/resetPassword";
    }

    /**
     * Reset password for student account.
     * @param id
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = {"/account/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam("id")Integer id, @RequestParam("password")String password,
                                HttpSession session) {
        Student student = studentService.resetPassword(id, password);
        session.setAttribute(STUDENT, student);
        return "redirect:/student/account";
    }

    /**
     * Preparing the contract pdf for downloading.
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/applications/contract")
    public ResponseEntity<byte[]> download(@RequestParam("id") Integer id, HttpSession session){
        Student student = (Student) session.getAttribute(STUDENT);
        HttpHeaders headers = new HttpHeaders();
        String fileName = "Loan Contact" + id + " - " + student.getId() + ".pdf";

        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(
                PDFUtil.contract(studentService.getApplication(id), student),
                headers, HttpStatus.OK);
    }


}
