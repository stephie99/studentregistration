package com.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.jpa.Student;
import com.spring.jpa.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class EnrollmentController {

    //@Autowired
    //private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpSession session) {
        //String username = principal.getName();
        String StudentId = (String) session.getAttribute("userId").toString();
        Long studentId = Long.parseLong(StudentId);

        Student student = studentRepository.findById(studentId).orElse(null);
        
        if (student != null) {
            model.addAttribute("student", student); 
            model.addAttribute("studentId", student.getStudentId());
        } else {
            model.addAttribute("errorMessage", "User not found.");
        }
        
        return "profile"; 
    }


    @PostMapping("/profile")
    public String updateProfile(@RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("address") String address,
                                @RequestParam("city") String city,
                                @RequestParam("postalCode") String postalCode,
                                //@ModelAttribute Student student,
                                @RequestParam("userId") Long userId,
                                //@RequestParam("student") Student student,
                                HttpSession session,
                                 Model model) {
        //Long studId = student.getStudentId();
        Student student = studentRepository.findById(userId).orElse(null);
        if ((userName != null) && (password != null) && (firstName != null) 
            && (lastName != null) && (address != null) && (city != null) && (postalCode!= null)) {
            student.setUserName(userName);
            student.setPassword(password);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setAddress(address);
            student.setCity(city);
            student.setPostalCode(postalCode);

            studentRepository.save(student);
        }

        model.addAttribute("successMessage", "Profile updated successfully!");
        //return "profile"; 
        session.setAttribute("userId", userId);

        return "redirect:/updatesuccess";
    }

    @GetMapping("/updatesuccess")
    public String showConfirmationPage(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("userId").toString();
        model.addAttribute("studentId", studentId);
        return "updatesuccess"; 
    }

    @PostMapping("/updatesuccess")
    public String updaeSuccess (@RequestParam("userId") String studentId, HttpSession session, Model model) {
        session.setAttribute("userId", studentId);
        return "redirect:/students/programs";
    }
}
