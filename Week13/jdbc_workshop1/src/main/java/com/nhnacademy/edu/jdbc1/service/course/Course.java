package com.nhnacademy.edu.jdbc1.service.course;

import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;

import java.util.Date;

public class Course {
    private final Long id;
    private final Teacher teacher;
    private final Subject subject;
    private final Date createAt;

    public Course(Long id, Teacher teacher, Subject subject, Date createAt) {
        this.id = id;
        this.teacher = teacher;
        this.subject = subject;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public Date getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", createAt=" + createAt +
                '}';
    }
}
