package de.sopro.sbs_app.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityBridge extends AppCompatActivity {

    public void onLogout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}
