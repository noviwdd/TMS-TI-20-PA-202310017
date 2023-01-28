package com.ibik.ledent.ledent.programstudy;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibik.ledent.ledent.program.Program;

@Entity
@Table(name="programstudy")
public class ProgramStudy implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(length = 20)
    private String description;

    @Column(length = 5)
    @NotEmpty(message = "Code is required")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    // @JsonIgnore
    private Program program;

    @OneToMany
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @NotEmpty(message = "Faculty is required")
    @JsonIgnore
    private List<ProgramStudy> Faculty = new ArrayList<>();

    // @Column(length = 11)
    // @Min(value = 1, message = "Department Id is required")
    // private int department_id;

    @Column(nullable = false, columnDefinition = "TINYINT(4)")
    private boolean is_active;

    public ProgramStudy() {
    }

    public ProgramStudy(int id, String name, String description, String code, Program program, List<ProgramStudy> faculty, int department_id, boolean is_active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.program = program;
        this.Faculty = faculty;
        // this.department_id = department_id;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<ProgramStudy> getFaculty() {
        return this.Faculty;
    }

    public void setFaculty(List<ProgramStudy> faculty) {
        this.Faculty = faculty;
    }

    // public int getDepartment_id() {
    //     return department_id;
    // }

    // public void setDepartment_id(int department_id) {
    //     this.department_id = department_id;
    // }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
