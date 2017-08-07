package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "person_name")
    private String personName;

    //共享主键关联使用PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
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
