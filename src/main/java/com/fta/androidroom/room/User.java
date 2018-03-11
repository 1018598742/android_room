package com.fta.androidroom.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
@Entity(tableName = "users",indices = {@Index(value = {"first_name","last_name"},unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public int age;

    public String region;

    public Date birthday;


    @Embedded(prefix = "first")
    public Address address;

    @Embedded(prefix = "second")
    public AddressTwo addressTwo;

    @Ignore
    public Bitmap picture;
}
