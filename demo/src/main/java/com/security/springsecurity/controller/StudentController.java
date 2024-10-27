package com.security.springsecurity.controller;

import com.security.springsecurity.student.Student;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Arun", 60),
            new Student(2, "Anu", 80),
            new Student(3, "Anusha", 90)
    ));

    @GetMapping("/getStudent")
    private List<Student> getStudent() {
        return students;
    }

    @GetMapping("/csrf-token")
    private CsrfToken getCsrfToke(final HttpServletRequest servletRequest) {
        return (CsrfToken) servletRequest.getAttribute("_csrf");
    }

    // whenever we do post,put and delete we need to send csrf token .
    // other ways change the session id for every request and disable it.
    // same-site strict so other site can access it .
    @PostMapping("/getStudent")
    private Student addStudent(@RequestBody final Student student) {
        students.add(student);
        return student;
    }
}
