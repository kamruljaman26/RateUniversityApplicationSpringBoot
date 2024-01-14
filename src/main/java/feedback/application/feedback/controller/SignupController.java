package feedback.application.feedback.controller;

import feedback.application.feedback.model.Student;
import feedback.application.feedback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showSignupForm() {
//        model.addAttribute("student", new Student());
        return "signup";
    }

    @PostMapping
    public String registerStudent(@ModelAttribute Student student) {
        studentService.registerStudent(student);
        return "redirect:/login"; // Redirect to login page after successful registration
    }
}

