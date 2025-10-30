package de.sopro.sbs_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.sopro.sbs_app.model.Address;
import de.sopro.sbs_app.model.ConstructionProgress;
import de.sopro.sbs_app.model.Contract;
import de.sopro.sbs_app.model.Project;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM projects")
    List<Project> findAll();

    @Query("SELECT * FROM projects WHERE id=:id")
    Project findById(Long id);

    @Query("SELECT * FROM contracts WHERE project_FK=:id")
    List<Contract> findContractsByProjectId(Long id);

    // Weiß nicht, ob das wirklich so funktioniert!!!
    @Query("SELECT addressID, street, houseNumber, zipCode, city, country FROM projects WHERE id=:id")
    Address findAddress(Long id);

    @Insert
    void insertOne(Project project);

    @Insert
    void insertAll(List<Project> projects);

    @Update
    void updateOne(Project project);

    @Update
    void updateAll(List<Project> projects);

    @Delete
    void deleteOne(Project project);

    @Delete
    void deleteAll(List<Project> projects);

}