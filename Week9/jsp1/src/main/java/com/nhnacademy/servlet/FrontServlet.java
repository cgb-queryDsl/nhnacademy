package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect:";

    // 모든 메서드에 대해서 처리를 해야 하기 때문에 service 로 구현
    // doGet, doPost, doDelete ... 다 구현하기엔 힘드니
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try {
            // 실제 요청 처리할 Servlet 결정.
            Command command = resolveServlet(req.getServletPath());

            // 실제 요청을 처리할 Servlet으로 요청을 전달하여 처리 결과를 include시킴.
            // ex) /student/register, /student/view
//            RequestDispatcher rd = req.getRequestDispatcher(processingServletPath);
//            rd.include(req, resp);

            String view = command.execute(req, resp);

            // 실제 요청을 처리한 Servlet이 `view`라는 request 속성 값으로 view를 전달해 줌.
//            String view = (String) req.getAttribute("view");
            if (view.startsWith(REDIRECT_PREFIX)) {
                // `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()));
            } else {
                // redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        } catch (Exception ex) {
            // 에러가 발생한 경우는 error page로 지정된 `/error.jsp`에게 view 처리를 위임.
            log.error("", ex);
            req.setAttribute("exception", ex);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }

    }

    private Command resolveServlet(String servletPath) {
        Command command = null;

        /**
         * .do 는 사용자가 요청을 보내는 것
         * .do가 없는 것은 실제(브라우저)로 보내는 것
         */
        if("/student/registerForm.do".equals(servletPath)) {
            command = new StudentRegisterFormServlet();
        } else if ("/student/register.do".equals(servletPath)) {
            command = new StudentRegisterServlet();
        } else if ("/student/view.do".equals(servletPath)) {
            command = new StudentViewServlet();
        }

        /**
         * //TODO
         * nhn mart 참고
         */
//        if ("/cart.do".equals(servletPath)) {
//            processingServletPath = "/cart";
//        } else if ("/foods.do".equals(servletPath)) {
//            processingServletPath = "/foods";
//        } else if ("/login.do".equals(servletPath)) {
//            processingServletPath = "/login";
//        } else if ("/logout.do".equals(servletPath)) {
//            processingServletPath = "/logout";
//        } else if ("/change-lang.do".equals(servletPath)) {
//            processingServletPath = "/change-lang";
//        }

        return command;
    }
}
