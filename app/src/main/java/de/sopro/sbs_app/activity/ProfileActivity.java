package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.sopro.sbs_app.R;
import de.sopro.sbs_app.database.UserDao;
import de.sopro.sbs_app.model.User;

public class ProfileActivity extends ActivityBridge {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserDao uDAO = RequestHandler.getAppDB(getApplicationContext()).userDao();
        user = uDAO.find();

        setUserInfo();
    }

    private void setUserInfo() {
        TextView id = findViewById(R.id.showId);
        id.setText(user.getId().toString());
        TextView forename = findViewById(R.id.showForename);
        forename.setText(user.getForename());
        TextView lastname = findViewById(R.id.showLastname);
        lastname.setText(user.getLastname());
        TextView username = findViewById(R.id.showUsername);
        username.setText(user.getUsername());
        TextView role = findViewById(R.id.showRole);
        role.setText(user.getRole());
        TextView orga = findViewById(R.id.showOrga);
        orga.setText(user.getOrganisation());
    }

}