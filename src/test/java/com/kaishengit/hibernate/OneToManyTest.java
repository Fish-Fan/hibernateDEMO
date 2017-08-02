package com.kaishengit.hibernate;


import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yanfeng-mac on 2017/8/1.
 */
public class OneToManyTest extends BaseTest {

    @org.junit.Test
    public void save() {

        User user = new User();
        user.setUsername("李晓明");

        Address address = new Address();
        address.setCityName("北京");
        address.setAddress("王府井大街");


        address.setUser(user);

        //保存时，先保存一再保存多
        //否则会多增加一条update语句
        session.save(user);
        session.save(address);


    }

    @Test
    public void save2() {
        //该做法不推荐

        User user = new User();
        user.setUsername("王麻子");

        Address address = new Address();
        address.setCityName("上海");
        address.setAddress("南京路");

        Set<Address> addressSet = new HashSet<Address>();
        addressSet.add(address);
        user.setAddressSet(addressSet);

        //会产生三条SQL语句(额外多了一条update address语句)
        //因为user需要去维护addressSet的状态，而新增加的address使其状态发生了改变，故需要update
        session.save(user);
        session.save(address);
    }

    @Test
    public void delete() {

        User user = (User) session.get(User.class,8);
        session.delete(user);

    }

    @Test
    public void saveNewAddress() {
        User user = (User) session.get(User.class,1);

        Address address = new Address();
        address.setCityName("南京");
        address.setAddress("南京外环一号线");
        address.setUser(user);

        session.save(address);
    }

    @Test
    public void findByAddressId() {

        //按照Address.hbm.xml配置时，会在查询时只产生一条SQL语句(连接查询)
        Address address = (Address) session.get(Address.class,1);
        System.out.println(address.getAddress());
        System.out.println(address.getUser().getUsername());

    }

    @Test
    public void findAddressByUserId() {

        Criteria criteria = session.createCriteria(Address.class);
        criteria.createAlias("user","u");
        criteria.add(Restrictions.eq("u.id",1));

        List<Address> addressList = criteria.list();
        for(Address address : addressList) {
            System.out.println(address.getId() + "->" + address.getCityName() + " -> " + address.getAddress());
        }
    }

    @Test
    public void findUserById() {
        User user = (User) session.get(User.class,1);

        System.out.println(user.getUsername());

        //该阶段产生的SQL语句没有任何连接，仅仅只是where userId = 1
        Set<Address> addressSet  = user.getAddressSet();
        for(Address address : addressSet) {
            System.out.println(address.getId() + "->" + address.getCityName() + " -> " + address.getAddress());
        }
    }
}
