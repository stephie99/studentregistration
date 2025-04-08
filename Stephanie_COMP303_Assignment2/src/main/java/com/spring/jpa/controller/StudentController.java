package com.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

import com.spring.jpa.Student;
import com.spring.jpa.repository.StudentRepository;
import com.spring.jpa.service.ProgramService;

@Controller

public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private ProgramService programServie;
    
    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/?success";
    }

    @GetMapping("/students")
    public String viewStudents(Model model) {
        List<Student> students = studentRepository.findAll();  
        model.addAttribute("students", students);              
        return "student-list";                                
    }
    
    @GetMapping("/students/programs")
    public String listPrograms(HttpSession session, Model model) {
        model.addAttribute("programs", programServie.getAllPrograms());
        String studentId = (String) session.getAttribute("userId").toString();
        model.addAttribute("studentId", studentId);
        return "programs"; 
    } 
    
}
