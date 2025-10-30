package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import de.sopro.sbs_app.model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    User find();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

}
