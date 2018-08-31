package com.fta.androidroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fta.androidroom.room.Address;
import com.fta.androidroom.room.AddressTwo;
import com.fta.androidroom.room.AppDatabase;
import com.fta.androidroom.room.Book;
import com.fta.androidroom.room.MyDao;
import com.fta.androidroom.room.NameTuple;
import com.fta.androidroom.room.User;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "android_room";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @BindView(R.id.showUserInfo)
    TextView mShowUsereInfo;

    @BindView(R.id.showBookInfo)
    TextView mShowBookInfo;

    @OnClick(R.id.create_db)
    void createDb() {
        new Thread() {
            @Override
            public void run() {
                super.run();
//                createDb();
                insertUser();
//                insertUserAndBook();
                showInfo();

            }
        }.start();


    }

    @OnClick(R.id.find_by_firstname)
    void findByFirstName() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                queryByFirstName();
                showInfo();

            }
        }.start();

    }

    @OnClick(R.id.add_book_to_user2)
    void addBookToUser2() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                insertBook();
                showInfo();
            }
        }.start();
    }

    @OnClick(R.id.load_user)
    void loadUsers() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                queryFromUser();
                showInfo();

            }
        }.start();
    }

    @OnClick(R.id.delete_user)
    void deleteUser() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                deleteFromUser();

                showInfo();
            }
        }.start();
    }

    @OnClick(R.id.add_user_book)
    void addUserAndBook() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                insertUserAndBook();

                showInfo();
            }
        }.start();
    }

    private MyDao createMyDb() {
        AppDatabase instance = AppDatabase.getInstance(this);
        return instance.userDao();
    }


    public void insertUserAndBook() {
        createMyDb().insertUserAndBook(creeateUser(), createBook());

    }

    public void insertBook() {
        createMyDb().insertBook(createBook());
    }

    public void insertUser() {
        long rowId = createMyDb().insertUser(creeateUser2());
        Log.i(TAG, "MainActivity-insertUser: 添加行数（>0 为成功）：" + rowId);
    }

    private User creeateUser2() {
        User user = new User();
//        user.id = 0;
        Address address = new Address();
        address.city = "北京";
        address.postCode = 119;
        address.state = "什么状态啊";
        address.street = "这是朝阳区";
        user.address = address;

        AddressTwo addressTwo = new AddressTwo();
        addressTwo.city = "北京";
        addressTwo.street = "这是海淀区";
        addressTwo.postCode = 118;
        addressTwo.state = "北京的状态";
        user.addressTwo = addressTwo;

        user.age = 28;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse("1991-12-22");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.birthday = date;

        user.firstName = "时尚";

        user.lastName = "古人";

        user.region = "河北省保定市";

        user.addName = "添加名字";
        return user;

    }

    private User creeateUser() {
        User user = new User();
//        user.id = 0;
        Address address = new Address();
        address.city = "天津";
        address.postCode = 109;
        address.state = "状态啊";
        address.street = "西青区";
        user.address = address;

        AddressTwo addressTwo = new AddressTwo();
        addressTwo.city = "北京";
        addressTwo.street = "朝阳区";
        addressTwo.postCode = 108;
        addressTwo.state = "北京状态";
        user.addressTwo = addressTwo;

        user.age = 18;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse("1991-12-22");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.birthday = date;

        user.firstName = "lei";

        user.lastName = "jiao";

        user.region = "河北省衡水市";
        return user;

    }

    private int bookNum = 0;
    private int bookId = 0;

    private Book createBook() {
        bookNum++;
        bookId++;
        Book book = new Book();
        book.bookId = bookId;
        book.title = "书名" + bookNum;
        book.userId = 2;
        Log.i(TAG, "MainActivity-createBook: 书的数量：" + bookNum + "=书的id=" + bookId);
        return book;
    }

    private void queryByFirstName() {
        User user = createMyDb().findUserByFirstName("jiao");
        if (user != null) {
            Log.i(TAG, "queryByFirstName: is not null");
            Log.i(TAG, "queryByFirstName: last name =" + user.lastName);
        } else {

            Log.i(TAG, "queryByFirstName: is null");
        }
    }

    private void queryFromUser() {
        List<NameTuple> nameTuples = createMyDb().loadFullName();
        for (int i = 0; i < nameTuples.size(); i++) {
            Log.i(TAG, "queryFromUser: firstname=" + nameTuples.get(i).firstName + "=lastname=" + nameTuples.get(i).lastName);
        }
    }

    private void deleteFromUser() {
        User[] users = createMyDb().loadAllUsers();

        if (users.length >= 2) {
            int deleteUser = createMyDb().deleteUser(users[1]);
            Log.i(TAG, "deleteFromUser: deleteUser=" + deleteUser);
        } else {
            Log.i(TAG, "deleteFromUser: length is < 2");
        }

//        User user = new User();
//        user.firstName = "lei";
//        int deleteUser = createMyDb().deleteUser(user);
//        Log.i(TAG, "deleteFromUser: deleteUser=" + deleteUser);
    }


    private void showUserInfo() {
        List<User> users = createMyDb().findUsers();
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            stringBuilder.append("\n用户表：")
                    .append("\n用户信息：").append(users.get(i).toString())
                    .append("\n用户地址1：").append(users.get(i).address.toString())
                    .append("\n用户地址2：").append(users.get(i).addressTwo.toString());
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShowUsereInfo.setText(stringBuilder.toString());
            }
        });


    }


    private void showBookInfo() {
        List<Book> books = createMyDb().findBooks();
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < books.size(); i++) {
            stringBuilder.append("\n书表：")
                    .append("\n书信息：").append(books.get(i).toString());

        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mShowBookInfo.setText(stringBuilder.toString());
            }
        });

    }

    private void showInfo() {
        showUserInfo();
        showBookInfo();
    }

}
