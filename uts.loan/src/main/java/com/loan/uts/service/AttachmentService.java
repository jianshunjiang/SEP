package com.loan.uts.service;
import com.loan.uts.model.Application;
import com.loan.uts.model.Attachment;
import com.loan.uts.model.Draft;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;

/**
 * Service for operations about attachments.
 */
@Service
public class AttachmentService {
    private static Logger logger = LoggerFactory.getLogger(AttachmentService.class);

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * Get attachments for a specific application.
     *
     * @param application
     * @return
     */
    public Set<Attachment> getAttachments(Application application) {
        return attachmentRepository.findAllByApplication(application);
    }

    /**
     * Get attachments for a specific draft.
     *
     * @param draft
     * @return
     */
    public Set<Attachment> getAttachments(Draft draft) {
        return attachmentRepository.findAllByDraft(draft);
    }


    /**
     * Delete all attachments for an application.
     * @param id
     * @param attachmentPath
     */
    public void deleteAttachmentsByApplication(Integer id, String attachmentPath) {
        Set<Attachment> attachmentSet = attachmentRepository.findAllByApplication(applicationRepository.findOne(id));
        if (attachmentSet != null && attachmentSet.size() != 0) {
            for (Attachment attachment : attachmentSet) {
                delete(attachment, attachmentPath);
            }
        }
    }

    /**
     * Delete all attachments for an application.
     * @param draft
     * @param attachmentPath
     */
    public void deleteAttachmentsByDraft(Draft draft, String attachmentPath) {
        Set<Attachment> attachmentSet = attachmentRepository.findAllByDraft(draft);
        if (attachmentSet != null && attachmentSet.size() != 0) {
            for (Attachment attachment : attachmentSet) {
                delete(attachment, attachmentPath);
            }
        }
    }

    /**
     * Delete attachments by ids.
     * @param ids
     */
    public void deleteAttachments(Integer[] ids, String path){
        if (ids == null) return;
        for(Integer id: ids){
            if (id != null){
                Attachment attachment = get(id);
                delete(attachment, path);
            }
        }
    }

    /**
     * Get attachment by id.
     */
    public Attachment get(Integer id){
        return attachmentRepository.findOne(id);
    }

    /**
     * Delete an attachment file and delete the record in the database.
     * @param attachment
     * @param path
     */
    public void delete(Attachment attachment, String path){
        if (attachment == null) return;
        File f = new File(path + attachment.getName());
        if (f.delete()) {
            logger.info("Deleted draft No." + attachment.getId());
            attachmentRepository.delete(attachment);
        }
    }

    /**
     * Remove the draft attachments to the application.
     * @param draft
     * @param application
     */
    public void moveAttachments(Draft draft, Application application){
        Set<Attachment> attachments = getAttachments(draft);
        for (Attachment attachment: attachments){
            attachment.setDraft(null);
            attachment.setApplication(application);
        }
        attachmentRepository.save(attachments);
    }

}
