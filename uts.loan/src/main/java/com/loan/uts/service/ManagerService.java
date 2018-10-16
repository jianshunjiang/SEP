package com.loan.uts.service;

import com.loan.uts.exception.EmailExistsException;
import com.loan.uts.exception.HasUnhandledWorkException;
import com.loan.uts.model.Administrator;
import com.loan.uts.model.Application;
import com.loan.uts.model.Manager;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.ManagerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.loan.uts.util.EncrptUtil.encrypt;

@Transactional
@Service
public class ManagerService {

    private static Logger logger = LogManager.getLogger(ManagerService.class);

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    EmailService emailService;

    /**
     * Log in method for manager.
     * Encrypt the password with email as salt.
     * @param email
     * @param password
     * @return
     */
    public Manager login(String email, String password){
        return managerRepository.findByEmailAndPasswordAndDeletedFalse(email, encrypt(email + password) );
    }

    /**
     * Get manager by id.
     * @param id
     * @return
     */
    public Manager get(Integer id) {
        return managerRepository.findOne(id);
    }

    /**
     * Update manager account
     * @param id
     * @param email
     * @param mobile
     * @param firstname
     * @param lastname
     * @throws EmailExistsException
     */
    public Manager update(Integer id, String email, String mobile, String firstname, String lastname) throws EmailExistsException {
        Manager duplicated = managerRepository.findByEmailAndDeletedFalse(email);
        if (duplicated.getId() != id) throw new EmailExistsException("Update", email);
        Manager manager = get(id);
        if (!manager.getEmail().equals(email)){
            manager.setEmail(email);
            manager.setPassword(encrypt(email + manager.getPassword()));
        }
        manager.setMobile(mobile);
        manager.setFirstname(firstname);
        manager.setLastname(lastname);
        managerRepository.saveAndFlush(manager);
        logger.info("Updated Loan Manager No." + id);
        return manager;
    }

    /**
     * Get all undeleted manager.
     * @return
     */
    public List<Manager> getAll() {

        return managerRepository.findAllByDeletedFalse();
    }

    /**
     * Assign an application to a manager.
     * @param application
     */
    public Manager AssignApplication(Application application){
        Manager manager = getRandomManager();
        application.setManager(manager);
        application = applicationRepository.save(application);
        logger.info("Application No." + application.getId() + " assigned: Manager " + manager.toString());
        emailService.notifyManager(application);
        return manager;
    }

    /**
     * Get random manager.
     * @return
     */
    private Manager getRandomManager(){
        List<Manager> managers = managerRepository.findAllByDeletedFalse();
        int index = (int)(Math.random()*(managers.size()));
        return managers.get(index);
    }

    /**
     * Reset manager's password.
     * @param id
     * @param password
     * @return
     */
    public Manager resetPassword(Integer id, String password){
        Manager manager = get(id);
        manager.setPassword(encrypt(manager.getEmail() + password));
        managerRepository.save(manager);
        return manager;
    }

    /**
     * Add new manager.
     * @param email
     * @param password
     * @param firstname
     * @param lastname
     * @param mobile
     */
    public void add(String email, String password, String firstname, String lastname, String mobile, Administrator admin) throws EmailExistsException {
        if (managerRepository.findByEmailAndDeletedFalse(email) != null ) throw new EmailExistsException("Add", email);
        Manager manager = new Manager(firstname, lastname, email, mobile, encrypt(email + password));
        manager.setAdminByAdminId(admin);
        manager = managerRepository.save(manager);
        logger.info("Added manager No." + manager.getId() + " by admin No." + admin.getId());
    }

    public void update(Integer id, String password, String email, String mobile, String firstname, String lastname) throws EmailExistsException {
        Manager duplicated = managerRepository.findByEmailAndDeletedFalse(email);
        if (duplicated.getId() != id) throw new EmailExistsException("Update", email);
        Manager manager = get(id);
        manager.setId(id);
        manager.setEmail(email);
        if (! manager.getPassword().equals(password)){
            manager.setPassword(encrypt(email + password));
        }
        manager.setMobile(mobile);
        manager.setFirstname(firstname);
        manager.setLastname(lastname);
        managerRepository.saveAndFlush(manager);
        logger.info("Update manager No." + id + " by admin");
    }

    /**
     * Delete manager by id.
     * @param managerId
     */
    public void delete(Integer managerId) throws HasUnhandledWorkException {
        Manager manager = get(managerId);
        delete(manager);
    }

    /**
     * Delete manager
     * @param manager
     */
    public void delete(Manager manager) throws HasUnhandledWorkException {
        Set<Application> unhandledwork = getUnhandledApp(manager);
        if( unhandledwork != null && unhandledwork.size() != 0) throw new HasUnhandledWorkException(manager.getId());
        manager.setDeleted(true);
        logger.info("Deleted manager: No." + manager.getId());
        managerRepository.save(manager);
    }

    /**
     * Get all unhandled applications for the manager.
     * @param manager
     * @return
     */
    public Set<Application> getUnhandledApp(Manager manager){
        logger.info("Get unhandled application for Loan Manager No." + manager.getId());
        return applicationRepository.getApplicationsByManagerIdAndResultDateIsNull(manager.getId());
    }

    /**
     * Find a specific application.
     * @param id
     * @return
     */
    public Application getApplication(Integer id){
        return applicationRepository.findOne(id);
    }

    /**
     * Notify the student that the application is processing.
     * @param application
     * @return
     */
    public void manageApp(Application application, String result){
        application.setStatus(result);
        applicationRepository.save(application);
        emailService.notifyStudent(application);
       logger.info("Application No. " + application.getId() + result);
    }

    /**
     * Approve or decline the application and notify the student.
     * @param applicationId
     * @return
     */
    public void manageApp(Integer applicationId, String result, Date date, String comment){
        Application application = getApplication(applicationId);
        application.setResultDate(date);
        application.addComment(comment);
        manageApp(application, result);
        logger.info("Application No. " + application.getId() + result);
    }


}
