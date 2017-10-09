package com.example.shundai.recyclerviewdemo;

import java.io.Serializable;

/**
 * Created by win7 on 2017/10/9.
 */

public class News implements Serializable {
    private int type;
    private String content;

    public News(int type, String content) {

        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
