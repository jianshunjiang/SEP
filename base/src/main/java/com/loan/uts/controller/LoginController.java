package com.loan.uts.controller;

import com.loan.uts.model.Administrator;
import com.loan.uts.model.Manager;
import com.loan.uts.model.Student;
import com.loan.uts.service.AdminService;
import com.loan.uts.service.ManagerService;
import com.loan.uts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    public static final String STUDENT = "Student";
    public static final String LOAN_MANAGER = "Loan Manager";
    public static final String SYSTEM_ADMIN = "System Administrator";
    public static final String USER_TYPE = "User type";

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
        if (userType.equals(STUDENT)) {
            Student student = studentService.login(username, password);
            if (student != null) {
                session.setAttribute(userType, student);
                session.setAttribute(USER_TYPE, userType);
                return "student";
            }
        }
        if(userType.equals(LOAN_MANAGER)){
            Manager manager = managerService.login(username, password);
            if(manager != null){
                session.setAttribute(userType, manager);
                session.setAttribute(USER_TYPE, userType);
                return "manager";
            }
        }

        if(userType.equals(SYSTEM_ADMIN)){
            Administrator admin = adminService.login(username, password);
            if(admin != null){
                session.setAttribute(userType, admin);
                session.setAttribute(USER_TYPE, userType);
                return "admin";
            }
        }

        modelMap.addAttribute("error", "Incorrect account or password");
        return "login";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        String userType = (String) session.getAttribute(USER_TYPE);
        session.removeAttribute(userType);
        session.removeAttribute(USER_TYPE);
        session.invalidate();
        return "logout";
    }


}