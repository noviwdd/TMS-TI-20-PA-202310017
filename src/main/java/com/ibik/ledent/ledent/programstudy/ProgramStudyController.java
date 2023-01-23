package com.ibik.ledent.ledent.programstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/programstudy")
public class ProgramStudyController {
    
    @Autowired
    private ProgramStudyServices programStudyServices;

    @PostMapping
    public ProgramStudy posPrograms(@RequestBody ProgramStudy programStudy) {
        return programStudyServices.save(programStudy);
    }

    @GetMapping
    public Iterable<ProgramStudy> fetchProgram() {
        return programStudyServices.findAll();
    }

    @GetMapping("/{id}")
    public ProgramStudy fetchProgramById(@PathVariable("id") int id) {
        return programStudyServices.findOne(id);
    }

    @PutMapping
    public ProgramStudy updateProgram(@RequestBody ProgramStudy programStudy) {
        return programStudyServices.save(programStudy);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramById(@PathVariable("id") int id) {
        programStudyServices.removeOne(id);
    }
}
