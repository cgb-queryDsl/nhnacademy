package com.nhnacademy.board.controller.user.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 등록 페이지
 * - 아이디
 * - 패스워드
 * - 이름
 * - 파일
 *
 * 입력
 */
public class UserCreateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/user/userCreate.jsp";
    }
}
