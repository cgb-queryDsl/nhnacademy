package com.nhnacademy.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentRegisterFormServlet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/studentRegister.jsp";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // FrontServlet 에서 처리해줌
////        resp.setContentType("text/html");
////        resp.setCharacterEncoding("UTF-8");
////
////        RequestDispatcher rd = req.getRequestDispatcher("/studentRegister.jsp");
////        rd.forward(req, resp);
//
//        // front 와 연결역할만
//        req.setAttribute("view", "/studentRegister.jsp");
//    }
}
