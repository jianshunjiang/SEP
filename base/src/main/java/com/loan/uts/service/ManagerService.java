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
import java.util.List;

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

    public void AssignApplication(Application application){
        Manager manager = getRandomManager();
        application.setManager(manager);
        applicationRepository.save(application);
        Email email = new Email(application.getId(), application.getTitle(), manager, Email.ASSIGNED);
        emailService.sendEmail(email);
    }

    private Manager getRandomManager(){
        List<Manager> managers = managerRepository.findAllByDeleteFalse();
        int index = (int)(Math.random()*(managers.size()));
        return managers.get(index);
    }
}
