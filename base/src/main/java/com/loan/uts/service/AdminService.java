package com.loan.uts.service;

import com.loan.uts.model.Administrator;
import com.loan.uts.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminService {
    @Autowired
    AdministratorRepository administratorRepository;
    public Administrator login(String username, String password){
        return administratorRepository.findByUsernameAndPassword(username, password);
    }
}
