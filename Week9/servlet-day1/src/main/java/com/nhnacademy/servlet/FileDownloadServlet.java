package com.nhnacademy.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/octet-stream");

        String fileName = req.getParameter("fileName");
        resp.setHeader("Content-Disposition", "attachment; filename=" +fileName);

        File file = new File("/Users/bakgyeongseo/Desktop/API_Design_박경서.md");

        try(FileInputStream fileInputStream = new FileInputStream(file)){
            resp.getOutputStream().write(fileInputStream.readAllBytes());
        }

    }
}
