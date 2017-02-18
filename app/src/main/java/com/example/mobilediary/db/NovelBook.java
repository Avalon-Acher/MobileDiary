package com.example.mobilediary.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 连浩逵 on 2017/2/15.
 */
public class NovelBook extends DataSupport {
    private int id;
    private String name;//小说名字 PK
    private String summary;//小说摘要
    private String author;//小说作者 PK

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    private int chapterCount;//小说总章数

}
