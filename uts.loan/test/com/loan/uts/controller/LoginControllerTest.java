package com.loan.uts.controller;

import com.loan.uts.BaseUnitTest;
import com.loan.uts.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import static com.loan.uts.controller.LoginController.STUDENT;
import static com.loan.uts.controller.LoginController.USER_TYPE;
import static org.junit.Assert.*;

public class LoginControllerTest extends BaseUnitTest {

    @Autowired
    private LoginController loginController;


    @Test
    public void login() {
        //Test student login.
        assertEquals("redirect:/student", loginController.login("12345678", "12345678",
                LoginController.STUDENT, session, new ModelMap()));

        //Test student login.
        assertEquals("redirect:/loanManager", loginController.login("12416881@student.uts.edu.au", "111111",
                LoginController.LOAN_MANAGER, session, new ModelMap()));

        assertEquals("redirect:/admin", loginController.login("admin", "111111",
                LoginController.SYSTEM_ADMIN, session, new ModelMap()));

    }

    @Test
    public void logout() {
        session.setAttribute(USER_TYPE, STUDENT);
        Student student = new Student();
        student.setId(88888888);
        session.setAttribute(STUDENT, student);
        assertEquals("index", loginController.logout(session));
    }
}