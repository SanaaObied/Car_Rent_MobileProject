package com.example.activity1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity8 extends AppCompatActivity {
    private TextView txtBrand;
    private TextView txtModel;
    private TextView txtTransmissiontypes;
    private TextView txtPrice;
    private Button btnConfirm;
    private Button btnBack;
    private static  final String BASE_URL="http://192.168.1.110/carreservation1/Car.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main8);

        txtBrand = findViewById(R.id.txtBrand);
        txtPrice = findViewById(R.id.txtPrice);
        txtModel = findViewById(R.id.txtModel);
        txtTransmissiontypes = findViewById(R.id.txtTransmissiontypes);
        btnBack = findViewById(R.id.btnBack);
        btnConfirm = findViewById(R.id.btnConfirm);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadItems();
        btnConfirm.setOnClickListener(v -> showConfirmDialog());
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Ready to confirm")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void loadItems() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String brand = jsonObject.getString("Brand");
                            String model = jsonObject.getString("Model");


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity8.this, "Error parsing data", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity8.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        Volley.newRequestQueue(MainActivity8.this).add(stringRequest);
    }

}
