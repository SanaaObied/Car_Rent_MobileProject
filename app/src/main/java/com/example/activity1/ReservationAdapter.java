package com.example.activity1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ReservationAdapter extends ArrayAdapter<Reservation> {

    public ReservationAdapter(Context context, List<Reservation> reservations) {
        super(context, 0, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Reservation reservation = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_reservation, parent, false);
        }

        // Lookup view for data population
        TextView reservationIdTextView = convertView.findViewById(R.id.reservation_id_text_view);
        TextView carNameTextView = convertView.findViewById(R.id.car_name_text_view);
        TextView driverLicenseTextView = convertView.findViewById(R.id.driver_license_text_view);
        TextView startDateTextView = convertView.findViewById(R.id.start_date_text_view);
        TextView endDateTextView = convertView.findViewById(R.id.end_date_text_view);
        TextView totalFeeTextView = convertView.findViewById(R.id.total_fee_text_view);

        // Populate the data into the template view using the data object
        reservationIdTextView.setText("ReservationId: " + String.valueOf(reservation.getReservationId()));
        carNameTextView.setText("CarName: " + reservation.getCarName());
        driverLicenseTextView.setText("DriverLicense: " + String.valueOf(reservation.getDriverLicense()));
        startDateTextView.setText("StartDate: " + reservation.getStartDate());
        endDateTextView.setText("EndDate: " + reservation.getEndDate());
        totalFeeTextView.setText("TotalFee: $" + String.valueOf(reservation.getTotalFee()));

        // Return the completed view to render on screen
        return convertView;
    }
}