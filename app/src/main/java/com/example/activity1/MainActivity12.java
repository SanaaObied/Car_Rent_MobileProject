package com.example.activity1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity12 extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.56.1:80/projectMobile/get_reservations.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main12);

        ListView listView = findViewById(R.id.lstDetails);

        // Fetch data from PHP script
        fetchDataFromServer(listView);
    }

    private void fetchDataFromServer(ListView listView) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a JSON response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, BASE_URL, null,
                response -> {
                    List<Reservation> reservations = new ArrayList<>();

                    // Parse the JSON response
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int reservationId = jsonObject.getInt("ReservationId");
                            String carName = jsonObject.getString("CarName");
                            int driverLicense = jsonObject.getInt("DiverLiscenes"); // Corrected field name
                            String startDate = jsonObject.getString("StartDate");
                            String endDate = jsonObject.getString("EndDate");
                            int totalFee = jsonObject.getInt("TotalFee");

                            Reservation reservation = new Reservation(reservationId, carName, driverLicense, startDate, endDate, totalFee);
                            reservations.add(reservation);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Create and set the adapter to the ListView
                    ReservationAdapter adapter = new ReservationAdapter(MainActivity12.this, reservations);
                    listView.setAdapter(adapter);
                },
                error -> {
                    // Handle error
                }
        );


        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }
}