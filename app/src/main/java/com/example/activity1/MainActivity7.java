package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import com.android.volley.TimeoutError;
import com.android.volley.AuthFailureError;
import com.android.volley.ServerError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class MainActivity7 extends AppCompatActivity {
    private Spinner spnMenu;
    private RecyclerView recyclerView;
    private CustomListAdapter adapter;
    private List<Car> itemsList;
    private static final String BASE_URL = "http://192.168.56.1:80/projectMobile/get_car_models.php?brand=";
    private static final String TAG = "MainActivity7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Initialize the spinner and recycler view
        spnMenu = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.recyclerView);

        // Set the layout manager and adapter for the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsList = new ArrayList<>();
        adapter = new CustomListAdapter(this, itemsList);
        recyclerView.setAdapter(adapter);

        // Setup the spinner
        setupSpinner();
    }



    private void setupSpinner() {
        Log.d(TAG, "Setting up spinner with URL: " + BASE_URL);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Brands response: " + response);
                        try {
                            // Assuming response is a JSON array of strings
                            JSONArray brandsArray = new JSONArray(response);
                            List<String> brands = new ArrayList<>();
                            for (int i = 0; i < brandsArray.length(); i++) {
                                brands.add(brandsArray.getString(i));
                            }
                            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity7.this, android.R.layout.simple_spinner_dropdown_item, brands);
                            spnMenu.setAdapter(spinnerAdapter);

                            spnMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedBrand = brands.get(position);
                                    Log.d(TAG, "Selected brand: " + selectedBrand);
                                    fetchMenuItems(selectedBrand);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    Log.d(TAG, "Nothing selected in spinner");
                                }
                            });
                        } catch (JSONException e) {
                            Log.e(TAG, "Failed to parse JSON: " + e.getMessage(), e);
                            Toast.makeText(MainActivity7.this, "Failed to parse JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
            }
        });

        Volley.newRequestQueue(MainActivity7.this).add(stringRequest);
    }

    private void fetchMenuItems(String brand) {
        String url = BASE_URL + brand;
        Log.d(TAG, "Fetching models for brand: " + brand + " with URL: " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Models response: " + response);
                        try {
                            JSONArray modelsArray = new JSONArray(response);
                            itemsList.clear();
                            for (int i = 0; i < modelsArray.length(); i++) {
                                String model = modelsArray.getJSONObject(i).getString("Model");
                                String image = modelsArray.getJSONObject(i).getString("Image");
                                Car car = new Car(model, image);
                                itemsList.add(car);
                            }
                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "Items list updated, item count: " + itemsList.size());
                        } catch (JSONException e) {
                            Log.e(TAG, "Failed to parse JSON: " + e.getMessage(), e);
                            Toast.makeText(MainActivity7.this, "Failed to parse JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
            }
        });

        Volley.newRequestQueue(MainActivity7.this).add(stringRequest);
    }

    private void handleVolleyError(VolleyError error) {
        String message;
        if (error instanceof TimeoutError || error instanceof NetworkError) {
            message = "Connection timeout";
        } else if (error instanceof AuthFailureError) {
            message = "Authentication failure";
        } else if (error instanceof ServerError) {
            message = "Server error";
        } else if (error instanceof ParseError) {
            message = "Parse error";
        } else {
            message = "Volley error: " + error.getMessage();
        }
        Log.e(TAG, message);
        Toast.makeText(MainActivity7.this, message, Toast.LENGTH_SHORT).show();
    }
}
