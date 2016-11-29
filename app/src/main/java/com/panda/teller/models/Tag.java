package com.panda.teller.models;

/**
 * Created by root on 16-11-20.
 */

public class Tag {

    int id;

    String name;

    int level;

    /* 表明希望服务器添加还是删除该tag */
    boolean remove;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isRemove() {
        return remove;
    }

    public int getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
