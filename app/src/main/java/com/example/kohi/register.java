package com.example.kohi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private EditText fullname, password, phonenumber, email;
    private Button buttonregister;
    private ProgressBar loading;
    private static  String URL_REGIST = "http://192.168.100.11/kohi_backend/register.php";
    String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        fullname = findViewById(R.id.fullname);
        phonenumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonregister = findViewById(R.id.buttonregister);

        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Regist();
            }
        });

    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        buttonregister.setVisibility(View.GONE);

        final String fullname = this.fullname.getText().toString().trim();
        final String phonenumber = this.phonenumber.getText().toString().trim();
        final  String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        success = jsonObject.optString("success");

                        if (success.equals("1")) {
                            Toast.makeText(register.this, "Register Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register.this, Login2.class));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(register.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        buttonregister.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        buttonregister.setVisibility(View.VISIBLE);

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", fullname);
                params.put("phonenumber", phonenumber);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
