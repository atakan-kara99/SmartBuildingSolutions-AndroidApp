package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.ConstructionProgress;

@Dao
public interface BillingItemDao {

    @Query("SELECT * FROM billingitems")
    List<BillingItem> findAll();

    @Query("SELECT * FROM billingitems WHERE id=:id")
    BillingItem findById(Long id);

    @Query("SELECT * FROM billingitems WHERE billingUnit_FK=:id")
    List<BillingItem> findBillingItemsByBillingUnitId(Long id);

    @Query("SELECT * FROM billingitems WHERE billingItem_FK=:id")
    List<BillingItem> findBillingItemsByBillingItemId(Long id);

    @Query("SELECT * FROM constructionprogress WHERE billingItem_FK=:id")
    List<ConstructionProgress> findConstructionProgressesByBillingItemId(Long id);

    @Insert
    void insertOne(BillingItem billingItem);

    @Insert
    void insertAll(List<BillingItem> billingItems);

    @Update
    void updateOne(BillingItem billingItem);

    @Update
    void updateAll(List<BillingItem> billingItems);

    @Delete
    void deleteOne(BillingItem billingItem);

    @Delete
    void deleteAll(List<BillingItem> billingItems);

}
