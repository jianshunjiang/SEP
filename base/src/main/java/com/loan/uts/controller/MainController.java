package com.loan.uts.controller;

import com.loan.uts.model.Student;
import com.loan.uts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String getStudent(ModelMap modelMap) {
        Student student = studentRepository.findOne(12345678);
        modelMap.addAttribute("student", student);
        return "student";
    }

}