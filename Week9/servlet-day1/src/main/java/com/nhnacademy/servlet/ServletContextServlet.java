package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServletContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        ServletContext servletContext = getServletConfig().getServletContext();

        resp.setContentType("text/plain");

        try(PrintWriter out = resp.getWriter()) {
            out.println(servletContext.getContextPath());
            out.println(servletContext.getMajorVersion());
            out.println(servletContext.getMinorVersion());
            out.println(servletContext.getEffectiveMajorVersion());
            out.println(servletContext.getEffectiveMinorVersion());
            out.println(servletContext.getRealPath("/servletcontext"));
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

}
