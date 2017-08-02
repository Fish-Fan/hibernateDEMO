package com.kaishengit.pojo;

/**
 * Created by yanfeng-mac on 2017/8/1.
 */
public class Address {
    private Integer id;
    private String cityName;
    private String address;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }
}
