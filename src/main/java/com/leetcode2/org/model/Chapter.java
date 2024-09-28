package com.leetcode2.org.model;

public class Chapter {
     private  int id;
     private  String name;
     private String pubY;

    public Chapter(int id, String name, String pubY) {
        this.id = id;
        this.name = name;
        this.pubY = pubY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPubY() {
        return pubY;
    }

    public void setPubY(String pubY) {
        this.pubY = pubY;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pubY='" + pubY + '\'' +
                '}';
    }
}
