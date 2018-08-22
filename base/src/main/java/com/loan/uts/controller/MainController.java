package com.loan.uts.controller;

import com.loan.uts.model.Student;
import com.loan.uts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("studentid") Integer studentId,
                        @RequestParam("password") String password,
                        ModelMap modelMap){
        Student student = studentRepository.findStudentByIdAndPassword(studentId, password);
        if(student != null)  {
            modelMap.addAttribute("student", student);
            return "student";
        }
        else return "index";
    }


//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    public String getStudent(ModelMap modelMap) {
//        Student student = studentRepository.findOne(12345678);
//        List<Student> students=studentRepository.findAll();
//        modelMap.addAttribute("student", student);
//        return "student";
//    }

}