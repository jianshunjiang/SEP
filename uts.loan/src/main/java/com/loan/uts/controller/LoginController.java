package com.loan.uts.controller;

import com.loan.uts.model.Administrator;
import com.loan.uts.model.Manager;
import com.loan.uts.model.Student;
import com.loan.uts.service.AdminService;
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

import javax.servlet.http.HttpSession;

/**
 * This controller deal with the log in and log out request from all type of users.
 */

@Controller
public class LoginController {

    public static final String STUDENT = "student";
    public static final String LOAN_MANAGER = "manager";
    public static final String SYSTEM_ADMIN = "admin";
    public static final String USER_TYPE = "type";


    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    ManagerService managerService;

    @Autowired
    AdminService adminService;

    /**
     * Handle the log in action with different user types.
     *
     * @param username
     * @param password
     * @param userType
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("userType") String userType,
                        HttpSession session, ModelMap modelMap) {
        try {
            if (userType.equals(STUDENT)) {
                Student student = studentService.login(username, password);
                if (student != null) {
                    session.setAttribute(userType, student);
                    session.setAttribute(USER_TYPE, userType);
                    logger.info("Logged in (" + userType + " ): " + username);
                    return "redirect:/student";
                }
            }
            if (userType.equals(LOAN_MANAGER)) {
                Manager manager = managerService.login(username, password);
                if (manager != null) {
                    session.setAttribute(userType, manager);
                    session.setAttribute(USER_TYPE, userType);
                    logger.info("Logged in (" + userType + " ): " + username);
                    return "redirect:/loanManager";
                }
            }

            if (userType.equals(SYSTEM_ADMIN)) {
                Administrator admin = adminService.login(username, password);
                if (admin != null) {
                    session.setAttribute(userType, admin);
                    session.setAttribute(USER_TYPE, userType);
                    logger.info("Logged in (" + userType + " ): " + username);
                    return "redirect:/admin";
                }
            }
            modelMap.addAttribute("error", "Incorrect account or password");
            logger.error("Log in fail (" + userType + " ):" + username);
        } catch (Exception e) {
            modelMap.addAttribute("error", "Incorrect account or password");
            e.printStackTrace();
            logger.error("Log in fail (" + userType + " ):" + username);
        }

        return "login";
    }

    /**
     * Return the log in page.
     *
     * @return
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * Deal with the log out operation.
     *
     * @param session
     * @return index page.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        String userType = (String) session.getAttribute(USER_TYPE);

        String username = "";
        if (userType.equals(STUDENT)) {
            Student student = (Student) session.getAttribute(STUDENT);
            username = student.getId().toString();
        } else if (userType.equals(LOAN_MANAGER)) {
            Manager manager = (Manager) session.getAttribute(LOAN_MANAGER);
            username = manager.getEmail();
        } else if (userType.equals(SYSTEM_ADMIN)) {
            Administrator admin = (Administrator) session.getAttribute(SYSTEM_ADMIN);
            username = admin.getUsername();
        }
        session.removeAttribute(userType);
        logger.info("Log out( " + userType + " ): " + username);
        session.removeAttribute(USER_TYPE);
        session.invalidate();
        return "index";
    }


}