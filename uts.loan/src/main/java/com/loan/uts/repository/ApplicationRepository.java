package com.loan.uts.repository;

import com.loan.uts.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Handle the database operations for applications.
 */
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    /**
     * Get applications within the student id.
     * @param studentId
     * @return
     */
    Set<Application> getApplicationsByStudentId(Integer studentId);

    /**
     * Get unfinished applications for the student.
     * @param studentId
     * @return
     */
    Set<Application> getApplicationsByStudentIdAndResultDateIsNull(Integer studentId);

    /**
     * Get finished applications for the student.
     * @param studentId
     * @return
     */
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNull(Integer studentId);

    /**
     * Get finished applications with title keyword.
     * @param studentId
     * @param title
     * @return
     */
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNullAndTitleContaining(Integer studentId, String title);

    /**
     * Get applications has the given manager id.
     * @param managerId
     * @return
     */
    Set<Application> getApplicationsByManagerIdAndResultDateIsNull(Integer managerId);

    /**
     * Get the applications within the student id and status
     * @param studentId
     * @param status
     * @return
     */
    Set<Application> getAllByStudentIdAndStatus(Integer studentId, String status);

    /**
     * Search for applications within the student id and date range.
     * @param studentId
     * @param timeStr
     * @return
     */
    @Query(
            value = "SELECT * FROM application a WHERE a.student_id =?1 AND a.result_date IS NOT NULL AND a.submit_date like ?2% ",
            nativeQuery = true)
    Set<Application> getApplicationsByStudentIdAndResultDateIsNotNullAndSubmitDate(Integer studentId, String timeStr);
}
