package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.junit.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by yanfeng-mac on 2017/8/1.
 */
public class CriteriaTest {
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
    public void findAll() {
        Criteria criteria = session.createCriteria(Account.class);

        List<Account> accountList = criteria.list();
        System.out.println(accountList);
    }

    @Test
    public void findByUsername() {
        Criteria criteria = session.createCriteria(Account.class);

        //where筛选条件
        criteria.add(Restrictions.eq("username","tom"));
//        criteria.add(Restrictions.lt("id",4));
//        criteria.add(Restrictions.le("id",4));
//        criteria.add(Restrictions.in("id",new Integer[]{2,3,4}));
//        criteria.add(Restrictions.like("username","王",MatchMode.ANYWHERE));
//        criteria.add(Restrictions.or(Restrictions.eq("username","王思聪"),Restrictions.eq("age",21)));

        //多重限制条件可用disjunction将条件分离出来逐个填写
//        Disjunction disjunction = Restrictions.disjunction();
//        disjunction.add(Restrictions.eq("username","王思聪"));
//        disjunction.add(Restrictions.eq("age",21));
//
//        criteria.add(disjunction);

        List<Account> accountList = criteria.list();

        //返回结果为单条数据时可以用uniqueResult接受
//        Account accountList = (Account) criteria.uniqueResult();

        System.out.println(accountList);
    }

    @Test
    public void page() {
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("address","上海"));

        //限制查询个数
        criteria.setFirstResult(0);
        criteria.setMaxResults(3);

        //排序
        //先按照username进行倒序排列，然后将username相同的按照id进行倒序排列
        criteria.addOrder(Order.desc("username"));
        criteria.addOrder(Order.desc("id"));



        List<Account> accountList = criteria.list();
        System.out.println(accountList);
    }

    @Test
    public void count() {
        Criteria criteria = session.createCriteria(Account.class);
//        criteria.setProjection(Projections.count("id"));
        //相当于select count(distinct username) from account
//        criteria.setProjection(Projections.countDistinct("username"));
        //相当于count(*)
//        criteria.setProjection(Projections.rowCount());


        //查询多个聚合函数使用projectionList
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.countDistinct("username"));

        criteria.setProjection(projectionList);

        //查询出来的是单条数据多列数据，故用数组进行接收
        Object[] array = (Object[]) criteria.uniqueResult();

        System.out.println("count(*)-> " + array[0] + " count(distinct username)-> " + array[1]);
    }
}
