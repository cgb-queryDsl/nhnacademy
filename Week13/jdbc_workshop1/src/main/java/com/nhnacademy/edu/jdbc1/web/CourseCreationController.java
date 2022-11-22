package com.nhnacademy.edu.jdbc1.web;

import com.nhnacademy.edu.jdbc1.config.MainConfig;
import com.nhnacademy.edu.jdbc1.service.course.Course;
import com.nhnacademy.edu.jdbc1.service.course.CourseCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Controller
public class CourseCreationController {
    private final CourseCreationService courseCreationService;

    public CourseCreationController(CourseCreationService courseCreationService) {
        this.courseCreationService = courseCreationService;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/course")
    public String course(Model model) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);
            List<Course> allCourseList = bean.getALlCourse();
            model.addAttribute("allCourseList", allCourseList);
            return "course";
        }
    }

    @GetMapping("/course/create")
    public String courseCreateForm() {
        return "courseCreateForm";
    }

    @PostMapping("/course/create")
    public String courseCreate(@RequestParam("teacherName") String teacherName,
                               @RequestParam("subjectName") String subjectName) throws SQLException {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);
            bean.registerCourse(teacherName, subjectName);

            return "redirect:/course";
        }
    }

//    @GetMapping("/course/delete")
//    public String courseDeleteForm() {
//        return "courseDeleteForm";
//    }

    @PostMapping("/course/delete")
    public String courseDelete(@RequestParam("id") String id, Model model) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);

            bean.deleteCourse(Long.valueOf(id));
            List<Course> allCourseList = bean.getALlCourse();
            model.addAttribute("allCourseList", allCourseList);

            return "redirect:/course";
        }

    }


    @PostMapping("/course/update")
    public String courseUpdate(@RequestParam("id") Long id, Model model) throws SQLException {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);

            // TODO : 하나 강의 가져와서 모델에 넘기기
            Course course = bean.getCourse(id);
            model.addAttribute("course", course);


            return "courseUpdateForm";
        }
    }

    @PostMapping("/course/update/success")
    public String courseUpdate(@RequestParam("id") Long courseId,
                               @RequestParam("teacherId") Long teacherId,
                               @RequestParam("teacherName") String teacherName,
                               @RequestParam("subjectId") Long subjectId,
                               @RequestParam("subjectName") String subjectName,
                               Model model) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class)) {
            CourseCreationService bean = ctx.getBean(CourseCreationService.class);
            bean.updateCourse(courseId, teacherId, teacherName, subjectId, subjectName);

            List<Course> allCourseList = bean.getALlCourse();
            model.addAttribute("allCourseList", allCourseList);

            return "redirect:/course";
        }
    }
}
