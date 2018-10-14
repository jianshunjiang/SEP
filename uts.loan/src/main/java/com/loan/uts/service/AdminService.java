package com.loan.uts.service;

import com.loan.uts.exception.EmailExistsException;
import com.loan.uts.exception.HasUnhandledWorkException;
import com.loan.uts.model.Administrator;
import com.loan.uts.model.Application;
import com.loan.uts.model.Manager;
import com.loan.uts.repository.AdministratorRepository;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    /**
     * Find manager by ID.
     * @param id
     * @return
     */
    public Manager getManager(Integer id){
        return managerRepository.findOne(id);
    }

    /**
     * Delete manager by id.
     * @param managerId
     */
    public void deleteManager(Integer managerId) throws HasUnhandledWorkException {
        Manager manager = getManager(managerId);
        deleteManager(manager);
    }

    /**
     * Delete manager
     * @param manager
     */
    public void deleteManager(Manager manager) throws HasUnhandledWorkException {
        Set<Application> unhandledwork = applicationRepository
                .getApplicationsByManagerIdAndResultDateIsNull(manager.getId());
        if( unhandledwork != null && unhandledwork.size() != 0) throw new HasUnhandledWorkException(manager.getId());
        manager.setDeleted(true);
        managerRepository.save(manager);
    }

    /**
     * Add new manager.
     * @param email
     * @param password
     * @param firstname
     * @param lastname
     * @param mobile
     */
    public void addManager(String email, String password, String firstname, String lastname, String mobile) throws EmailExistsException {
        if (managerRepository.findByEmailAndDeletedFalse(email) != null ) throw new EmailExistsException("Add", email);
        Manager manager = new Manager(firstname, lastname, email, mobile, password);
        managerRepository.save(manager);
    }

    public void editManager(Integer id, String password, String email, String mobile, String firstname, String lastname) throws EmailExistsException {
        Manager duplicated = managerRepository.findByEmailAndDeletedFalse(email);
        if (duplicated.getId() != id) throw new EmailExistsException("Edit", email);
        Manager manager = getManager(id);
        manager.setId(id);
        manager.setPassword(password);
        manager.setEmail(email);
        manager.setMobile(mobile);
        manager.setFirstname(firstname);
        manager.setLastname(lastname);
        managerRepository.saveAndFlush(manager);
    }

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
