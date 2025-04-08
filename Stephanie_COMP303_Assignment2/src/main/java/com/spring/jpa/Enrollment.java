package com.spring.jpa;

import jakarta.persistence.*;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo; // Primary Key

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student; // Foreign Key to Student

    @ManyToOne
    @JoinColumn(name = "programCode", nullable = false)
    private Program program; // Foreign Key to Program

    private String startDate;
    private double amountPaid;
    private String status;

    // Getters and Setters
    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Enrollment() {}
    public Enrollment(Program program, Student student){
        this.program = program;
        this.student = student;
    }
}
