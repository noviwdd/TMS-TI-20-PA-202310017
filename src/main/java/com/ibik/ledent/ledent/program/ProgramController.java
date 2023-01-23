package com.ibik.ledent.ledent.program;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramServices programServices;

    @PostMapping
    public Program postProgram(@Valid @RequestBody Program program, Errors errors) {
        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }

            throw new RuntimeException("Validation Error");
        }
        return programServices.save(program);
    }

    @GetMapping
    public Iterable<Program> fetchProgram() {
        return programServices.findAll();
    }

    @GetMapping("/{id}")
    public Program fetchProgramById(@PathVariable("id") int id) {
        return programServices.findOne(id);
    }

    @PutMapping
    public Program updateProgram(@RequestBody Program program) {
        return programServices.save(program);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramById(@PathVariable("id") int id) {
        programServices.removeOne(id);
    }
}
