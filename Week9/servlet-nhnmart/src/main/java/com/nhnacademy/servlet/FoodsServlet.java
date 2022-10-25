package com.nhnacademy.servlet;

import com.nhnacademy.servlet.food.FoodStand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FoodsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        FoodStand foods = (FoodStand) servletContext.getAttribute("foods");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>NNH 마트</title></head>");
        out.println("<body>");

        // 바디 구성
        out.println("<h1>NHN 마트</h1>");
        out.println("<a href=\"/logout\"> LOGOUT </a>");
        out.println("<hr/>");

        out.println("<form method=\"post\" action=\"/cart\">");

        for(int i = 0; i < foods.getFoods().size(); i++) {
            String foodName = foods.getFoods().get(i).getName();
            int foodPrice = foods.getFoods().get(i).getPrice();

            String foodEnName = "";

            out.println("<input type=\"checkbox\" name=\"" + foodName + "\" value=\"" + foodName +"-" + foodPrice + "\"> " + foodName +"-" + foodPrice + "<br/>" );
        }

        out.println("<input type=\"submit\">");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}
