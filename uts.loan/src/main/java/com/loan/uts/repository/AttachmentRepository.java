package com.loan.uts.repository;

import com.loan.uts.model.Application;
import com.loan.uts.model.Attachment;
import com.loan.uts.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    Set<Attachment> findAllByApplication(Application application);
    Set<Attachment> findAllByDraft(Draft draft);
}
