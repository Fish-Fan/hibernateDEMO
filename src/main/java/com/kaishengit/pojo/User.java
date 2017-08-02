package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created by yanfeng-mac on 2017/8/1.
 */
public class User {
    private Integer id;
    private String username;
    private Set<Address> addressSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }


}
