package com.loan.uts.controller;

import com.loan.uts.exception.AttachFailException;
import com.loan.uts.model.Application;
import com.loan.uts.model.Draft;
import com.loan.uts.model.Manager;
import com.loan.uts.model.Student;
import com.loan.uts.service.ManagerService;
import com.loan.uts.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.Multipart;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

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

    @Autowired
    StudentService studentService;

    @Autowired
    ManagerService managerService;

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
        return "studentApplications";
    }

    /**
     * Go to the homepage of the student.
     * @return
     */
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String student() {
        return "student";
    }

    /**
     * Go to the homepage of the adding new applications.
     * @return
     */
    @RequestMapping(value = {"/applications/add"}, method = RequestMethod.GET)
    public String newApplication(@RequestParam(name = "draftId", required = false) Integer draftId, ModelMap modelMap) {
        logger.info("Request for creating new application.");
        if(draftId != null) {
            modelMap.addAttribute(DRAFT, studentService.getDraft(draftId));
            logger.info("Load draft: " + draftId);
        }
        return "newApplication";
    }

    /**
     * Submit the application and go to the homepage of the student.
     * @return
     */
    @RequestMapping(value = {"/applications/add"}, method = RequestMethod.POST)
    public String submitApplication(@RequestParam("title") String title, @RequestParam("content") String content,
                                    @RequestParam("draft_id") Integer draftId,
//                                    @RequestParam(name = "attachments", required = false) MultipartFile[] attachments,
                                    HttpSession session, ModelMap modelMap) {
        Student student = (Student)session.getAttribute(STUDENT);
//        String uploadPath = session.getServletContext().getRealPath("/upload/");

        Application application = new Application(title, content, new Date(), SUBMITTED, student);
        Application savedApp = null;
        savedApp = studentService.submitApplication(application, null, null, draftId);
//        try {
//            savedApp = studentService.submitApplication(application, attachments, uploadPath);
//        } catch (AttachFailException e) {
//            e.printStackTrace();
//        }
        managerService.AssignApplication(savedApp);
        logger.info("Application submitted.");
        if(draftId != null) {
            student.setDraft(null);
            session.setAttribute(STUDENT, student);
        }
        modelMap.addAttribute("filetype", "application");
        return "success";
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
        return "history";
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
        modelMap.addAttribute("applications", applications);
        return "history";
    }

    /**
     * Save the draft for student.
     * @param title
     * @param content
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/student/draft/save"}, method = RequestMethod.POST)
    public String saveDraft(@RequestParam("title") String title, @RequestParam("draft_id") Integer draftId,
                            @RequestParam("content") String content, HttpSession session, ModelMap modelMap){
        Student student = (Student)session.getAttribute(STUDENT);
        Draft draft = new Draft(title, content, student);
        if (draftId != null) draft.setId(draftId);
        draft = studentService.saveDraft(student, draft);
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
    @RequestMapping(value = {"/student/draft/delete"}, method = RequestMethod.GET)
    public String deleteDraft(HttpSession session){
        Student student = (Student) session.getAttribute(STUDENT);
        Draft draft = student.getDraft();
        studentService.deleteDraft(draft);
        student.setDraft(null);
        session.setAttribute(STUDENT, student);
        logger.info("Delete draft");
        return "redirect:/student/applications";
    }

}
