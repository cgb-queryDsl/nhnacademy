package com.nhnacademy.servlet;

import java.util.Optional;
import javax.servlet.ServletContext;

public class CounterUtils {
    private CounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext) {
        Integer count = Optional.ofNullable((Integer) servletContext.getAttribute("counter")).orElse(0);
        servletContext.setAttribute("counter", ++count);
    }

}
