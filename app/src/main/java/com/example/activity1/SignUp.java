package com.example.activity1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText txtFirstName;
    EditText txtLastName;
    EditText txtEmail;
    EditText txtPhoneNumber;
    EditText txtDriverLicense;
    RadioGroup RGender;
    RadioButton rBtnMale;
    RadioButton rBtnFemale;
    EditText txtPassword;
    EditText txtConfirmPassword;
    Button btnSignUp;

    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeViews();

        btnSignUp.setOnClickListener(v -> {
            // Get the values entered by the user
            String firstName = txtFirstName.getText().toString().trim();
            String lastName = txtLastName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String phoneNumber = txtPhoneNumber.getText().toString().trim();
            String driverLicense = txtDriverLicense.getText().toString().trim();
            String gender = RGender.getCheckedRadioButtonId() == R.id.rBtnMale ? "M" : "F";
            String password = txtPassword.getText().toString().trim();
            String confirmPassword = txtConfirmPassword.getText().toString().trim();

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                txtConfirmPassword.setError("Passwords do not match");
                return;
            }

            // Sign up user
            signUpUser(firstName, lastName, email, phoneNumber, driverLicense, gender, password);
        });
    }

    private void initializeViews() {
        txtFirstName = findViewById(R.id.txtFristName);
        txtLastName = findViewById(R.id.txtLastName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtxPhone);
        txtDriverLicense = findViewById(R.id.txtDriver);
        RGender = findViewById(R.id.RGender);
        rBtnMale = findViewById(R.id.rBtnMale);
        rBtnFemale = findViewById(R.id.rBtnfemale);
        txtPassword = findViewById(R.id.txtPass);
        txtConfirmPassword = findViewById(R.id.txtPass2);
        btnSignUp = findViewById(R.id.btnStart);
    }

    private void signUpUser(String firstName, String lastName, String email, String phoneNumber, String driverLicense, String gender, String password) {
        RequestQueue queue = Volley.newRequestQueue(SignUp.this);
        String url = "https://192.168.1.105/CarReservation/Customersignup.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        Log.d(TAG, "Response: " + response); // Log the server response
                        JSONObject jsonObject = new JSONObject(response);
                        String message = jsonObject.getString("message");
                        if (message.equals("Customer added successfully.")) {
                            Log.d(TAG, "Sign up successful");
                            Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Sign up failed: " + message);
                            Toast.makeText(SignUp.this, "Sign up failed: " + message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                        Toast.makeText(SignUp.this, "Sign up failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e(TAG, "Volley error: " + error.getMessage());
                    Toast.makeText(SignUp.this, "Sign up failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("email", email);
                params.put("phone_number", phoneNumber);
                params.put("driver_license", driverLicense);
                params.put("gender", gender); // ensure gender is a single character string
                params.put("password", password);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
