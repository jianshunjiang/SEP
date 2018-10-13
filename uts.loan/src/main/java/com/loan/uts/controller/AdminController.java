package com.loan.uts.controller;

import com.loan.uts.model.Administrator;
import com.loan.uts.model.Manager;
import com.loan.uts.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String home(ModelMap modelMap, @RequestParam(name = "error", required = false) String error) {
        modelMap.addAttribute("managers", managerService.getAll());
        if(error != null)modelMap.addAttribute("error", error);
            return "admin/home";
    }

    @RequestMapping(value = "/create_manager", method = RequestMethod.POST)
    public String createManager(HttpSession session, ModelMap modelMap, @RequestParam("email") String email, @RequestParam("password") String password){
        try{
            managerService.create(session, email, password);
            return "redirect:/admin/";
        }catch (Exception e){
            modelMap.addAttribute("error", e.getMessage());
            return "admin/newManager";
        }
    }

    @RequestMapping(value = "/modify_account", method = RequestMethod.GET)
    public String modifyManager(@RequestParam("id")Integer id, ModelMap modelMap, @RequestParam(name = "error", required = false) String error) {
        modelMap.addAttribute("manager", managerService.get(id));
        if(error != null) modelMap.addAttribute("error", error);
        return "manager/editAccount";
    }

    @RequestMapping(value = "/modify_account", method = RequestMethod.POST)
    public String saveChanges(@RequestParam("id")Integer id,
                              @RequestParam("username")String username,
                              @RequestParam("password")String password,
                              @RequestParam("repeatPassword")String repeatPassword,
                              @RequestParam("mobile")String mobile,
                              @RequestParam("email")String email, HttpSession session){
        if(password.equals(repeatPassword)){
            Administrator admin = (Administrator)session.getAttribute("admin");
            managerService.modify(id, username, password, mobile);
            return "redirect:/admin/";
        }
        return "redirect:/loanManager/modify_account?id=" + id + "&error=" + "Passwords do not match";
    }

    @RequestMapping(value = "delete_manager", method = RequestMethod.POST)
    public String deleteManager(@RequestParam("id")Integer id, ModelMap modelMap){
        try {
            managerService.delete(id);
        }catch (Exception e){
            modelMap.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin";
    }



}
