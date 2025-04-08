package com.spring.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//import javax.persistence.Entity;
//import javax.persistence.Id;

@Entity
public class Program {
    
    @Id
    private String programCode;
    private String duration;
    private int fee;
    private String programName;

    // Getters and Setters
    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}

