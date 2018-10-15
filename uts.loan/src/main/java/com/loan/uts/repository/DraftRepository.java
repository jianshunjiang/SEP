package com.loan.uts.repository;

import com.loan.uts.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Hanlde database operations for draft.
 */
public interface DraftRepository extends JpaRepository<Draft, Integer> {
}
