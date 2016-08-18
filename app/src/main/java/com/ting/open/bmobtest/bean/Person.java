package com.ting.open.bmobtest.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by lt on 2016/8/17.
 */
public class Person extends BmobObject {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

