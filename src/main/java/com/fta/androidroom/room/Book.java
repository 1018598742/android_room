package com.fta.androidroom.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Administrator on 2018/3/10 0010.
 * indices = {@Index("user_id")}设置索引
 */

@Entity(indices = {@Index(value = "user_id", unique = true)},
        foreignKeys = {@ForeignKey(entity = User.class,
                onDelete = ForeignKey.CASCADE,
                parentColumns = "id",
                childColumns = "user_id")})
public class Book {

    @PrimaryKey
    @ColumnInfo(name = "book_id")
    public int bookId;

    public String title;

    @ColumnInfo(name = "user_id")
    public int userId;

}
