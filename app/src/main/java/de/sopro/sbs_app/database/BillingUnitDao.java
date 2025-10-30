package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.Project;

@Dao
public interface BillingUnitDao {

    @Query("SELECT * FROM billingunits")
    List<BillingUnit> findAll();

    @Query("SELECT * FROM billingunits WHERE id=:id")
    BillingUnit findById(Long id);

    @Query("SELECT * FROM billingitems WHERE billingUnit_FK=:id")
    List<BillingItem> findBillingItemsByBillingUnitId(Long id);

    @Query("SELECT * FROM billingunits WHERE contract_FK=:id")
    List<BillingUnit> findBillingUnitsByContractId(Long id);

    @Insert
    void insertOne(BillingUnit billingUnit);

    @Insert
    void insertAll(List<BillingUnit> billingUnits);

    @Update
    void updateOne(BillingUnit billingUnit);

    @Update
    void updateAll(List<BillingUnit> billingUnits);

    @Delete
    void deleteOne(BillingUnit billingUnit);

    @Delete
    void deleteAll(List<BillingUnit> billingUnits);

}
