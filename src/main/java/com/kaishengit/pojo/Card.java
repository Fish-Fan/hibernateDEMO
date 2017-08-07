package com.kaishengit.pojo;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GenericGenerator(name = "generator",strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property",value = "person"))
    @GeneratedValue(generator = "generator")
    private Integer id;

    @Column(name = "card_num")
    private String cardNum;


    //共享主键关联使用PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "card")
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
