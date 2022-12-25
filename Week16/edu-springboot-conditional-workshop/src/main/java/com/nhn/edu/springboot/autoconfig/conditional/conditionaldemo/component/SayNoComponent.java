package com.nhn.edu.springboot.autoconfig.conditional.conditionaldemo.component;

public class SayNoComponent {
    private String name;

    public SayNoComponent(String name) {
        this.name = name;
    }

    public void sayYes() {
        System.out.println("NO! " + this.name);
    }
}