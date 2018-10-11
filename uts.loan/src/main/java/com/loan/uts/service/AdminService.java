package com.loan.uts.service;

import com.loan.uts.model.Administrator;
import com.loan.uts.model.Manager;
import com.loan.uts.repository.AdministratorRepository;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for Administrator.
 */
@Transactional
@Service
public class AdminService {
    @Autowired
    AdministratorRepository administratorRepository;

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
        return administratorRepository.findByUsernameAndPassword(username, password);
    }

    // Find Manager by id.
    public Manager getManager(Integer id){
        return managerRepository.findOne(id);
    }

    // Delete Manager by id.
    public void deleteManager(Integer managerId){
        Manager manager = getManager(managerId);
        deleteManager(manager);
    }

    // Delete manager
    public void deleteManager(Manager manager){
        manager.setDeleted(true);
        managerRepository.save(manager);
    }

    // Return list of manager

    /**
     *
     * @return
     */
    public List<Manager> getManagers(){
        return managerRepository.findAllByDeletedFalse();
    }

    // Delete application through id
    public void deleteApplication(Integer id){
        applicationRepository.delete(id);
    }

    public Administrator get(Integer id){
        return administratorRepository.findOne(id);
    }

    public void resetPassword(String password, Integer id){
        Administrator admin = get(id);
        admin.setPassword(password);
        administratorRepository.save(admin);
    }
}
