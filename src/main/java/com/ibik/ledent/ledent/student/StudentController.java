package com.ibik.ledent.ledent.student;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.ledent.ledent.student.dto.ResponseData;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentServices studentServices;

    @PostMapping
    // public Student posPrograms (@Valid @RequestBody Student student, Errors errors) { 
    public ResponseEntity<ResponseData<Student>> posPrograms(@Valid @RequestBody Student student, Errors errors) {
        ResponseData<Student> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                // System.out.println(error.getDefaultMessage());
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

            // throw new RuntimeException("Validation Error");
        }

        responseData.setResult(true);
        List<Student> value = new ArrayList<>();
        value.add(studentServices.save(student));
        responseData.setData(student);
        return ResponseEntity.ok(responseData);
        // return studentServices.save(student);
    }

    @GetMapping
    public Iterable<Student> fetchProgram() {
        return studentServices.findAll();
    }

    @GetMapping("/{id}")
    public Student fetchProgramById(@PathVariable("id") int id) {
        return studentServices.findOne(id);
    }

    @PutMapping
    public Student updateProgram(@Valid @RequestBody Student student, Errors errors) {
        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            throw new RuntimeException("Validation Error");
        }
        return studentServices.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramById(@PathVariable("id") int id) {
        studentServices.removeOne(id);
    }
}
