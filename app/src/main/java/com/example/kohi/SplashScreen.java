package com.example.kohi;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private EditText email, password;
    String getEmail = "", getPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login Data", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                getEmail = pref.getString("email", null);
                getPassword = pref.getString("password", null);

                if (getEmail == null) {
                    startActivity(new Intent(getApplicationContext(), login1.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), home.class));
                }
                finish();
            }
        }, 3000);
    }
}
