package com.example.kohi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login2 extends AppCompatActivity {
    private TextView register;
    private EditText email, password;
    private Button  butonlogin;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://192.168.100.11/kohi_backend/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();

            }
        });

        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        butonlogin = findViewById(R.id.buttonlogin);

        butonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    login(mEmail, mPassword);
                } else {
                    email.setError("Please insert username or email");
                    password.setError("Please insert password");
                }
            }
        });
    }

    private void login(final String email, final String password) {
        loading.setVisibility(View.VISIBLE);
        butonlogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                        JSONObject jsonObject = new JSONObject (response);
                        String success = jsonObject.optString("success");

                        if (success.equals("1")) {

                            Toast.makeText(Login2.this, "Welcome", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login2.this, home.class));
                                 }  else {
                                Toast.makeText(getApplicationContext(), "Email / Password salah!", Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.GONE);
                                butonlogin.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login2.this, "Erorr" +e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            butonlogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login2.this, "Erorr" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openRegister() {
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}