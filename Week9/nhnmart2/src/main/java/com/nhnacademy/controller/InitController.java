package com.nhnacademy.controller;

import com.nhnacademy.domain.BuyList;
import com.nhnacademy.domain.Food;
import com.nhnacademy.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class InitController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        FoodStand foodStand = new FoodStand();
        BuyList buyList = new BuyList();

        ServletContext servletContext = req.getServletContext();

        String product1 = (String) servletContext.getAttribute("product1"); // onion-1000-2
        String temp1 [] = product1.split("-");
        String product2 = (String) servletContext.getAttribute("product2");
        String temp2 [] = product2.split("-");
        String product3 = (String) servletContext.getAttribute("product3");
        String temp3 [] = product3.split("-");
        String product4 = (String) servletContext.getAttribute("product4");
        String temp4 [] = product4.split("-");

        for(int i = 0; i < Integer.parseInt(temp1[2]); i++) {
            foodStand.add(new Food(temp1[0], Integer.parseInt(temp1[1])));
        }
        for(int i = 0; i < Integer.parseInt(temp2[2]); i++) {
            foodStand.add(new Food(temp2[0], Integer.parseInt(temp2[1])));
        }
        for(int i = 0; i < Integer.parseInt(temp3[2]); i++) {
            foodStand.add(new Food(temp3[0], Integer.parseInt(temp3[1])));
        }
        for(int i = 0; i < Integer.parseInt(temp4[2]); i++) {
            foodStand.add(new Food(temp4[0], Integer.parseInt(temp4[1])));
        }

//        log.info("foodStand={}", foodStand);

        req.setAttribute("foodStand", foodStand);
        servletContext.setAttribute("foodStand", foodStand);
        servletContext.setAttribute("buyList", buyList);
        servletContext.setAttribute("money", 20000);

        return "/completeInit.jsp";
    }
}
