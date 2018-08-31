package com.fta.androidroom.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class Address {

//    @PrimaryKey
//    public int id;

    public String street;
    public String state;

    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
