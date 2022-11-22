package com.nhnacademy.edu.jdbc1.web;

import com.nhnacademy.edu.jdbc1.DBUtils;
import com.nhnacademy.edu.jdbc1.config.MainConfig;
import com.nhnacademy.edu.jdbc1.service.course.Course;
import com.nhnacademy.edu.jdbc1.service.course.CourseCreationService;
import com.nhnacademy.edu.jdbc1.service.course.DefaultCourseCreationService;
import com.nhnacademy.edu.jdbc1.service.login.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

    private static final String SESSION = "session";
    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("pwd") String pwd,
                        HttpSession session, Model model) throws SQLException {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);
            Connection connection = DBUtils.getDataSource().getConnection();

            if (userLoginService.login(connection, username, pwd)) {
                session.setAttribute(SESSION, "ON");
                List<Course> allCourseList = bean.getALlCourse();
                model.addAttribute("allCourseList", allCourseList);

                return "course";
            }
        }

        return "loginFail";
    }
}
