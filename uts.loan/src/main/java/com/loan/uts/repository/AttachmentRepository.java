package com.loan.uts.repository;

import com.loan.uts.model.Application;
import com.loan.uts.model.Attachment;
import com.loan.uts.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Handles the database operations for attachments.
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    /**
     * Find the attachments of an application.
     * @param application
     * @return
     */
    Set<Attachment> findAllByApplication(Application application);

    /**
     * Find attachments of the draft.
     * @param draft
     * @return
     */
    Set<Attachment> findAllByDraft(Draft draft);
}
