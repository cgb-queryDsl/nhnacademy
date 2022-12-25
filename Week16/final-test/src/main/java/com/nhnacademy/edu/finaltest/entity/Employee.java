package com.nhnacademy.edu.finaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private String id;

    private String name;

    @JoinColumn(name = "department_code")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
}
