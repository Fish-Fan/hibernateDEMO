package com.kaishengit.pojo;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class Passage {
    private Integer id;
    private String title;
    //单向一对一外键关系
    private PassageContent passageContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PassageContent getPassageContent() {
        return passageContent;
    }

    public void setPassageContent(PassageContent passageContent) {
        this.passageContent = passageContent;
    }
}
