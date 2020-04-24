package com.example.kohi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login2 extends AppCompatActivity {
private TextView register;
private EditText username;
private EditText password;
private Button  butonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin2();

            }
        });

        butonlogin = (Button) findViewById(R.id.buttonlogin);
        butonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });
    }

    public void openLogin2() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
    public void openHome() {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        if( username.getText().toString().length() == 0 )
            username.setError( "Username is required!" );
        else if ( password.getText().toString().length() == 0 )
            password.setError( "Password is required!" );
        else if (username.getText().toString().equals("admin") && password.getText().toString().equals("123")) {
            Intent intent = new Intent(this, home.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Check Username or Password", Toast.LENGTH_SHORT).show();
        }
    }

}
