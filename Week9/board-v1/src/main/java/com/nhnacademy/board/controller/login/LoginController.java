package com.nhnacademy.board.controller.login;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class LoginController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = req.getServletContext();

        // 관리자
        User admin = (User) servletContext.getAttribute("admin");
        // 유저
        UserRepository repository = (UserRepository) servletContext.getAttribute("repository");
        Map<String, User> users = repository.getUsers();

        // jsp 에서 넘어온 아이디 패스워드
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        boolean loginCheck = false;
        // 관리자 로그인
        if(id.equals(admin.getId()) && password.equals(admin.getPassword())) {
            servletContext.setAttribute("adminLogin", "admin-login-on");
            loginCheck = true;
        } else {
            for(String key : users.keySet()) {
                if(users.get(key).getId().equals(id) && users.get(key).getPassword().equals(password)) {
                    servletContext.setAttribute("userLogin", users.get(key).getName() + "-login-on");
                    loginCheck = true;
                    break;
                } else {
                    loginCheck = false;
                }
            }
        }

        if(loginCheck){
            log.info("loginStatus = {}", servletContext.getAttribute("userLogin"));
            return "/login/loginSuccess.jsp";
        } else {
            return "/login/loginFail.jsp";
        }
    }
}
