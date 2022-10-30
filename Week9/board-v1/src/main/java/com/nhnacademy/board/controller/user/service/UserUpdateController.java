package com.nhnacademy.board.controller.user.service;

import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Slf4j
@MultipartConfig(
        location = "/tmp/",
        maxFileSize = -1L,
        maxRequestSize = -1L,
        fileSizeThreshold = 1024
)
@WebServlet(name = "userUpdateController", urlPatterns = "/users/user/update/check")
public class UserUpdateController extends HttpServlet {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/bakgyeongseo/Desktop";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = "";
        for (Part part : req.getParts()) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if (contentDisposition.contains("filename=")) {
                fileName = extractFileName(contentDisposition);

                if (part.getSize() > 0) {
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            }
        }

        ServletContext servletContext = req.getServletContext();
        UserRepository repository = (UserRepository) servletContext.getAttribute("repository");

        // 저장소에 아이디를 기준으로 아무도 없는 경우
        User updateTempUser = repository.getUser(req.getParameter("id"));
        log.info("getUser = {}", updateTempUser);

        if(updateTempUser == null) {
            resp.sendRedirect("/user/userFail.jsp");
        } else {
            log.info("변경 전 updateUser = {}", updateTempUser);
            updateTempUser.setPassword(req.getParameter("password"));
            updateTempUser.setName(req.getParameter("name"));
            updateTempUser.setProfileFileName(fileName);

            repository.modify(updateTempUser);

            servletContext.setAttribute("repository", repository);
            log.info("변경 후 updateUser = {}", updateTempUser);
            log.info("변경이 반영 된 repository ={}", repository);
            resp.sendRedirect("/user/userUpdateSuccess.jsp");
        }

    }

    private String extractFileName(String contentDisposition) {
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 1).trim().replace("\"", "");
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1);
            }
        }

        return null;
    }
}
