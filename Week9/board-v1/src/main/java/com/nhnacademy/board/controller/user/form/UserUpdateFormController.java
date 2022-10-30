package com.nhnacademy.board.controller.user.form;

import com.nhnacademy.board.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 수정 서비스
 * - 아이디
 * - 패스워드
 * - 이름
 * - 파일
 *
 * 수정
 */
public class UserUpdateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/user/userUpdate.jsp";
    }
}
