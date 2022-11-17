package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {

    private long id;
    private String name;
    private String email;
    private int score;
    private String comment;

    public static Student createStudent(String name, String email, int score, String comment) {
        return new Student(name, email, score, comment);
    }

    private Student(String name, String email, int score, String comment) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public Student(long id, String name, String email, int score, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }
}
