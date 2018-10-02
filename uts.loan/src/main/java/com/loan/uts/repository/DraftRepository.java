package com.loan.uts.repository;

import com.loan.uts.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface DraftRepository extends JpaRepository<Draft, Integer> {
}
