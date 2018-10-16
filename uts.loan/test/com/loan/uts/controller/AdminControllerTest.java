package com.loan.uts.controller;

import com.loan.uts.BaseUnitTest;
import com.loan.uts.model.Administrator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.loan.uts.controller.LoginController.SYSTEM_ADMIN;
import static com.loan.uts.controller.LoginController.USER_TYPE;
import static org.junit.Assert.assertEquals;

public class AdminControllerTest extends BaseUnitTest {

    @Autowired
    AdminController adminController;

    @Override
    public void setup() {
        super.setup();
        Administrator admin = new Administrator();
        admin.setId(1);
        admin.setPassword("0D5F043B540F205B1E112760541F4166");
        admin.setUsername("admin");
        session.setAttribute(USER_TYPE, SYSTEM_ADMIN);
        session.setAttribute(SYSTEM_ADMIN, admin);
    }

    @Test
    public void home(){
        assertEquals("admin/home", adminController.home());
    }

    @Test
    public void resetPassword() {
    }

    @Test
    public void managers() {
    }

    @Test
    public void editManager() {
    }

    @Test
    public void deleteManager() {
    }

    @Test
    public void applications() {
    }

    @Test
    public void deleteApplication() {
    }
}