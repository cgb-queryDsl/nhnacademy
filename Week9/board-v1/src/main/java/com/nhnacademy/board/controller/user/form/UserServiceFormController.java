package com.nhnacademy.board.controller.user.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 서비스 페이지 컨트롤러
 * - 등록
 * - 삭제
 * - 삭제
 * - 조회
 */
public class UserServiceFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();
        String adminLogin = (String) servletContext.getAttribute("adminLogin");

        if(adminLogin.contains("on")) {
            return "/user/userAllService.jsp";
        } else {
            return "/noAuthority.jsp";
        }
    }
}
