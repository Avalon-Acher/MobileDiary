package com.example.mobilediary.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 连浩逵 on 2017/2/15.
 */
public class Diary extends DataSupport {
    private int id;
    private String title;//标题
    private String connect;//内容
    private int connectCount;//总字数
    private String alterDate;//最近修改日期
    private String readDate;//最近查看日期
    private String author;//作者 PK
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public int getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(int connectCount) {
        this.connectCount = connectCount;
    }

    public String getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(String alterDate) {
        this.alterDate = alterDate;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
