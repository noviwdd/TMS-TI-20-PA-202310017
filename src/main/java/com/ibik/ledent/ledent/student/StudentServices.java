package com.ibik.ledent.ledent.student;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentServices {
    
    @Autowired
    private StudentRepo studentRepo;

    public Student save(Student programStudy) {
        return studentRepo.save(programStudy);
    }

    public Student findOne(int id) {
        return studentRepo.findById(id).get();
    }

    public Iterable<Student> findAll() {
        return studentRepo.findAll();
    }

    public void removeOne(int id) {
        studentRepo.deleteById(id);
    }
}
