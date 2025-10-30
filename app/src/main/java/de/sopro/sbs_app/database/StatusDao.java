package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.Project;
import de.sopro.sbs_app.model.Status;

@Dao
public interface StatusDao {

    @Query("SELECT * FROM status")
    List<Status> findAll();

    @Query("SELECT * FROM status WHERE status=:status")
    Status findByName(String status);

    @Query("SELECT * FROM status WHERE status_FK=:name")
    List<Status> findSuccessors(String name);

    @Insert
    void insertOne(Status status);

    @Insert
    void insertAll(List<Status> status);

    @Update
    void updateOne(Status status);

    @Update
    void updateAll(List<Status> status);

    @Delete
    void deleteOne(Status status);

    @Delete
    void deleteAll(List<Status> status);

}
