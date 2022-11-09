package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        CounterUtils.increaseCounter(getServletContext());

        try (PrintWriter out = resp.getWriter()) {
            out.println("url=" + getServletContext().getInitParameter("url"));
            out.println("counter=" + getServletContext().getAttribute("counter"));
        } catch (IOException ex) {
            log.error("", ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String[] arr = req.getParameterValues("class");

        try (PrintWriter out = resp.getWriter()) {
            //out.println(arr);
            out.println(String.join(",", arr));
        } catch (IOException ex) {
            log.error("", ex);
        }
    }

}
