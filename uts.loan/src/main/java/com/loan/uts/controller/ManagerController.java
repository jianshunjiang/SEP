package com.loan.uts.controller;

import com.loan.uts.model.Application;
import com.loan.uts.model.Manager;
import com.loan.uts.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static com.loan.uts.controller.LoginController.LOAN_MANAGER;
import static com.loan.uts.controller.StudentController.APPLICATIONS;
import static com.loan.uts.model.Application.PROCESSING;
import static com.loan.uts.model.Application.SUBMITTED;

@Controller
@RequestMapping("/loanManager")
public class  ManagerController {

    private static Logger logger = LoggerFactory.getLogger(ManagerController.class);
    public static final String APPLICATION = "application";

    @Autowired
    ManagerService managerService;

    /**
     * Go to the home page of manager.
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home(){

        return "manager";
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
        return "managerApplications";
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
        modelMap.addAttribute(APPLICATION, application);
        logger.info("Application detail: " + application.toString());
        return "appDetail";
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
    public String manage(@RequestParam("id") Integer id, @RequestParam("result") String result, @RequestParam("comment") String comment) {
        managerService.manageApp(id, result, new Date(), comment);
        return "redirect:/loanManager/applications";
    }

    @RequestMapping(value = "/modify_account", method = RequestMethod.GET)
    public String modifyAccount(@RequestParam("id") Integer id, ModelMap modelMap, @RequestParam(required = false, name = "error") String error) {
        modelMap.addAttribute("manager", managerService.get(id));
        if(error != null) modelMap.addAttribute("error", error);
        return "modify_account";
    }

    @RequestMapping(value = "/modify_account", method = RequestMethod.POST)
    public String saveChanges(@RequestParam("id") Integer id,
                              @RequestParam("password") String password,
                              @RequestParam("repeatPassword") String repeatPassword,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("email") String email, HttpSession session) {
        if(password.equals(repeatPassword)) {
            Manager manager = (Manager) session.getAttribute("manager");;
            managerService.modify(id, password, email, manager, mobile);
            return "redirect:/manager/";
        }
        return "redirect:/manager/modify_account?id=" + id + "&error=" + "Passwords do not match";
    }

    /**
     * Get to the decline page of application.
     * @param id The id of the application that is supposed to be declined.
     * @return
     */
    @RequestMapping(value = "/applications/decline", method = RequestMethod.GET)
    public String decline(@RequestParam("id") Integer id, ModelMap modelMap) {
        modelMap.addAttribute(APPLICATION, managerService.getApplication(id));
        return "declineApp";
    }
}
