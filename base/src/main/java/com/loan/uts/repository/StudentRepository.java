package com.loan.uts.repository;

import com.loan.uts.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface deal with the database operations in the student table.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Find the student with given id and password in the database.
     * @param id
     * @param password
     * @return
     */
    Student findByIdAndPassword(Integer id, String password);
}
