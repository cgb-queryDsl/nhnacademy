package com.nhnacademy.servlet;

import com.nhnacademy.servlet.food.Food;
import com.nhnacademy.servlet.food.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인이 안되어있으면 로그인 폼으로
        HttpSession session = req.getSession(false);
        FoodStand foodStand = new FoodStand();

        if(Objects.isNull(session)) {
            // 세션이 없으면 로그인화면
            resp.sendRedirect("/loginForm.html");
        } else {
            if(foodStand.getFoods().size() != 0) {
                foodStand.getFoods().clear();
            }

            // 양파
            String onion[] = getServletContext().getInitParameter("onion").split("-");
            for(int i = 0; i < Integer.parseInt(onion[1]); i++) {
                foodStand.add(new Food("onion", Integer.parseInt(onion[0])));
            }

            // 달걀
            String egg[] = getServletContext().getInitParameter("egg").split("-");
            for(int i = 0; i < Integer.parseInt(egg[1]); i++) {
                foodStand.add(new Food("egg", Integer.parseInt(egg[0])));
                resp.getWriter();
            }

            // 파
            String greenOnion[] = getServletContext().getInitParameter("greenOnion").split("-");
            for(int i = 0; i < Integer.parseInt(greenOnion[1]); i++) {
                foodStand.add(new Food("greenOnion", Integer.parseInt(greenOnion[0])));
            }

            // 사과
            String apple[] = getServletContext().getInitParameter("apple").split("-");
            for(int i = 0; i < Integer.parseInt(apple[1]); i++) {
                foodStand.add(new Food("apple", Integer.parseInt(apple[0])));
            }

            ServletContext servletContext = getServletContext();

            servletContext.setAttribute("foods", foodStand);

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<head><title>NNH Mart</title></head>");
            out.println("<body>");
            out.println("<h3>Init Completed!!</h3>");
            out.println("<a href=\"/foods\">Go Foods List </a>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
