package com.fta.androidroom.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);//可以将返回值设为List<Long> 或者 Long []

    @Insert
    public void insertBothUsers(User user1, User user2);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertUser(User user);//可以将返回值设为 long 或者 Long,该值是插入新条目的 rowId;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUserAndBook(User user, Book book);


    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBook(Book book);

    @Update
    public void updateUsers(User... users);//可以返回 int，表示受影响的行数

    @Delete
    public void deleteUsers(User... users);//可以返回 int,表示删除的行数

    @Delete
    int deleteUser(User user);

    //@Query的值为SQL语句，可以被SQLite执行。@Query支持查询语句，删除语句和更新语句，不支持插入语句。
    @Query("SELECT * FROM users")
    public User[] loadAllUsers();


    //传入单个参数
    @Query("SELECT * FROM users WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);


    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    public User[] loadAllUsersBetweenAges(int minAge, int maxAge);

    @Query("SELECT * FROM users WHERE first_name LIKE :search "
            + "OR last_name LIKE :search")
    public List<User> findUserWithName(String search);

    @Query("SELECT first_name, last_name FROM users WHERE region IN (:regions)")
    public List<NameTuple> loadUsersFromRegions(List<String> regions);


    @Query("SELECT first_name, last_name FROM users")
    public List<NameTuple> loadFullName();

    @Query("SELECT * FROM users WHERE birthday BETWEEN :from AND :to")
    List<User> findUsersBornBetweenDates(Date from, Date to);

    @Query("SELECT * FROM users WHERE first_name = :firstName")
    User findUserByFirstName(String firstName);


    @Query("SELECT * FROM users")
    List<User> findUsers();

    @Query("SELECT * FROM book")
    List<Book> findBooks();
}
