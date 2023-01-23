package com.ibik.ledent.ledent.program;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProgramServices {

    @Autowired
    private ProgramRepo programRepo;

    public Program save(Program program) {
        return programRepo.save(program);
    }

    public Program findOne(int id) {
        return programRepo.findById(id).get();
    }

    public Iterable<Program> findAll() {
        return programRepo.findAll();
    }

    public void removeOne(int id) {
        programRepo.deleteById(id);
    }
}
