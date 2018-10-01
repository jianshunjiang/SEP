package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Draft;
import com.loan.uts.model.Email;
import com.loan.uts.model.Student;
import com.loan.uts.repository.ApplicationRepository;
import com.loan.uts.repository.DraftRepository;
import com.loan.uts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.loan.uts.model.Application.SUBMITTED;

/**
 * This service deal with the operations that a student might conduct.
 */
@Transactional
@Service("studentService")
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    DraftRepository draftRepository;
    @Autowired
    EmailService emailService;

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

    /**
     * Submit new application in the database and notify the student.
     * @param application
     */
    public Application submitApplication(Application application){
        Integer id = applicationRepository.save(application).getId();
        Email email = new Email( id,application.getTitle(), application.getStudent(), SUBMITTED);
        emailService.sendEmail(email);
        return applicationRepository.findOne(id);
    }

    /**
     * Get the historical applications that is in a month.
     * @param student
     * @param timeStr
     * @return
     */
    public Set<Application> searchByMonth(Student student, String timeStr){
        return applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNotNullAndSubmitDate(
                        student.getId(),timeStr);
    }

    /**
     * Get the historical applications that has some title.
     * @param student
     * @param title
     * @return
     */
    public Set<Application> searchByTitle(Student student, String title){
        return applicationRepository
                .getApplicationsByStudentIdAndResultDateIsNotNullAndTitleContaining(student.getId(), title);
    }

    /**
     * Save the draft.
     * @param student
     * @param draft
     */
    public Draft saveDraft(Student student, Draft draft){
        if (student.getDraft() != null) draft.setId(student.getDraft().getId());
        Draft savedDraft = draftRepository.save(draft);
        student.setDraft(savedDraft);
        studentRepository.save(student);
        return savedDraft;
    }

    /**
     * Delete the draft.
     * @param draft
     */
    public void deleteDraft(Draft draft){
        draftRepository.delete(draft);
    }

}
