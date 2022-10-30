package com.nhnacademy.board.controller.user.service;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.domain.UserRepository;
import com.nhnacademy.board.domain.Users;
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
@WebServlet(name = "userCreateController", urlPatterns = "/users/user/create/success")
public class UserCreateController extends HttpServlet {

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

        repository.add(new Users(
                req.getParameter("id"),
                req.getParameter("password"),
                req.getParameter("name"),
                fileName
        ));

        log.info("User 추가 후 repository={}", repository);
        resp.sendRedirect("/user/userCreateSuccess.jsp");
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
