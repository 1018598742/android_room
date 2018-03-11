package com.fta.androidroom.room;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class NameTuple {

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;

}
