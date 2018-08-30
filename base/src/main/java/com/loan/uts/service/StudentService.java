package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Student;
import com.loan.uts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("studentService")
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student login(Integer studentId, String password){
        return studentRepository.findByIdAndPassword(studentId, password);
    }

    public Student login(String studentId, String password){
        Integer id = Integer.parseInt(studentId);
        return login(id, password);
    }

//    public List<Application> getApplication(Integer id){
//
//    }
}
