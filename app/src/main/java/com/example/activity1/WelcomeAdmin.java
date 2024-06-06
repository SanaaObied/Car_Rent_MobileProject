package com.example.activity1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class WelcomeAdmin extends AppCompatActivity {

    TextView txtWeclome;
    EditText txtname;
    EditText edtpass;
    Button btnLogin;

    private static final String TAG = "WelcomeAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_admin);
        show();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtWeclome = findViewById(R.id.txtWeclome);
        txtname = findViewById(R.id.txtname);
        edtpass = findViewById(R.id.edtpass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = txtname.getText().toString().trim();
                String enteredPassword = edtpass.getText().toString().trim();

                // Execute AsyncTask to authenticate user
                new AuthenticationTask().execute(enteredUsername, enteredPassword);
            }
        });
    }

    private void show() {
        // Method to initialize views
    }

    private class AuthenticationTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return authenticateUser(username, password);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(WelcomeAdmin.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Add your login logic here (e.g., start a new activity)
            } else {
                Toast.makeText(WelcomeAdmin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean authenticateUser(String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.105/CarReservation/Admin.php";
        AtomicBoolean isAuthenticated = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String dbUsername = jsonObject.getString("UserName");
                                String dbPassword = jsonObject.getString("Pass");

                                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                                    // Authentication successful
                                    isAuthenticated.set(true);
                                    break;
                                }
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON response: " + e.getMessage());
                        } finally {
                            latch.countDown();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error fetching data from server: " + error.getMessage());
                latch.countDown();
            }
        });

        queue.add(stringRequest);

        try {
            latch.await(); // Wait for the Volley response
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isAuthenticated.get();
    }
}
