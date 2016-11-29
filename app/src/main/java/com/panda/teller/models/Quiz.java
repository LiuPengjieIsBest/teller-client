package com.panda.teller.models;

/**
 * Created by root on 16-11-20.
 */

public class Quiz {

    int id;

    int userId;

    String title;

    String content;

    int visitCount;

    int answerCount;

    int limitTime; /* /秒 */

    long originTime;

    long updateTime;

    public int getId() {
        return id;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public long getOriginTime() {
        return originTime;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public void setOriginTime(long originTime) {
        this.originTime = originTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
