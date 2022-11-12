package com.nhnacademy.servlet;

import com.nhnacademy.domain.Student;
import com.nhnacademy.domain.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")

public class StudentRegisterServlet implements Command {

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // front 에서
////        resp.setCharacterEncoding("UTF-8");
//
//        Student student = new Student(
//            req.getParameter("id"),
//            req.getParameter("name"),
//            req.getParameter("gender"),
//            Integer.parseInt(req.getParameter("age")));
//
//        // StudentRepository or MapStudentRepository
//        // --> StudentRepository 사용해야 함
//        // --> 언제든지 바뀔 수 있기 때문
//        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
//        studentRepository.addStudent(student);
//
//        // redirect : 받은건 post 주는건 get 이기 때문에 forward 보단 redirect
//        // front 에서 해줌
////        resp.sendRedirect("/student/view?id=" + student.getId());
//        req.setAttribute("view", "redirect:/student/view.do?=" + student.getId());
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Student student = new Student(
                request.getParameter("id"),
                request.getParameter("name"),
                request.getParameter("gender"),
                Integer.parseInt(request.getParameter("age")));

        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        studentRepository.addStudent(student);

        return "redirect:/student/view.do?id=" + student.getId();
    }
}
