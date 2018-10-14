package com.loan.uts.controller;

import com.loan.uts.model.Application;
import com.loan.uts.model.Attachment;
import com.loan.uts.model.Manager;
import com.loan.uts.service.AttachmentService;
import com.loan.uts.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Set;

import static com.loan.uts.controller.LoginController.LOAN_MANAGER;
import static com.loan.uts.controller.StudentController.APPLICATIONS;
import static com.loan.uts.model.Application.PROCESSING;
import static com.loan.uts.model.Application.REPLIED;
import static com.loan.uts.model.Application.SUBMITTED;

@Controller
@RequestMapping("/loanManager")
public class  ManagerController {

    private static Logger logger = LoggerFactory.getLogger(ManagerController.class);
    public static final String APPLICATION = "application";
    public static final String ATTACHMENTS = "attachments";

    @Autowired
    ManagerService managerService;
    @Autowired
    AttachmentService attachmentService;

    /**
     * Go to the home page of manager.
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home(){

        return "manager/home";
    }

    /**
     * Get to the unhandled application lists
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping( value = {"/applications"}, method = RequestMethod.GET)
    public String getApplications(HttpSession session, ModelMap modelMap){
        Manager manager = (Manager) session.getAttribute(LOAN_MANAGER);
        modelMap.addAttribute(APPLICATIONS, managerService.getUnhandledApp(manager));
        return "manager/applications";
    }

    /**
     * View detail of applications.
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/applications/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Integer id, ModelMap modelMap){
        Application application = managerService.getApplication(id);
        if(application.getStatus().equals(SUBMITTED)) managerService.manageApp(application, PROCESSING);
        Set<Attachment> attachmentSet = attachmentService.getAttachments(application);
        modelMap.addAttribute(ATTACHMENTS, attachmentService.getAttachments(application));
        modelMap.addAttribute(APPLICATION, application);
        logger.info("Application detail: " + application.toString());
        return "manager/appDetail";
    }

    /**
     * Approve the application and save it in the database, back to the applications page.
     * @param id The id of the application that is supposed to be approved.
     * @return
     */
    @RequestMapping(value = "/applications/manage", method = RequestMethod.GET)
    public String manage(@RequestParam("id") Integer id, @RequestParam("result") String result) {
        managerService.manageApp(id, result, new Date(), null);
        return "redirect:/loanManager/applications";
    }

    /**
     * Disapprove the application and save it in the database, back to the applications page.
     * @param id The id of the application that is supposed to be approved.
     * @return
     */
    @RequestMapping(value = "/applications/manage", method = RequestMethod.POST)
    public String manage(@RequestParam("id") Integer id, @RequestParam("result") String result, @RequestParam(name = "comment", required = false) String comment) {
        if (result.equals(REPLIED)) managerService.manageApp(id, result, null, comment);
        else managerService.manageApp(id, result, new Date(), comment);
        return "redirect:/loanManager/applications";
    }

    @RequestMapping(value = "/modify_account", method = RequestMethod.GET)
    public String modifyAccount(@RequestParam("id") Integer id, ModelMap modelMap, @ModelAttribute("userAttribute") Manager manager,
                                @RequestParam(required = false, name = "error") String error) {
        modelMap.addAttribute("id", managerService.get(id));
        if(error != null) modelMap.addAttribute("error", error);
        return "manager/editAccount";
    }

    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.PUT)
    public String saveChanges(@RequestParam("id") Integer id,
                              @RequestParam("firstname") String firstname,
                              @RequestParam("firstname") String lastname,
                              @RequestParam("password") String password,
                              @RequestParam("repeatPassword") String repeatPassword,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("email") String email) {
        if(password.equals(repeatPassword)) {
            managerService.edit(id, password, email, mobile, firstname, lastname);
            return "redirect:/loanManager/";
        }
        return "redirect:/loanManager/modify_account?id=" + id + "&error=" + "Passwords do not match";
    }

    /**
     * Get to the decline page of application.
     * @param id The id of the application that is supposed to be declined.
     * @return
     */
    @RequestMapping(value = "/applications/decline", method = RequestMethod.GET)
    public String decline(@RequestParam("id") Integer id, @RequestParam("result") String result, ModelMap modelMap) {
        modelMap.addAttribute(APPLICATION, managerService.getApplication(id));
        modelMap.addAttribute("result", result);
        return "manager/declineApp";
    }

}
