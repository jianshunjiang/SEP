package com.loan.uts.repository;

import com.loan.uts.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Set<Application> getApplicationsByStudentId(Integer studentId);
    Set<Application> getApplicationsByStudentIdAndResultDateIsNull(Integer studentId);
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNull(Integer studentId);
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNullAndTitleContaining(Integer studentId, String title);
    @Query(
            value = "SELECT * FROM application a WHERE a.student_id =?1 AND a.result_date IS NOT NULL AND a.submit_date like ?2% ",
            nativeQuery = true)
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNullAndSubmitDate(Integer studentId, String timeStr);
}
