package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Email;
import com.loan.uts.model.Student;
import com.loan.uts.repository.ApplicationRepository;
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
     * Get the student by id.
     * @param id
     * @return
     */
    public Student get(Integer id){
        return studentRepository.findOne(id);
    }

    /**
     * Submit new application in the database.
     * @param application
     */
    public Integer submitApplication(Application application){
        Integer id = applicationRepository.save(application).getId();
        Email email = new Email(id, application.getStudent(), SUBMITTED);
        emailService.sendEmail(email);
        return id;
    }

}
