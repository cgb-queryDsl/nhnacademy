package com.nhnacademy.edu.studentopen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String number;
    private Integer balance;
}
