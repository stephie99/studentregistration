package com.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.jpa.Student;
import com.spring.jpa.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {

    @Autowired
    private StudentRepository studentRepository;


    @PostMapping("/login")
    public String loginStudent(@RequestParam("userName") String userName, 
                               @RequestParam("password") String password,
                               //RedirectAttributes redirectAttributes, 
                               HttpSession session,
                               Model model) {
        Student student = studentRepository.findByUserName(userName);
        if (student != null && student.getPassword().equals(password)) {           
            //return "programs"; // Redirect to program selection page
        	//return "redirect:/programs";
            //model.addAttribute("student", student);
            //redirectAttributes.addAttribute("userId", student.getStudentId());
            session.setAttribute("userId", student.getStudentId());
            return "redirect:/students/programs";
            //return "programs";
        } else {
      
            model.addAttribute("errorMessage", "Invalid username or password!");
            return "index"; 
        }
    }


}
