package com.nhnacademy.board.controller.post.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostRegisterFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        // 게시글을 작성하기 위해서는 로그인이 필요함
        String userLoginStatus = (String) servletContext.getAttribute("userLogin");

        if(userLoginStatus.equals("off")) {
            return "/login/loginRequest.jsp";
        } else {
            return "/post/postRegister.jsp";
        }
    }
}
