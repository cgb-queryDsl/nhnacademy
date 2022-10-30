package com.nhnacademy.board.domain;

import java.time.LocalDateTime;

public class Posts implements Post {
    private static long count = 1;
    private long id;
    private String title;
    private String content;
    private String writeUserId;
    private LocalDateTime writeTime;
    private int viewCount;

    public Posts(String title, String content, String writeUserId, LocalDateTime writeTime) {
        this.id = count++;
        this.title = title;
        this.content = content;
        this.writeUserId = writeUserId;
        this.writeTime = writeTime;
        this.viewCount = 0;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getWriterUserId() {
        return this.writeUserId;
    }

    @Override
    public void setWriterUserId(String writerUserId) {
        this.writeUserId = writerUserId;
    }

    @Override
    public LocalDateTime getWriteTime() {
        return this.writeTime;
    }

    @Override
    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public int getViewCount() {
        return this.viewCount;
    }

    @Override
    public void increaseViewCount() {
        this.viewCount++;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writeUserId='" + writeUserId + '\'' +
                ", writeTime=" + writeTime +
                ", viewCount=" + viewCount +
                '}';
    }
}
