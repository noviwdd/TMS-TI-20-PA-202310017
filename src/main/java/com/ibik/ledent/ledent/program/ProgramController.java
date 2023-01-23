package com.ibik.ledent.ledent.program;

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

import com.ibik.ledent.ledent.dto.ResponseData;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramServices programServices;

    @PostMapping
    public ResponseEntity<ResponseData<Program>> postProgram(@Valid @RequestBody Program program, Errors errors) {
        ResponseData<Program> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setResult(true);
        List<Program> value = new ArrayList<>();
        value.add(programServices.save(program));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Program>> fetchProgram() {
        ResponseData<Program> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<Program> value = (List<Program>) programServices.findAll();
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setResult(false);
            responseData.getMessage().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Program>> fetchProgramById(@PathVariable("id") int id) {
        ResponseData<Program> responseData = new ResponseData<>();

        try {
            responseData.setResult(true);
            List<Program> value = new ArrayList<>();
            value.add(programServices.findOne(id));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setResult(false);
            responseData.getMessage().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Program>> updateProgram(@Valid @RequestBody Program program, Errors errors) {
        ResponseData<Program> responseData = new ResponseData<>();

        if(program.getId() != 0) {
            if(errors.hasErrors()) {
                for(ObjectError error : errors.getAllErrors()) {
                    responseData.getMessage().add(error.getDefaultMessage());
                }
                responseData.setResult(false);
                responseData.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
            responseData.setResult(true);
            List<Program> value = new ArrayList<>();
            value.add(programServices.save(program));
            responseData.setData(value);
            return ResponseEntity.ok(responseData);
        } else {
            responseData.setResult(false);
            responseData.getMessage().add("ID is required");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteProgramById(@PathVariable("id") int id) {
        ResponseData<Void> responseData = new ResponseData<>();

        try {
            programServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setResult(false);
            responseData.getMessage().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
}
