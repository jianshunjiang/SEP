package com.loan.uts.repository;

import com.loan.uts.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Collection<Application> getApplicationsByStudentId(Integer studentId);
}
