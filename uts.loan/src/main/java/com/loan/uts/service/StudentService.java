package com.loan.uts.service;

import com.loan.uts.exception.AttachFailException;
import com.loan.uts.model.*;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.AttachmentRepository;
import com.loan.uts.repository.DraftRepository;
import com.loan.uts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Set;

/**
 * This service deal with the operations that a student might conduct.
 */
@Transactional
@Service("studentService")
public class StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    DraftRepository draftRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    AttachmentRepository attachmentRepository;

    /**
     * Search in the database for the student authentication details.
     * @param studentId
     * @param password
     * @return whether there is a student match the student id and password.
     */
    public Student login(Integer studentId, String password){
        return studentRepository.findByIdAndPassword(studentId, password);
    }

    /**
     * Search in the database for the student authentication details.
     * @param studentId
     * @param password
     * @return whether there is a student match the student id and password.
     */
    public Student login(String studentId, String password){
        Integer id = Integer.parseInt(studentId);
        return login(id, password);
    }

    /**
     * Get application for the specific student.
     * @param student
     * @return
     */
    public Set<Application> getApplication(Student student){
        return applicationRepository.getApplicationsByStudentId(student.getId());
    }

    /**
     * Get the applications that are not finished.
     * @param student
     * @return
     */
    public Set<Application> getUnFinishedApplication(Student student){
        return applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNull(student.getId());
    }

    /**
     * Get the applications that are finished.
     * @param student
     * @return
     */
    public Set<Application> getHistoricalApplication(Student student){
        return applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNotNull(student.getId());
    }

    /**
     * Get the student by id.
     * @param id
     * @return
     */
    public Student get(Integer id){
        return studentRepository.findOne(id);
    }

    public void edit(Integer id, String password, String bankaccount, String phone, String faculty, String firstname, String lastname, String course, Date dob, String gender, String nationality, String start_year) {
        Student student = get(id);
        student.setPassword(password);
        student.setBankaccount(bankaccount);
        student.setPhone(phone);
        student.setFaculty(faculty);
        student.setFirstname(firstname);
        student.setLastname(lastname);
        student.setCourse(course);
        student.setDob(dob);
        student.setGender(gender);
        student.setStartYear(start_year);
        studentRepository.saveAndFlush(student);
    }

    /**
     * Submit new application in the database and notify the student.
     * @param application
     */
    public Application submitApplication(Application application, MultipartFile[] attachments, String uploadPath, Integer draftId)
            throws AttachFailException
    {
        if( draftId != null ) deleteDraft(draftId);

        if(attachments != null && attachments.length != 0){
            for(MultipartFile file: attachments){
                if(file.isEmpty()) continue;
                try {
                    String name = application.getStudent().getId() + new java.util.Date().toString() + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + name));
                    Attachment attachment = new Attachment(uploadPath, application, new java.util.Date(), name);
                    attachmentRepository.save(attachment);
                } catch (IOException e) {
                    throw new AttachFailException();
                }
            }
        }

        application = applicationRepository.save(application);
        emailService.notifyStudent(application);

        return application;
    }

    /**
     * Get the historical applications that is in a month.
     * @param student
     * @param timeStr
     * @return
     */
    public Set<Application> searchByMonth(Student student, String timeStr){
        Set<Application> applications = applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNotNullAndSubmitDate(
                        student.getId(),timeStr);
        logger.info("Searched application by date ' " + timeStr + "' result: " + applications.size());
        return applications;
    }

    /**
     * Get the historical applications that has some title.
     * @param student
     * @param title
     * @return
     */
    public Set<Application> searchByTitle(Student student, String title){
        Set<Application> applications = applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNotNullAndTitleContaining(student.getId(), title);
        logger.info("Searched application by title ' " + title + "' result: " + applications.size());
        return applications;
    }

    /**
     * Save the draft.
     * @param student
     * @param draft
     */
    public Draft saveDraft(Student student, Draft draft){
        if (draft.getId() == null && student.getDraft() != null) draft.setId(student.getDraft().getId());
        Draft savedDraft = draftRepository.save(draft);
        student.setDraft(savedDraft);
        studentRepository.save(student);
        logger.info("Draft saved: " + draft.toString());
        return savedDraft;
    }

    /**
     * Delete the draft.
     * @param draft
     */
    public void deleteDraft(Draft draft){
        Student student = draft.getStudent();
        student.setDraft(null);
        studentRepository.save(student);
        logger.info("Draft deleted: " + draft.toString());
        draftRepository.delete(draft.getId());
    }

    /**
     * Delete the draft.
     * @param id
     */
    public void deleteDraft(Integer id){
        deleteDraft(getDraft(id));
        logger.info("Draft deleted: " + id);
    }

    /**
     * Get draft by draft id.
     * @param draftId
     * @return
     */
    public Draft getDraft(Integer draftId){
       return  draftRepository.findOne(draftId);
    }

}
