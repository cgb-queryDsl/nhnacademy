package com.nhnmart.cs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Answer {
    String answerContent;
    String answerTime;
    String admin;

    public void registerAnswer(String answerContent, String answerTime, String admin) {
        this.answerContent = answerContent;
        this.answerTime = answerTime;
        this.admin = admin;
    }
}
