package com.nhnacademy.controller;

import com.nhnacademy.domain.FoodStand;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class FoodController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
        FoodStand foodStand = (FoodStand) context.getAttribute("foodStand");

        if(foodStand == null) {
            return "/initRequest.jsp";
        }

        return "/foods.jsp";
    }
}
