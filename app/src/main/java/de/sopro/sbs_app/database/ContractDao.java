package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.Contract;
import de.sopro.sbs_app.model.Project;

@Dao
public interface ContractDao {

    @Query("SELECT * FROM contracts")
    List<Contract> findAll();

    @Query("SELECT * FROM contracts WHERE id=:id")
    Contract findById(Long id);

    @Query("SELECT * FROM billingunits WHERE contract_FK=:id")
    List<BillingUnit> findBillingUnitsByContractId(Long id);

    @Query("SELECT * FROM contracts WHERE project_FK=:id")
    List<Contract> findContractsByProjectId(Long id);

    @Insert
    void insertOne(Contract contarct);

    @Insert
    void insertAll(List<Contract> contarcts);

    @Update
    void updateOne(Contract contarct);

    @Update
    void updateAll(List<Contract> contarcts);

    @Delete
    void deleteOne(Contract contarct);

    @Delete
    void deleteAll(List<Contract> contarcts);
}
