package com.kaishengit.pojo;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class PassageContent {
    private Integer id;
    private String content;

    //双向一对一外键关系
    private Passage passage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Passage getPassage() {
        return passage;
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
    }
}
