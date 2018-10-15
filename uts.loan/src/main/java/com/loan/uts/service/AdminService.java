package com.loan.uts.service;


import com.loan.uts.model.*;
import com.loan.uts.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Set;

import static com.loan.uts.util.EncrptUtil.encrypt;

/**
 * Service for Administrator.
 */
@Transactional
@Service
public class AdminService {
    private static Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * The log in function of Administrator.
     * @param username
     * @param password
     * @return Administrator object.
     */
    public Administrator login(String username, String password){
        logger.info("Trying to login(Admin): " + username);
        return administratorRepository.findByUsernameAndPassword(username, encrypt(username + password));
    }

    /**
     *
     * @return
     */
    public List<Manager> getManagers(){
        return managerRepository.findAllByDeletedFalse();
    }

    /**
     * Delete application by application id.
     * @param id
     */
    public void deleteApplication(Integer id){
        Application application = applicationRepository.findOne(id);
        logger.info("Application No." + id + " deleted.");
        applicationRepository.delete(id);
    }

    /**
     * Get all applications.
     */
    public List<Application> getApplications(){
        return applicationRepository.findAll();
    }

    /**
     * Get admin by id.
     * @param id
     * @return
     */
    public Administrator get(Integer id){
        return administratorRepository.findOne(id);
    }

    /**
     * Reset the password of admin.
     * @param password
     * @param id
     * @return
     */
    public Administrator resetPassword(String password, Integer id){
        Administrator admin = get(id);
        admin.setPassword(encrypt(admin.getUsername() + password));
        administratorRepository.save(admin);
        return admin;
    }
}
