package com.nhnacademy.edu.springboot.student.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private Long id;

    private String name;
    private Integer score;
}
