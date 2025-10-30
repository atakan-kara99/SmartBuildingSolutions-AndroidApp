package de.sopro.sbs_app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import de.sopro.sbs_app.model.*;

@Database(version = 1,
          exportSchema = false,
          entities = {BillingItem.class, BillingUnit.class, ConstructionProgress.class,
                      Contract.class, Project.class, User.class, Status.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ConstructionProgressDao constructionProgressDao();

    public abstract BillingUnitDao billingUnitDao();

    public abstract BillingItemDao billingItemDao();

    public abstract ContractDao contractDao();

    public abstract ProjectDao projectDao();

    public abstract UserDao userDao();

    public abstract StatusDao statusDao();

}
