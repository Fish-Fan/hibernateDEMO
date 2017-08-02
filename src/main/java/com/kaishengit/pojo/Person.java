package com.kaishengit.pojo;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class Person {
    private Integer id;
    private String personName;

    private Card card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


}
