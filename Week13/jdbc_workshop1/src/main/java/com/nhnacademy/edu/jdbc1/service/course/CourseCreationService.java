package com.nhnacademy.edu.jdbc1.service.course;

import java.util.List;

public interface CourseCreationService {
    List<Course> getALlCourse();
    Course getCourse(Long id);
    void registerCourse(String teacherName, String subjectName);
    void updateCourse(Long courseId, Long teacherId, String teacherName, Long subjectId, String subjectName);
    void deleteCourse(Long id);
}
