package de.sopro.sbs_app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.AppDatabase;
import de.sopro.sbs_app.database.TestData;
import de.sopro.sbs_app.database.UserDao;
import de.sopro.sbs_app.model.User;

public class LoginActivity extends ActivityBridge {
    // Variables for rotating the logo
    private Thread rotation = new Thread();
    float degree = 1;
    private boolean stopRotation;

    // Variables for saving data if app was closed
    SharedPreferences prefs;
    final static String DEFAULT_SERVER_URL = "http://10.0.2.2:8080";

    // Variables for clickables
    Switch urlSwitch;
    EditText editServerUrl;
    EditText editUsername;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get values from preference file
        prefs = getPreferences(Context.MODE_PRIVATE);
        String serverUrl = prefs.getString("SERVER_URL", DEFAULT_SERVER_URL);
        String username = prefs.getString("USERNAME", "");
        editUsername = findViewById(R.id.username);
        editServerUrl = findViewById(R.id.login_url);
        editUsername.setText(username);
        editServerUrl.setText(serverUrl);

        stopRotation = false;

        //init DB with testData
        TestData.generateData(getApplicationContext());

        // Initialize user dao
        userDao = RequestHandler.getAppDB(getApplicationContext()).userDao();

        // Activate URL-Switch listener
        urlSwitch = findViewById(R.id.urlSwitch);
        activateURLSwitch();
    }

    @Override
    protected void onPause() {
        rotation.interrupt();
        // Get current values from view components
        String serverUrl = editServerUrl.getText().toString();
        String username = editUsername.getText().toString();

        // Update values in preference file asynchronously
        prefs.edit().putString("SERVER_URL", serverUrl).apply();
        prefs.edit().putString("USERNAME", username).apply();

        super.onPause();
    }

    public void loginButton(View view){

        //TODO: MUSS NOCH WEG, ABER BLEIBT ERSTMAL IN DER
        // ENTWICKLUNGSPHASE UM DAS EINLOGGEN ZU VEREINFACHEN
        nextActivity();
/*
        // Initialize stings for edit texts
        String username = editUsername.getText().toString();
        EditText editPassword = findViewById(R.id.login_password);
        String password = editPassword.getText().toString();
        String serverUrl = editServerUrl.getText().toString();
        String hashedPW = BCrypt.generateHashedPass(password);

        // Check if all are not empty
        if (!(username.equals("") || password.equals("") || serverUrl.equals(""))) {
            // If there is network connection active
            if (RequestHandler.isConnected(this)) {
                // Forward login process to request handler. View projects if successful
                if (RequestHandler.login(username, hashedPW, serverUrl)) {

                    RequestHandler.syncDatabase();

                    prefs.edit().putString("USERNAME", username).apply();
                    nextActivity();
                    return;
                }
            } else {
                User user = userDao.find();
                if (user != null) {
                    // Offline authorization process
                    String savedUsername = user.getUsername();
                    String savedPassword = user.getPassword();
                    // Check if username and password are correct
                    if (username.equals(savedUsername) && BCrypt.isValid(password, savedPassword)) {
                        nextActivity();
                        return;
                    }
                }
            }
        }
        Toast.makeText(this, "Try again!", Toast.LENGTH_LONG).show();

 */
    }

    private void nextActivity() {
        stopRotation = true;
        rotation.interrupt();
        Intent intent = new Intent(this, ProjectListActivity.class);
        startActivity(intent);
    }

    public void rotateMe(View view) {
        stopRotation = false;
        View logo = findViewById(R.id.login_logo);
        if (rotation.isAlive()) {
            //change direction
            rotation.interrupt();
        } else {
            // start rotation in new thread
            rotation = new Thread(() -> {

                int updates = 36;

                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000 / updates);
                    } catch (InterruptedException e) {
                        degree *= -1;
                        if (stopRotation)
                            break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                    logo.setRotation(logo.getRotation() + degree);
                }
            });
            rotation.start();
        }
    }

    // Activate listener for the URL-Switch
    private void activateURLSwitch() {
        // Logo long press listener
        ImageView logo = findViewById(R.id.login_logo);
        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                urlSwitch.setVisibility(View.VISIBLE);
                editServerUrl.setVisibility(View.INVISIBLE);
                if (rotation.isAlive()) {
                    stopRotation = true;
                    rotation.interrupt();
                }
                return true;
            }
        });

        // Url edit text on/off switch
        urlSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    urlSwitch.setVisibility(View.GONE);
                    editServerUrl.setVisibility(View.VISIBLE);
                    urlSwitch.setChecked(false);
                }
            }
        });
    }
}
