package com.loan.uts.repository;

import com.loan.uts.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Handles the database operations of table admin.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    /**
     * Search for admin record with given username and password in the database.
     * @param username
     * @param password
     * @return
     */
    Administrator findByUsernameAndPassword(String username, String password);
}
