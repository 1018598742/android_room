package com.fta.androidroom.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class AddressTwo {

//    @PrimaryKey
//    public int id;

    public String street;
    public String state;

    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
