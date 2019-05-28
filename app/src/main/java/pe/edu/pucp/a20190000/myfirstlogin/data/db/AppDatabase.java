package pe.edu.pucp.a20190000.myfirstlogin.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pe.edu.pucp.a20190000.myfirstlogin.data.db.dao.UserDao;
import pe.edu.pucp.a20190000.myfirstlogin.data.db.entities.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "myfirstlogin.db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context applicationContext) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(applicationContext, AppDatabase.class,
                    DB_NAME).build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
