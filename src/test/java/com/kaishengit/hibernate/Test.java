package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;

import java.util.List;
import java.util.Set;

public class Test {

    @org.junit.Test
    public void first() {
        //读取配置文件(从classpath中读取名称为hibernate.cfg.xml的配置文件)
        Configuration configuration = new Configuration().configure();
        //创建SessionFactory
        //SessionFactory sessionFactory = configuration.buildSessionFactory();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //创建Session
        Session session = sessionFactory.getCurrentSession();
        //开启事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Account account = new Account();
        account.setUsername("王思雨");
        account.setAddress("北京");
        account.setAge(23);

        session.save(account);//merge=saveOrUpdate 不改变对象状态

        //session.clear(); //将session关联的所有对象从session中清除

        account.setUsername("王老五");
        session.flush();


        //关闭事务（提交 | 回滚）
        transaction.commit();// session.close();

    }

    @org.junit.Test
    public void updateAccount() {

        String id = "ff8080815da710b0015da710b3310000";

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Account account = (Account) session.get(Account.class,id); //持久态

        session.getTransaction().commit(); // 游离态

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        account.setUsername("李老八"); //游离态
        session2.merge(account); // 再次 持久态

        session2.getTransaction().commit(); //再次 游离态


    }



    @org.junit.Test
    public void save() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Account account = new Account();
        account.setUsername("王思雨");
        account.setAddress("北京");
        account.setAge(23);

        session.persist(account);
        System.out.println(account.getId());


        session.getTransaction().commit();
    }


    @org.junit.Test
    public void findById() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Account account = (Account) session.get(Account.class,1);
        //System.out.println(account.getUsername());
        session.getTransaction().commit();

        System.out.println("-----------------------------------");

        Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        Account account1 = (Account) session1.get(Account.class,1);
        System.out.println(account.getUsername());
        session1.getTransaction().commit();




    }

    @org.junit.Test
    public void findByIdWithLoad() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Account account = (Account) session.load(Account.class,22);
        //System.out.println(account.getUsername());

        System.out.println(account.getUsername());

        session.getTransaction().commit();



        //System.out.println(account.getUsername());
    }



    @org.junit.Test
    public void update() {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Account account = (Account) session.get(Account.class,1);
        account.setUsername("张三丰1");

        session.getTransaction().commit();
    }

    @org.junit.Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Account account = (Account) session.get(Account.class,1);
        session.delete(account);


        session.getTransaction().commit();
    }

    @org.junit.Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from Account where username = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"tom");

        List<Account> accountList = query.list();

        for(Account account : accountList) {
            System.out.println(account.getUsername() + " -> " + account.getAddress());
        }
        session.getTransaction().commit();
    }

    @org.junit.Test
    public void uuidTest() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String uuid = "402881c15da6b88d015da6b890090000";
        Account account = (Account) session.get(Account.class,uuid);

        System.out.println(account.getUsername());
    }

    @org.junit.Test
    public void goodLockTest() throws InterruptedException {
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();

        final String id = "ff8080815da710b0015da710b3310000";
        Account account = (Account) session.get(Account.class,id);

        Thread thread = new Thread(new Runnable() {
            public void run() {


                //另外一个session对account操作
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Account account1 = (Account) session1.get(Account.class,id);

                account1.setUsername("赵四");
                session1.update(account1);

                session1.getTransaction().commit();
            }
        });

        thread.start();

        Thread.sleep(3000);

        //最开始的session对account进行修改

        //最开始的session对对象进行更新操作时,version已经被后一个session更新了，该种情况下会抛出org.hibernate.StaleObjectStateException异常
        account.setUsername("王麻子");
        session.getTransaction().commit();
    }

    @org.junit.Test
    public void badLockTest() throws InterruptedException {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        final String id = "ff8080815da710b0015da710b3310000";
        //对该行加上悲观锁
        Account account = (Account) session.get(Account.class,id, LockOptions.UPGRADE);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Account account1 = (Account) session1.get(Account.class,id);

                account1.setUsername("李建国");

                session1.getTransaction().commit();
                System.out.println("名称已变更为李建国");
            }
        });

        thread.start();

        Thread.sleep(3000);

        account.setUsername("李远超");
        session.getTransaction().commit();
        System.out.println("名称已变更为李远超");

        //最终程序执行结果为李建国
        //子线程一直在等待主线程执行完毕，当主线程将名字修改为李远超后，悲观锁失效，子线程将名称修改为李建国

    }

}
