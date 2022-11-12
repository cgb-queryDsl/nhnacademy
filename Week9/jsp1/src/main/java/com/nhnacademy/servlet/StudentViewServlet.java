package com.nhnacademy.servlet;

import com.nhnacademy.domain.Student;
import com.nhnacademy.domain.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")

public class StudentViewServlet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = request.getServletContext();;

        StudentRepository studentRepository = (StudentRepository) servletContext.getAttribute("studentRepository");

        String id = request.getParameter("id");
        Student student = studentRepository.get(id);

        request.setAttribute("student", student);
        if(Objects.isNull(student)) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "/404.jsp";
        } else {
            return "/studentView.jsp";
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = req.getServletContext();
//
//        StudentRepository studentRepository = (StudentRepository) servletContext.getAttribute("studentRepository");
//
//        String id = req.getParameter("id");
//        Student student = studentRepository.get(id);
//
//        if(Objects.isNull(student)) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } else {
//            // front 에서 처리
////            resp.setContentType("text/html");
////            resp.setCharacterEncoding("UTF-8");
//
//            // jsp 에 student 를 넘겨주는 코드
//            // 요청마다 1번 2번 3번 ... 학생의 값이 다 달라짐
//            // 이런 경우에 request 에 Attribute 를 ㄴ준다.
//            req.setAttribute("student", student);
//            req.setAttribute("view", "/studentView.jsp");
//
////            RequestDispatcher rd = req.getRequestDispatcher("/studentView.jsp");
////            rd.forward(req, resp);
//        }
//    }
}
