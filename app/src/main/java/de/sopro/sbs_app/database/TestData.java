package de.sopro.sbs_app.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.sopro.sbs_app.activity.RequestHandler;
import de.sopro.sbs_app.model.Address;
import de.sopro.sbs_app.model.BillingItem;
import de.sopro.sbs_app.model.BillingUnit;
import de.sopro.sbs_app.model.Contract;
import de.sopro.sbs_app.model.Project;
import de.sopro.sbs_app.model.Status;
import de.sopro.sbs_app.model.User;

public class TestData {

    private static StatusDao statusDao;

    public static void generateData(Context context) {
        AppDatabase appDB = RequestHandler.getAppDB(context);
        UserDao userDao = appDB.userDao();
        generateUser(userDao);
        statusDao = appDB.statusDao();
        generateStatus(statusDao);
        ProjectDao projectDao = appDB.projectDao();
        generateProjects(projectDao);
        ContractDao contractDao = appDB.contractDao();
        generateContracts(contractDao);
        BillingUnitDao billingUnitDao = appDB.billingUnitDao();
        generateBillingUnits(billingUnitDao);
        BillingItemDao billingItemDao = appDB.billingItemDao();
        generateBillingItems(billingItemDao);

    }

    private static void generateUser(UserDao dao) {
        if (dao.find() == null) {
            dao.insert(new User(1337L,
                    "Benaja Joachim Gottfried Max",
                    "Mix",
                    "admin",
                    "$2a$10$8dW9BsgEDb0iTaycAzEeZ.xUOfHB4PKM4AGAZns5778ajywM0rE.i",
                    "Pokémon",
                    "Kleinstein und Friedrichs GmbH"
            ));
        }
    }

    private static void generateStatus(StatusDao dao) {
        if (dao.findAll().isEmpty()) {
            List<Status> statusList = new ArrayList<>();
            statusList.add(
                    new Status("NO_STATUS",
                            "Element nicht gemeldet", 
                            null
                    ));
            statusList.add(
                    new Status("OPEN",
                            "Element gemeldet, aber nicht geprüft",
                            "NO_STATUS"
                    ));
            statusList.add(
                    new Status("OK",
                            "Element gemeldet und bestätigt",
                            "OPEN"
                    ));
            statusList.add(
                    new Status("DENY",
                            "Element gemeldet und abgelehnt",
                            "OPEN"
                    ));
            dao.insertAll(statusList);
        }
    }

    public static Status randomStatus() {
        Random rng = new Random();
        int num = rng.nextInt(4);
        Status val = null;
        switch (num) {
            case 0:
                val = statusDao.findByName("NO_STATUS");
                break;
            case 1:
                val = statusDao.findByName("OPEN");
                break;
            case 2:
                val = statusDao.findByName("OK");
                break;
            case 3:
                val = statusDao.findByName("DENY");
                break;
            default:
                break;
        }
        return val;
    }

    private static void generateProjects(ProjectDao dao) {
        if (dao.findAll().isEmpty()) {
            Project p;
            for (int i = 0; i < 10; i++) {
                p = new Project(
                        (long) i,
                        "project " + i,
                        "description",
                        "erster August oder so",
                        "vorgestern",
                        (double) i,
                        "Ich",
                        new Address((long) 132,
                                "Straße",
                                9, 21,
                                "Ostsee",
                                "Atlantik"),
                        "org",
                        randomStatus()
                );
                dao.insertOne(p);
            }
        }
    }

    private static void generateContracts(ContractDao dao) {

        if (dao.findAll().isEmpty()) {
            int id = 0;
            for (int fk = 0; fk < 10; fk++) {

                for (int i = 0; i < 10; i++) {

                    Contract c = new Contract((long) id,
                            "contract name " + id,
                            "description",
                            "consignee",
                            "contractor",
                            (long) fk,
                            randomStatus());
                    dao.insertOne(c);
                    id++;
                }
            }
        }
    }

    private static void generateBillingUnits(BillingUnitDao dao) {
        if (dao.findAll().isEmpty()) {
            int id = 0;

            for (int fk = 0; fk < 100; fk++) {

                for (int i = 0; i < 10; i++) {
                    BillingUnit b = new BillingUnit((long) id, "short desc " + id, "long desc",
                            "absolute unit",
                            "morgen abend 20 Uhr",
                            2.4,
                            1,
                            (long) fk,
                            randomStatus());
                    dao.insertOne(b);
                    id++;
                }
            }
        }
    }
/*
    private static void generateBillingItems(BillingItemDao dao) {
        if (dao.findAll().isEmpty()) {
            List<BillingItem> items = new ArrayList<>();
            BillingItem a = new BillingItem((long) 0,
                    4.50,
                    "short desc" + 0,
                    randomStatus(),
                    5.97,
                    "Wassermelonen",
                    0.99,
                    "what's dis",
                    null,
                    (long) 1);
            items.add(a);
            BillingItem b = new BillingItem((long) 1,
                    4.50,
                    "short desc" + 1,
                    randomStatus(),
                    5.97,
                    "Wassermelonen",
                    0.99,
                    "what's dis",
                    (long) 0,
                    null);
            items.add(b);
            BillingItem c = new BillingItem((long) 2,
                    4.50,
                    "short desc" + 2,
                    randomStatus(),
                    5.97,
                    "Wassermelonen",
                    0.99,
                    "what's dis",
                    (long) 0,
                    null);
            items.add(c);
            BillingItem d = new BillingItem((long) 3,
                    4.50,
                    "short desc" + 3,
                    randomStatus(),
                    5.97,
                    "Wassermelonen",
                    0.99,
                    "what's dis",
                    (long) 0,
                    null);
            items.add(d);
            dao.insertAll(items);
        }
    }
    */
    private static void generateBillingItems(BillingItemDao dao) {
        if (dao.findAll().isEmpty()) {
            int id = 0;
            for (int fk = 0; fk < 1000; fk++) {
                for (int i = 0; i < 10; i++) {
                    BillingItem b = new BillingItem((long) id,
                            4.50,
                            "short desc" + id,
                            randomStatus(),
                            5.97,
                            "Wassermelonen",
                            0.99,
                            "what's dis",
                            null,
                            (long) fk);
                    dao.insertOne(b);
                    id++;
                }
            }
            BillingItem d = new BillingItem((long) 1337,
                    4.50,
                    "short desc" + 1337,
                    randomStatus(),
                    5.97,
                    "Wassermelonen",
                    0.99,
                    "what's dis",
                    (long) 1,
                    null);
            dao.updateOne(d);
        }
    }

}
