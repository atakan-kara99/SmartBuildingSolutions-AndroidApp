package de.sopro.sbs_app.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.camera.core.impl.utils.ContextUtil;
import androidx.room.Room;

import de.sopro.sbs_app.database.AppDatabase;
import okhttp3.OkHttpClient;

public class RequestHandler {

    final static String DATABASE_NAME = "sbs-app-database";
    private static OkHttpClient httpClient = new OkHttpClient();
    private static AppDatabase appDB;
    private static String jwtToken;

    /**
     * Returns the singleton App-Database.
     * @param context context
     * @return App Database
     */
    public static AppDatabase getAppDB(Context context) {
        if (appDB == null) {
            appDB = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return appDB;
    }

    // Checks whether there is a internet connection or not.
    public static boolean isConnected(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean login(String username, String hashedPW, String serverUrl){
        //TODO: POST-Request for login mit JWT-Token
        return true;
    }

    public static boolean syncDatabase() {
        return true;
    }
}
