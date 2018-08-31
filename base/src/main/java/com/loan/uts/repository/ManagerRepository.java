package com.loan.uts.repository;

import com.loan.uts.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface handles the data operations of Manager entity.
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    /**
     * Find the corresponding manager records with given email and password.
     * @param email
     * @param password
     * @return
     */
    Manager findByEmailAndPassword(String email, String password);
}
