package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init() called");
        super.init(config);
    }

    @Override
    public void destroy() {
        log.info("destroy() called");
        super.destroy();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        log.info("service() called");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        CounterUtils.increaseCounter(getServletContext());

        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");

        resp.setContentType("text/plain");

        try (PrintWriter out = resp.getWriter()) {
            out.println("hello " + title + " " + name);
            out.println("counter=" + getServletContext().getAttribute("counter"));
        } catch (IOException ex) {
            log.error("", ex);
        }
    }

}
