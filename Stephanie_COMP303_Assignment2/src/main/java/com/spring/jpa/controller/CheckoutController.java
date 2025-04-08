package com.spring.jpa.controller;

import com.spring.jpa.Program;
import com.spring.jpa.Student;
import com.spring.jpa.Enrollment;
import com.spring.jpa.repository.EnrollmentRepository;
import com.spring.jpa.repository.ProgramRepository;
import com.spring.jpa.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CheckoutController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam("programCode") String programCode, 
                                    @RequestParam("userId") Long userId,
                                    Model model) {
        
        Program program = programRepository.findById(programCode).orElse(null); 
        Student student = studentRepository.findById(userId).orElse(null);
        
        
        if (program == null) {
            model.addAttribute("errorMessage", "Program not found.");
            return "error"; 
        }
        
        model.addAttribute("program", program); 
        model.addAttribute("studentId", student.getStudentId());
        return "checkout"; 
    }

    @PostMapping("/checkout")
    public String processPayment(@RequestParam("creditCardNumber") String creditCardNumber,
                                  @RequestParam("expiryDate") String expiryDate,
                                  @RequestParam("cvv") String cvv,
                                  @RequestParam("programCode") String programCode,
                                  @RequestParam("userId") Long userId,
                                  HttpSession session,
                                  //@ModelAttribute Enrollment enrollment,
                                  Model model) {
        
        boolean paymentSuccessful = false;
        if (creditCardNumber.matches("\\d{16}") || expiryDate.matches("\\d{4}") && cvv.matches("\\d{3}")){
            paymentSuccessful = true;
        }

        Date currDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String currentDateTime = dateFormat.format(currDate);
        Program program = programRepository.findById(programCode).orElse(null); 
        Student student = studentRepository.findById(userId).orElse(null);
                
        if (paymentSuccessful) {
            Enrollment enrollment = new Enrollment(program, student);
            enrollment.setAmountPaid(program.getFee());
            enrollment.setStartDate(currentDateTime);
            enrollment.setStatus("Enrolled");
            enrollmentRepository.save(enrollment);
            session.setAttribute("userId", userId);
            return "redirect:/confirmation";
        } else {
            model.addAttribute("errorMessage", "Payment failed. Please try again.");
            return "checkout"; 
        }
    }


    @GetMapping("/confirmation")
    public String showConfirmationPage(HttpSession session, Model model) {
        String studentId = (String) session.getAttribute("userId").toString();
        model.addAttribute("studentId", studentId);
        return "confirmation"; 
    }

    @PostMapping("/confirmation")
    public String backProfile (@RequestParam("userId") String studentId, HttpSession session, Model model) {
        session.setAttribute("userId", studentId);
        return "redirect:/students/programs";
    }
}
