package com.nhnacademy.servlet;

import com.nhnacademy.servlet.food.BuyList;
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

@Slf4j
public class CartServlet extends HttpServlet {

    private ServletContext servletContext;
    private BuyList buyList = new BuyList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletContext = getServletContext();
        FoodStand foods = (FoodStand) servletContext.getAttribute("foods");

        String[] values1 = req.getParameterValues("onion");
        String[] values2 = req.getParameterValues("egg");
        String[] values3 = req.getParameterValues("greenOnion");
        String[] values4 = req.getParameterValues("apple");

        // 양파
        if (values1 != null) {
            for(int i = 0; i < values1.length; i++) {
                String temp [] = values1[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
            }
        }

        // 달걀
        if (values2 != null) {
            for(int i = 0; i < values2.length; i++) {
                String temp [] = values2[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
            }
        }

        // 파
        if (values3 != null) {
            for(int i = 0; i < values3.length; i++) {
                String temp [] = values3[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
            }
        }

        // 사과
        if (values4 != null) {
            for (int i = 0; i < values4.length; i++) {
                String temp [] = values4[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
            }
        }

        for(BuyList.Item item : buyList.getItems()) {
            foods.remove(item);
        }

        servletContext.setAttribute("buyList", buyList);

        resp.sendRedirect("/cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session)) {
            // 세션이 없으면 로그인화면
            resp.sendRedirect("/loginForm.html");
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");

            BuyList buyList = (BuyList) servletContext.getAttribute("buyList");

            PrintWriter out = resp.getWriter();

            int totalPrice = 0;

            out.println("<html>");
            out.println("<head>");
            out.println("<title>NHN Mart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>구매 목록</h1>");

            for(BuyList.Item item : buyList.getItems()) {
                out.println("<p>" + item.getName() + "</p>");
                totalPrice += item.getPrice();
            }
            out.println("<hr/>");

            out.println("<h1>총 가격</h1>");
            out.println("<p>" + totalPrice + "원 </p>");

            out.println("</body>");
            out.println("</head>");
            out.println("</html");
        }

    }
}
