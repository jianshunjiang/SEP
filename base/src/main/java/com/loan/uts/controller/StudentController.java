package com.loan.uts.controller;

import com.loan.uts.model.Application;
import com.loan.uts.model.Student;
import com.loan.uts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import java.util.Set;

import static com.loan.uts.controller.LoginController.STUDENT;

@Controller
public class StudentController {
    public static final String APPLICATIONS = "applications";

    @Autowired
    StudentService studentService;

    @RequestMapping(value = {"/student/applications"}, method = RequestMethod.GET)
    public String applications(HttpSession session, ModelMap modelMap) {
        Student student = (Student) session.getAttribute(STUDENT);
        Set<Application> applications = studentService.getApplication(student.getId());
        modelMap.addAttribute(APPLICATIONS, applications);
        return "applications";
    }

    @RequestMapping(value = {"/student"}, method = RequestMethod.GET)
    public String student(HttpSession session, ModelMap modelMap) {
        return "student";
    }
}
