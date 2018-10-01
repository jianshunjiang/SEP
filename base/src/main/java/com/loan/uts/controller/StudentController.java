package com.loan.uts.controller;

import com.loan.uts.model.Application;
import com.loan.uts.model.Draft;
import com.loan.uts.model.Manager;
import com.loan.uts.model.Student;
import com.loan.uts.service.ManagerService;
import com.loan.uts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Set;

import static com.loan.uts.controller.LoginController.STUDENT;
import static com.loan.uts.model.Application.SUBMITTED;

/**
 * Handle the requests that from the student operation in the front end..
 */
@Controller
public class StudentController {
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
    @RequestMapping(value = {"/student/applications"}, method = RequestMethod.GET)
    public String applications(HttpSession session, ModelMap modelMap) {
        Student student = (Student) session.getAttribute(STUDENT);
        Set<Application> applications = studentService.getUnFinishedApplication(student);
        modelMap.addAttribute(APPLICATIONS, applications);
        return "applications";
    }

    /**
     * Go to the homepage of the student.
     * @return
     */
    @RequestMapping(value = {"/student"}, method = RequestMethod.GET)
    public String student() {
        return "student";
    }

    /**
     * Go to the homepage of the adding new applications.
     * @return
     */
    @RequestMapping(value = {"/student/applications/add"}, method = RequestMethod.GET)
    public String newApplication(@RequestParam(name = "draftId", required = false) Integer draftId, ModelMap modelMap) {
        if(draftId != null) modelMap.addAttribute(DRAFT, studentService.getDraft(draftId));
        return "newApplication";
    }

    /**
     * Submit the application and go to the homepage of the student.
     * @return
     */
    @RequestMapping(value = {"/student/applications/add"}, method = RequestMethod.POST)
    public String submitApplication(@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session, ModelMap modelMap) {
        Student student = (Student)session.getAttribute(STUDENT);
        Application application = new Application(title, content, new Date(), SUBMITTED, student);
        Application savedApp = studentService.submitApplication(application);
        managerService.AssignApplication(savedApp);
        modelMap.addAttribute("filetype", "draft");
        return "success";
    }

    /**
     * Return the historical application page.
     * Prepare the historical application datas.
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/student/history"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/student/history"}, method = RequestMethod.POST)
    public String history(HttpSession session, ModelMap modelMap,
                          @RequestParam("type") Integer type, @RequestParam("title") String title, @RequestParam("month") String month){
        Student student = (Student)session.getAttribute(STUDENT);
        Set<Application> applications;
        if (type == DATE) applications = studentService.searchByMonth(student, month);
        else applications = studentService.searchByTitle(student, title);
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
    public String saveDraft(@RequestParam("title") String title, @RequestParam("content") String content, HttpSession session, ModelMap modelMap){
        Student student = (Student)session.getAttribute(STUDENT);
        Draft draft = new Draft(title, content, student);
        studentService.saveDraft(student, draft);
        session.setAttribute(STUDENT, student);
        modelMap.addAttribute("filetype", "draft");
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
        studentService.deleteDraft(student.getDraft());
        student.setDraft(null);
        session.setAttribute(STUDENT, student);
        return "redirect:/student/applications";
    }

}
