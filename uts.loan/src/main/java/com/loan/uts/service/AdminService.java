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

@Transactional
@Service
public class AdminService {
    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    public Administrator login(String username, String password){
        return administratorRepository.findByUsernameAndPassword(username, password);
    }

    public Manager getManager(Integer id){
        return managerRepository.findOne(id);
    }

    public void deleteManager(Integer managerId){
        Manager manager = getManager(managerId);
        deleteManager(manager);
    }

    public void deleteManager(Manager manager){
        manager.setDeleted(true);
        managerRepository.save(manager);
    }

    public List<Manager> getManagers(){
        return managerRepository.findAllByDeletedFalse();
    }

    public void deleteApplication(Integer id){
        applicationRepository.delete(id);
    }
}
