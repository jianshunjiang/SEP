package com.loan.uts.controller;

import com.loan.uts.exception.EmailExistsException;
import com.loan.uts.exception.HasUnhandledWorkException;
import com.loan.uts.model.Administrator;
import com.loan.uts.service.AdminService;
import com.loan.uts.service.AttachmentService;
import com.loan.uts.service.ManagerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import static com.loan.uts.controller.LoginController.SYSTEM_ADMIN;
import static com.loan.uts.controller.StudentController.APPLICATIONS;

/**
 * Controls functions for administrators.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    /**
     * log tools.
     */
    private static Logger logger = LogManager.getLogger(AdminController.class);

    /**
     * Business functions for administrator.
     */
    @Autowired
    AdminService adminService;

    /**
     * Basic operations for managers.
     */
    @Autowired
    ManagerService managerService;

    /**
     * Functions for attachhments
     */
    @Autowired
    AttachmentService attachmentService;

    /**
     * Go to the home page of administrator.
     *
     * @return
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home() {
        logger.info("Admin: home");
        return "admin/home";
    }

    /**
     * Go to the profile page.
     *
     * @return
     */
    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String getProfile() {
        logger.info("Admin: profile");
        return "admin/profile";
    }

    /**
     * Go to the homepage of resetting password.
     *
     * @return
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
    public String resetPassword() {
        logger.info("Admin: reset password page.");
        return "admin/resetPassword";
    }

    /**
     * Go to the homepage of resetting password.
     *
     * @return
     */
    @RequestMapping(value = {"/resetPassword"}, method = RequestMethod.POST)
    public String resetPassword(@RequestParam("newPassword") String newPassword,
                                @RequestParam("id") Integer id, HttpSession httpSession) {
        Administrator admin = adminService.resetPassword(newPassword, id);
        httpSession.setAttribute(SYSTEM_ADMIN, admin);
        logger.info("Admin: reset password.");
        return "admin/profile";
    }

    /**
     * Go to the homepage of managers.
     *
     * @param modelMap
     * @param error
     * @return
     */
    @RequestMapping(value = {"/managers"}, method = RequestMethod.GET)
    public String managers(ModelMap modelMap, @RequestParam(name = "error", required = false) String error) {
        modelMap.addAttribute("managers", adminService.getManagers());
        if (error != null) modelMap.addAttribute("error", error);
        logger.info("Admin: managers");
        return "admin/managers";
    }

    /**
     * Go to the page to add new manager.
     *
     * @param error
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managers/add", method = RequestMethod.GET)
    public String addManager(@RequestParam(name = "error", required = false) String error, ModelMap modelMap) {
        if (error != null) modelMap.addAttribute("error", error);
        return "admin/newManager";
    }

    /**
     * Go to the page to edit manager, prepared the manager information for editing.
     * Display the edition error message if required.
     *
     * @param modelMap
     * @param managerId
     * @param error
     * @return
     */
    @RequestMapping(value = "/managers/edit", method = RequestMethod.GET)
    public String editManager(ModelMap modelMap, @RequestParam("managerId") Integer managerId, @RequestParam(name = "error", required = false) String error) {
        if (error != null) modelMap.addAttribute("error", error);
        modelMap.addAttribute("manager", managerService.get(managerId));
        return "admin/newManager";
    }

    /**
     * Add manager if there is no id paramater, or update manager information when manager id is provided.
     *
     * @param id
     * @param adminId
     * @param firstname
     * @param lastname
     * @param password
     * @param mobile
     * @param email
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managers/edit", method = RequestMethod.POST)
    public String editManager(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam(name = "adminId", required = false) Integer adminId,
                              @RequestParam("firstname") String firstname,
                              @RequestParam("lastname") String lastname,
                              @RequestParam("password") String password,
                              @RequestParam("mobile") String mobile,
                              @RequestParam("email") String email,
                              ModelMap modelMap) {

        if (id == null) {
            try {
                managerService.add(email, password, firstname, lastname, mobile, adminService.get(adminId));
            } catch (EmailExistsException e) {
                modelMap.addAttribute("error", e.getMessage());
                return "redirect:/admin/managers/add";
            }
        } else {
            try {
                managerService.update(id, password, email, mobile, firstname, lastname);
            } catch (EmailExistsException e) {
                modelMap.addAttribute("error", e.getMessage());
                modelMap.addAttribute("managerId", id);
                return "redirect:/admin/managers/edit";
            }
        }
        return "redirect:/admin/managers";
    }

    /**
     * Delete the manager by its id.
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "managers/delete", method = RequestMethod.POST)
    public String deleteManager(@RequestParam("managerId") Integer id, ModelMap modelMap) {
        try {
            managerService.delete(id);
        } catch (HasUnhandledWorkException e) {
            modelMap.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/managers";
    }

    /**
     * Get to the applications page.
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "applications", method = RequestMethod.GET)
    public String applications(ModelMap modelMap) {
        modelMap.addAttribute(APPLICATIONS, adminService.getApplications());
        return "admin/applications";
    }


    /**
     * Delete the application by application id.
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "applications/delete", method = RequestMethod.POST)
    public String deleteApplication(@RequestParam("id") Integer id, ModelMap modelMap, HttpSession session) {
        try {
            String path = session.getServletContext().getRealPath("/").split("target")[0] + "upload/";
            attachmentService.deleteAttachmentsByApplication(id, path);
            adminService.deleteApplication(id);
        } catch (Exception e) {
            modelMap.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin/applications";
    }

}
