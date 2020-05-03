package com.example.kohi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class login1 extends AppCompatActivity {
    private Button buttonlogin, buttonnotlogin;
    private long backPressedTime;
    private Toast backToast;
    SharedPreferences pref;
    Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        buttonlogin = (Button) findViewById(R.id.buttonlogin);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin2();

            }
        });

        buttonnotlogin = findViewById(R.id.buttonnotlogin);
        buttonnotlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomenotuser();
            }
        });
    }

    private void openHomenotuser() {
        Prefrences.setLoggedInStatus(getBaseContext(),true);
        Intent intent = new Intent(this, homenonuser.class);
        startActivity(intent);
    }

    public void openLogin2() {
        Intent intent = new Intent(this, Login2.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(Prefrences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(this, home.class));
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
