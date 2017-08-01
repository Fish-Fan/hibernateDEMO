package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by yanfeng-mac on 2017/7/31.
 */
public class MyTest {

    @org.junit.Test
    public void first() {
        //读取配置文件
        Configuration configuration = new Configuration().configure();

        //创建SessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //创建session
        Session session = sessionFactory.getCurrentSession();

        //开启事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Account account = new Account();
        account.setUsername("王思聪");
        account.setAddress("北京");
        account.setAge(23);

        //save方法返回插入的主键
//        Object id = session.save(account);

        //persist方法没有返回值，但是会将主键自动映射至属性中
//        session.persist(account);
//        System.out.println("id->" + account.getId());

        //saveOrUpdate方法在执行时进行判断，如果传入的对象为自由态进行插入操作，若为游离态则进行更新操作
//        session.saveOrUpdate(account);

        //merge方法不改变对象的状态，传入的对象如果为自由态会进行插入操作
        session.merge(account);
        transaction.commit();

    }

    @org.junit.Test
    public void update() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        //持久态
        Account account = (Account) session.get(Account.class,6);

        //游离态
        transaction.commit();

        Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        //仍然为游离态
        account.setUsername("土巴兔");

        session1.merge(account);
        session1.getTransaction().commit();

    }

    //get和load都会优先从缓存中查询数据

    @org.junit.Test
    public void findById() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        //使用get方法进行查询,如果主键为不存在，返回null
        //get执行完毕之后立即向数据库发送SQL语句
        Account account = (Account) session.get(Account.class,4);

        transaction.commit();
        System.out.println(account.getId());

    }

    @Test
    public void findByIdWithLoad() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        //使用load方法进行查询,检索不到抛出ObjectNotFound异常
        //account执行完毕之后仅仅包含主键，主键之外的内容一概不知，也就是不立即向数据库发送SQL语句
        //只有当使用到除主键之外的属性时，才会向数据库发送SQL语句进行查询
        Account account = (Account) session.load(Account.class,9);

        transaction.commit();
        System.out.println(account.getId());
    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Account account = (Account) session.get(Account.class,7);
        session.delete(account);

        transaction.commit();
    }

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        //from 后面跟持久化类名
        String hql = "from Account";
        Query query = session.createQuery(hql);

        //分页查询
        query.setFirstResult(1);
        query.setMaxResults(3);

        List<Account> accountList = query.list();
        System.out.println(accountList);

        transaction.commit();
    }

    @Test
    public void findByUserName() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        String hql = "from Account as ac where ac.username = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name","王思聪");

        List<Account> accountList = query.list();
        System.out.println(accountList);

        transaction.commit();
    }


    @Test
    public void Select() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        String hql = "select id,username,address from Account";
        Query query = session.createQuery(hql);

        //查询出来的每行数据使用数组进行接收
        List<Object[]> objectList = query.list();
        for(Object[] objects : objectList) {
            System.out.println("id->" + objects[0] + "username->" + objects[1] + "address->" + objects[2]);
        }

        transaction.commit();
    }

    @Test
    public void findByUsername() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        String hql = "from Account where username = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name","王思聪");

        List<Account> accountList = query.list();
        System.out.println(accountList);

        transaction.commit();
    }

    @Test
    public void findByUsernameDesc() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();

        String hql = "from Account where username = :name order by age desc";
        Query query = session.createQuery(hql);
        query.setParameter("name","王思聪");

        List<Account> accountList = query.list();
        System.out.println(accountList);

        transaction.commit();
    }
}
