package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Manager;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.ManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class ManagerService {

    private static Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    EmailService emailService;

    public Manager login(String email, String password){
        return managerRepository.findByEmailAndPasswordAndDeletedFalse(email, password);
    }

    public Manager get(Integer id) {
        return managerRepository.findOne(id);
    }

    public void modify(Integer id, String password, String email, Manager manager, String mobile) {
        manager.setId(id);
        manager.setPassword(password);
        manager.setEmail(email);
        //manager.setManagerByManagerId(manager);
        manager.setMobile(mobile);
        managerRepository.saveAndFlush(manager);
    }

    public void delete(Integer id) {
        Manager manager = managerRepository.findById(id);
        manager.setDeleted(true);
        managerRepository.saveAndFlush(manager);
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

    private Manager getRandomManager(){
        List<Manager> managers = managerRepository.findAllByDeletedFalse();
        int index = (int)(Math.random()*(managers.size()));
        return managers.get(index);
    }

    /**
     * Get all unhandled applications for the manager.
     * @param manager
     * @return
     */
    public Set<Application> getUnhandledApp(Manager manager){
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
        application.setComment(comment);
        manageApp(application, result);
        logger.info("Application No. " + application.getId() + result);
    }


}
