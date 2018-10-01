package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Email;
import com.loan.uts.model.Manager;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.MediaName;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.loan.uts.model.Application.ACCEPTED;

@Transactional
@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    EmailService emailService;

    public Manager login(String email, String password){
        return managerRepository.findByEmailAndPassword(email, password);
    }

    /**
     * Assign an application to a manager.
     * @param application
     */
    public void AssignApplication(Application application){
        Manager manager = getRandomManager();
        application.setManager(manager);
        application = applicationRepository.save(application);
        emailService.notifyManager(application);
    }

    private Manager getRandomManager(){
        List<Manager> managers = managerRepository.findAllByDeleteFalse();
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
    }


}
