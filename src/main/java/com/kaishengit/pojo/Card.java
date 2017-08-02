package com.kaishengit.pojo;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class Card {
    private Integer id;
    private String cardNum;

    private Person person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
