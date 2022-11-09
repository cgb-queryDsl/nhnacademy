package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NowServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.error("init() called");
        super.init(config);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
        throws ServletException, IOException {
        log.error("service() called");
        super.service(req, res);
    }

    @Override
    public void destroy() {
        log.error("destroy() called");
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        CounterUtils.increaseCounter(getServletContext());

        resp.setContentType("text/html");

        try (PrintWriter out = resp.getWriter()) {
//            out.println(new Date());
            out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));

            out.println("counter=" + getServletContext().getAttribute("counter"));
        } catch (IOException ex) {
            log.error("", ex);
        }
    }

}
