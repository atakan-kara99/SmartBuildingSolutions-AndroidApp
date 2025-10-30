package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.ConstructionProgress;

@Dao
public interface ConstructionProgressDao {

    @Query("SELECT * FROM constructionprogress WHERE id=:id")
    ConstructionProgress findById(Long id);

    @Query("SELECT * FROM constructionprogress WHERE billingItem_FK=:id")
    List<ConstructionProgress> findConstructionProgressByBillingItemId(Long id);

    @Insert
    void insertOne(ConstructionProgress constructionProgress);

    @Insert
    void insertAll(List<ConstructionProgress> constructionProgress);

    @Update
    void updateOne(ConstructionProgress constructionProgress);

    @Update
    void updateAll(List<ConstructionProgress> constructionProgresses);

    @Delete
    void deleteOne(ConstructionProgress constructionProgress);

    @Delete
    void deleteAll(List<ConstructionProgress> constructionProgress);

    @Query("SELECT * FROM constructionprogress")
    List<ConstructionProgress> findAll();

}
