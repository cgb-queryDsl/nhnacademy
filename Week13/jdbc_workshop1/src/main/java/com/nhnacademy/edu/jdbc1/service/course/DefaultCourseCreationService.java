package com.nhnacademy.edu.jdbc1.service.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCourseCreationService implements CourseCreationService {

    private final CourseRepository courseRepository;

    public DefaultCourseCreationService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getALlCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public void registerCourse(String teacherName, String subjectName) {
        courseRepository.createCourse(teacherName, subjectName);
    }

    @Override
    public void updateCourse(Long courseId, Long teacherId, String teacherName, Long subjectId, String subjectName) {
        courseRepository.updateCourse(courseId, teacherId, teacherName, subjectId, subjectName);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteCourse(id);
    }
}
