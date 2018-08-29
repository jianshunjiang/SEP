package com.loan.uts.controller;

import com.loan.uts.model.Student;
import com.loan.uts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    public static final String STUDENT = "Student";
    public static final String LOAN_MANAGER = "Loan Manager";
    public static final String SYSTEM_ADMIN = "System Administrator";
    public static final String USER_TYPE = "User type";

    @Autowired
    StudentRepository studentRepository;

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }

    /**
     * Handle the log in action with different user types.
     *
     * @param studentId
     * @param password
     * @param userType
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public String login(@RequestParam("studentid") Integer studentId,
                        @RequestParam("password") String password,
                        @RequestParam("userType") String userType,
                        ModelMap modelMap) {
        if (userType.equals(STUDENT)) {
            Student student = studentRepository.findByIdAndPassword(studentId, password);
            if (student != null) {
                modelMap.addAttribute(STUDENT, student);
                modelMap.addAttribute(USER_TYPE, STUDENT);
                return "student";
            }
        } else return "index";
        return "index";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout/action", method = RequestMethod.GET)
    public String logout(ModelMap modelMap) {
        Student test = (Student) modelMap.get(STUDENT);
        String userType = (String) modelMap.get(USER_TYPE);
        modelMap.remove(userType);
        modelMap.remove(USER_TYPE);
        test = (Student) modelMap.get(STUDENT);
        return "logout";
    }


//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    public String getStudent(ModelMap modelMap) {
//        Student student = studentRepository.findOne(12345678);
//        List<Student> students=studentRepository.findAll();
//        modelMap.addAttribute("student", student);
//        return "student";
//    }

}