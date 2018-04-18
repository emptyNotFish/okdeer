package com.okdeer.mall.entity;

/**
 * Created by Administrator on 2018/4/18 0018.
 */
public class UserDemo {


    public UserDemo(String name, String password) {
        this.name=name;
        this.password=password;
    }

    public UserDemo() {
        super();
    }

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "name: "+name+" password: "+password;
    }
}
