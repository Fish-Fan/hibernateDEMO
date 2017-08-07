package com.kaishengit.hibernate;


import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import org.hibernate.PersistentObjectException;
import org.junit.*;
import org.junit.Test;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class OneToOne extends BaseTest{

    @org.junit.Test
    public void save() {
        Person person = new Person();
        person.setPersonName("Lucy");

        Card card = new Card();
        card.setCardNum("xxxx5");

        //必须相互设置属性
        person.setCard(card);
        card.setPerson(person);

        //保存person的同时，会自动保存card
//        session.save(person);
        session.save(card);
    }

    @Test
    public void find() {



        //测试双向查询

        Person person = (Person) session.get(Person.class,27);
        System.out.println(person.getPersonName());
        System.out.println(person.getCard().getCardNum());

        System.out.println("---------------------------");

        Card card = (Card) session.get(Card.class,27);

        System.out.println(card.getCardNum());
//        Person person1 = card.getPerson();
//        System.out.println(card.getPerson().getPersonName());
    }

}
