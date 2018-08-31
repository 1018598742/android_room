package com.fta.androidroom.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
@Database(entities = {User.class, Book.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class MemoryAppDatabase extends RoomDatabase {
    public abstract MyDao userDao();

    private static MemoryAppDatabase INSTANCE;
    private static final Object sLock = new Object();

    public static MemoryAppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                        MemoryAppDatabase.class,
//                        "aboutuser.db")
//                        .addMigrations(MIGRATION_1_2)
//                        .build();
                //创建内存数据库，程序销毁，数据库销毁
                INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), MemoryAppDatabase.class).build();
            }
            return INSTANCE;
        }
    }


    //一下为升级时用到的代码（迁移）
    public static void setMigration(Context context) {
        Room.databaseBuilder(context.getApplicationContext(), MemoryAppDatabase.class, "aboutuser.db")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();
    }


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
            database.execSQL("ALTER TABLE users ADD COLUMN add_name TEXT");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Book "
                    + " ADD COLUMN pub_year INTEGER");
        }
    };

}
