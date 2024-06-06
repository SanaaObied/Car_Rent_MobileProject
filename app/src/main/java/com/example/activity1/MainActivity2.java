package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import  android.content.Intent;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button adminButton = findViewById(R.id.btnADMIN);
        Button customerButton = findViewById(R.id.btnCustomer);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action for admin button click
                // For example, start AdminActivity
             //  Intent intent = new Intent(MainActivity2.this, AdminActivity.class);
                //startActivity(intent);
            }
        });


        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action for customer button click
                // For example, start CustomerActivity
               Intent intent = new Intent(MainActivity2.this, MainActivity8.class);
                startActivity(intent);
            }
        });
    }
}