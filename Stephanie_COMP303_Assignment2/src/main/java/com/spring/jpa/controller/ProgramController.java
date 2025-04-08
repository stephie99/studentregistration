package com.spring.jpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.jpa.Program;
import com.spring.jpa.service.ProgramService;

import jakarta.servlet.http.HttpSession;

@Controller

public class ProgramController {

    private static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @Autowired
    private ProgramService programService;

    @GetMapping("/login")
    public String showPrograms(//@RequestParam("userId") String userId,
                                HttpSession session, Model model) {
        List<Program> programs = programService.getAllPrograms();
        logger.info("Fetched programs: {}", programs); 
        model.addAttribute("programs", programs);
        String studentId = (String) session.getAttribute("userId");
        model.addAttribute("studentId", studentId);
        return "programs"; 
    }
}