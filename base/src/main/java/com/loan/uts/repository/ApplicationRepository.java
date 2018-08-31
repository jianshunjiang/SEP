package com.loan.uts.repository;

import com.loan.uts.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Set<Application> getApplicationsByStudentId(Integer studentId);
}
