package com.nhnmart.cs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Post {
    long id;
    Customer customer;
    String title;
    Category category;
    String content;
    String writeTime;
    String[] files;
    AnswerStatus answerStatus;
    Answer answer;

    public Post(Customer customer, String title, Category category, String content, String writeTime, String[] files) {
        this.customer = customer;
        this.title = title;
        this.category = category;
        this.content = content;
        this.writeTime = writeTime;
        this.files = files;
        this.answerStatus = AnswerStatus.YET;
        this.answer = new Answer();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void answerComplete() {
        this.answerStatus = AnswerStatus.COMPLETE;
    }

    public void updateAnswer(String answerContent, String answerTime, String admin) {
        this.answer.registerAnswer(answerContent, answerTime, admin);
    }
}
