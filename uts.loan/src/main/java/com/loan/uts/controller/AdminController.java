package com.loan.uts.controller;

import com.loan.uts.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    public static final String PROFILE = "profile";


    @Autowired
    AdminService adminService;

    /**
     * Go to the home page of administrator.
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home() {
        return "admin/home";
    }

    /**
     * Get to the profile page
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping( value = {"/profile"}, method = RequestMethod.GET)
    public String profile(HttpSession session, ModelMap modelMap){
        return "admin/profile";
    }

    /**
     * Go to the homepage of resetting password.
     * @return
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword() {
        return "admin/resetPassword";
    }

    /**
     * Go to the homepage of staffs.
     * @return
     */
    @RequestMapping(value = {"/applications/add"}, method = RequestMethod.GET)
    public String staffs() {
        return "admin/staffs";
    }






    @RequestMapping(value = {"/managers"}, method = RequestMethod.GET)
    public String managers(ModelMap modelMap, @RequestParam(name = "error", required = false) String error) {
        modelMap.addAttribute("managers", adminService.getManagers());
        if (error != null) modelMap.addAttribute("error", error);
        return "admin/home";
    }

    @RequestMapping(value = "/managers/add", method = RequestMethod.GET)
    public String addManager() {
       return "admin/newManager";
    }

    @RequestMapping(value = "/managers/edit", method = RequestMethod.GET)
    public String editManager(ModelMap modelMap, @RequestParam("managerId") Integer managerId, @RequestParam(name = "error", required = false) String error) {
        if (error != null) modelMap.addAttribute("error", error);
        modelMap.addAttribute("manager", adminService.getManager(managerId));
        return "manager/editAccount";
    }

    @RequestMapping(value = "/managers/edit", method = RequestMethod.POST)
    public String editManager(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam("firstname") String firstname,
                              @RequestParam("lastname") String lastname,
                              @RequestParam("password") String password,
                              @RequestParam("repeatPassword") String repeatPassword,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("email") String email) {
        if (password.equals(repeatPassword)) {
            if (id == null) adminService.addManager(email, password, firstname, lastname, mobile);
            else adminService.editManager(id, password, email, mobile, firstname, lastname);
            return "redirect:/admin/managers";
        }
        return "";
    }

    @RequestMapping(value = "managers/delete", method = RequestMethod.POST)
    public String deleteManager(@RequestParam("managerId") Integer id, ModelMap modelMap) {
        try {
            adminService.deleteManager(id);
        } catch (Exception e) {
            modelMap.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/managers";
    }


}