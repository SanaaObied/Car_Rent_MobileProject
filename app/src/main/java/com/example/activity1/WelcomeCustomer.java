package com.example.activity1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class WelcomeCustomer extends AppCompatActivity {

    TextView txtWelcome1;
    CheckBox checkBox;
    EditText txtName1;
    EditText edtTxtPass1;
    Button btnLogin1;

    private static final String TAG = "WelcomeCustomer";
    private static final String PREF_NAME = "LoginPrefs";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_customer);
        show();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = txtName1.getText().toString().trim();
                String enteredPassword = edtTxtPass1.getText().toString().trim();

                // Execute AsyncTask to authenticate user
                new AuthenticationTask().execute(enteredUsername, enteredPassword);
            }
        });

        // Load saved username and password if available
        loadSavedCredentials();
    }

    private void show() {
        checkBox = findViewById(R.id.checkBox);
        txtWelcome1 = findViewById(R.id.txtWeclome1);
        txtName1 = findViewById(R.id.txtname1);
        edtTxtPass1 = findViewById(R.id.edtTxtPAss);
        btnLogin1 = findViewById(R.id.btnLogin1);
    }

    private class AuthenticationTask extends AsyncTask<String, Void, Boolean> {

        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return authenticateUser(username, password);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(WelcomeCustomer.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Save username and password in SharedPreferences
                saveCredentials(txtName1.getText().toString(), edtTxtPass1.getText().toString());
                // Add your login logic here (e.g., start a new activity)
            } else {
                Toast.makeText(WelcomeCustomer.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean authenticateUser(String username, String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.105/CarReservation/CustomerLogin.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            boolean isAuthenticated = false;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String dbUsername = jsonObject.getString("FirstName"); // Check against FirstName column
                                String dbPassword = jsonObject.getString("pass");

                                if (username.equals(dbUsername) && password.equals(dbPassword)) {
                                    // Authentication successful
                                    isAuthenticated = true;
                                    break;
                                }
                            }

                            if (isAuthenticated) {
                                // Authentication successful
                                Toast.makeText(WelcomeCustomer.this, "Login successful", Toast.LENGTH_SHORT).show();
                                // Add your login logic here (e.g., start a new activity)
                            } else {
                                // Authentication failed
                                Toast.makeText(WelcomeCustomer.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON response: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error fetching data from server: " + error.getMessage());
            }
        });

        queue.add(stringRequest);
        return true;
    }

    // Method to load saved username and password from SharedPreferences
    private void loadSavedCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString(PREF_USERNAME, "");
        String savedPassword = sharedPreferences.getString(PREF_PASSWORD, "");
        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            // Display saved username and password
            txtName1.setText(savedUsername);
            edtTxtPass1.setText(savedPassword);
        }
    }

    // Method to save username and password in SharedPreferences
    private void saveCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_USERNAME, username);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }
}
