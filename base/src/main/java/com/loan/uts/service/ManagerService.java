package com.loan.uts.service;

import com.loan.uts.model.Manager;
import com.loan.uts.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    public Manager login(String email, String password){
        return managerRepository.findByEmailAndPassword(email, password);
    }
}
