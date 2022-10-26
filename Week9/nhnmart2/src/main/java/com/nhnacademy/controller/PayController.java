package com.nhnacademy.controller;

import com.nhnacademy.domain.BuyList;
import com.nhnacademy.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class PayController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if(Objects.isNull(session)) {
            return "/loginRequest.jsp";
        }

        ServletContext servletContext = req.getServletContext();
        FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodStand");
        BuyList buyList = (BuyList) servletContext.getAttribute("buyList");

        String [] values1 = req.getParameterValues("onion");
        String [] values2 = req.getParameterValues("egg");
        String [] values3 = req.getParameterValues("greenOnion");
        String [] values4 = req.getParameterValues("apple");
        int price = 0;

        if (values1 != null) {
            for(int i = 0; i < values1.length; i++) {
                String temp [] = values1[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
                price += Integer.parseInt(temp[1]);
            }
        }
        // 달걀
        if (values2 != null) {
            for(int i = 0; i < values2.length; i++) {
                String temp [] = values2[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
                price += Integer.parseInt(temp[1]);
            }
        }
        // 파
        if (values3 != null) {
            for(int i = 0; i < values3.length; i++) {
                String temp [] = values3[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
                price += Integer.parseInt(temp[1]);
            }
        }
        // 사과
        if (values4 != null) {
            for (int i = 0; i < values4.length; i++) {
                String temp [] = values4[i].split("-");
                buyList.add(new BuyList.Item(temp[0], Integer.parseInt(temp[1])));
                price += Integer.parseInt(temp[1]);
            }
        }

        int buyLIstTotalPrice = 0;
        boolean check = false;  // false: 돈 부족
        int money = (int) servletContext.getAttribute("money");

        for(BuyList.Item item : buyList.getItems()) {
            buyLIstTotalPrice += item.getPrice();
        }

        if(buyLIstTotalPrice <= money) {
            check = true;
        }

        if(check) {
            for(BuyList.Item item : buyList.getItems()) {
                foodStand.remove(item);
            }
            servletContext.setAttribute("foodStand", foodStand);
            servletContext.setAttribute("buyList", buyList);
            servletContext.setAttribute("buyLIstTotalPrice", buyLIstTotalPrice);
            servletContext.setAttribute("money", money-price);

            return "/pay.jsp";
        } else {
            return "/moneyRequest.jsp";
        }
    }
}
