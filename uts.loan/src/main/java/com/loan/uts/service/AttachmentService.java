package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Attachment;
import com.loan.uts.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    public Set<Attachment> getAttachments(Application application){
        return attachmentRepository.findAllByApplication(application);
    }
}
