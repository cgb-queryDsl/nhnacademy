package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentRequestModify;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
import com.nhnacademy.springmvc.validator.StudentRegisterModifyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final StudentRegisterModifyValidator validator;

    public StudentController(StudentRepository studentRepository, StudentRegisterModifyValidator validator) {
        this.studentRepository = studentRepository;
        this.validator = validator;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException(studentId);
        }

        return student;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        model.addAttribute("student", studentRepository.getStudent(studentId));
        return "studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore")
    public String viewStudentHideScore(@PathVariable("studentId") Long studentId, ModelMap modelMap,
                                       @RequestParam(value = "hideScore", required = false) String hideScore) {
        modelMap.addAttribute("student", studentRepository.getStudent(studentId));

        if (hideScore.equals("yes")) {
            return "studentViewHideScore";
        }

        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable("studentId") Long studentId, Model model) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException(studentId);
        }

        model.addAttribute("student", studentRepository.getStudent(studentId));
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyUser(@Validated @ModelAttribute StudentRequestModify studentRequestModify,
                             @PathVariable("studentId") Long studentId,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = studentRepository.getStudent(studentId);
        student.setName(studentRequestModify.getName());
        student.setEmail(studentRequestModify.getEmail());
        student.setScore(studentRequestModify.getScore());
        student.setComment(studentRequestModify.getComment());

        return "studentView";
    }

    @InitBinder("studentRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(StudentNotFoundException ex) {
        log.error("error = {}", ex);
    }
}
