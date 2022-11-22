package com.nhnacademy.edu.jdbc1.service.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CourseRepository {
    List<Course> findAll();
    Course findCourseById(Long id);
    void createCourse(String teacherName, String subjectName);
    void updateCourse(Long courseId, Long teacherId, String teacherName, Long subjectId, String subjectName);
    void deleteCourse(Long id);
}
