package com.panda.teller.models;

/**
 * Created by root on 16-11-13.
 */

public class Video {

    int id;

    int userId;

    String visibility;

    String title;

    String intro;

    String content;

    float price;

    int length;

    int visitCount;

    int likeCount;

    String videoCover;

    /* 协调数据库-go-java的计时 */
    long time;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTitle() {

        return title;
    }

    public String getIntro() {
        return intro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public int getId() {

        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getContent() {
        return content;
    }

    public float getPrice() {
        return price;
    }

    public int getLength() {
        return length;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public long getTime() {
        return time;
    }

    public String getVideoCover() {
        return videoCover;
    }
}
