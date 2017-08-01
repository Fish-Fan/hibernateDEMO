package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.*;

import java.util.List;

/**
 * Created by yanfeng-mac on 2017/8/1.
 */
public class NativeTest {
    private Session session;

    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }

    @org.junit.Test
    public void demo() {
//
//        String sql = "select id,username,address,age from account";
//        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Account.class);

        //带参查询
//        String sql = "select id,username,address,age from account where username = :name";
//        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Account.class);
//        sqlQuery.setParameter("name","tom");

        //特殊查询
        String sql = "select id,username,address,age from account";
        SQLQuery sqlQuery = session.createSQLQuery(sql);

        List<Object[]> accountList = sqlQuery.list();

        for(Object[] arr: accountList) {
            Account account = new Account();
            account.setId(Integer.valueOf(arr[0].toString()));
            account.setUsername(arr[1].toString());
            account.setAddress(arr[2].toString().equals("") ? "默认地址" : arr[2].toString());
            account.setAge(Integer.valueOf(arr[3] == null ? "0" : arr[3].toString()));
            System.out.println(account);
            System.out.println("------------------------");
        }



    }
}
