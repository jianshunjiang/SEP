package com.loan.uts.service;

import com.loan.uts.exception.AttachFailException;
import com.loan.uts.exception.TooManyAppException;
import com.loan.uts.model.*;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.AttachmentRepository;
import com.loan.uts.repository.DraftRepository;
import com.loan.uts.repository.StudentRepository;
import com.loan.uts.util.EncrptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.loan.uts.model.Application.SUBMITTED;

/**
 * This service deal with the operations that a student might conduct.
 */
@Transactional
@Service("studentService")
public class StudentService {

    private static Logger logger = LogManager.getLogger(StudentService.class);

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
        return studentRepository.findByIdAndPassword(studentId, EncrptUtil.encrypt(studentId + password));
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
    public Set<Application> getApplications(Student student){
        return applicationRepository.getApplicationsByStudentId(student.getId());
    }

    /**
     * Get application for the specific student.
     * @return
     */
    public Application getApplication(Integer id){
        return applicationRepository.findOne(id);
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

    /**
     * Update student account information.
     * @param id
     * @param email
     * @param bankaccount
     * @param phone
     * @param faculty
     * @param course
     * @return
     */
    public Student update(Integer id, String email, String bankaccount, String phone, String faculty, String course) {
        Student student = get(id);
        student.setEmail(email);
        student.setBankaccount(bankaccount);
        student.setPhone(phone);
        student.setFaculty(faculty);
        student.setCourse(course);
        studentRepository.saveAndFlush(student);
        return student;
    }

    /**
     * Submit new application in the database and notify the student.
     * @param application
     */
    public Application submitApplication(Application application, MultipartFile[] attachments, String uploadPath)
            throws AttachFailException, TooManyAppException {
        if (getApplicationsByStatus(application.getStudent(), SUBMITTED).size() >= 3) throw new TooManyAppException();
        application = applicationRepository.save(application);
        addAttachments(attachments, uploadPath, application, null, application.getStudent());
        emailService.notifyStudent(application);
        return application;
    }

    /**
     * Get student's applications by status
     * @param student
     * @param status
     * @return
     */
    public Set<Application> getApplicationsByStatus(Student student, String status){
        return applicationRepository.getAllByStudentIdAndStatus(student.getId(), status);
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
    public Draft saveDraft(Student student, Draft draft, MultipartFile[] attachments, String path){
        if (draft.getId() == null && student.getDraft() != null) draft.setId(student.getDraft().getId());
        Draft savedDraft = draftRepository.save(draft);
        student.setDraft(savedDraft);
        studentRepository.save(student);
        try {
            addAttachments(attachments, path, null, draft, student);
        } catch (AttachFailException e) {
            e.printStackTrace();
        }
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
     * Reset student's password.
     * @param id
     * @param password
     * @return
     */
    public Student resetPassword(Integer id, String password){
        Student student = get(id);
        student.setPassword(EncrptUtil.encrypt(id + password));
        studentRepository.save(student);
        return student;
    }

    /**
     * Delete the draft by draft id.
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

    /**
     * Response to a replied application.
     * @param id
     * @param result
     * @param content
     * @param attachments
     * @param uploadPath
     * @throws AttachFailException
     */
    public void responseApplication(Integer id, String result, String content, MultipartFile[] attachments, String uploadPath) throws AttachFailException {
        Application application = getApplication(id);
        application.setStatus(result);
        application.setContent(content);
        application = applicationRepository.save(application);
        addAttachments(attachments, uploadPath, application, null, application.getStudent());
    }

    /**
     * Add attachments to the database and save the file.
     * @param attachments
     * @param uploadPath
     * @param application
     * @throws AttachFailException
     */
    private void addAttachments(MultipartFile[] attachments, String uploadPath, Application application, Draft draft, Student student) throws AttachFailException {
        if(attachments != null && attachments.length != 0){
            for(MultipartFile file: attachments){
                if(file.isEmpty()) continue;
                try {
                    String name = student.getId() + new java.util.Date().toString() + file.getOriginalFilename();
                    file.transferTo(new File(uploadPath + name));
                    Attachment attachment = new Attachment(uploadPath, application, draft, new java.util.Date(), name);
                    attachmentRepository.save(attachment);
                } catch (IOException e) {
                    throw new AttachFailException();
                }
            }
        }
    }
}
