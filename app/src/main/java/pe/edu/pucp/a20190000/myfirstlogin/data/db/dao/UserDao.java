package pe.edu.pucp.a20190000.myfirstlogin.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import pe.edu.pucp.a20190000.myfirstlogin.data.db.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM USER WHERE USERNAME = :username AND PASSWORD = :password")
    User loginOffline(String username, String password);

    @Query("SELECT * FROM USER WHERE USER_ID = :userId LIMIT 1")
    User findById(int userId);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

}

