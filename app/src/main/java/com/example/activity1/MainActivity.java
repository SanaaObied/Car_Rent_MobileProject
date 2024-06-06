package com.example.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.text.method.LinkMovementMethod;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Log In button by its ID
        Button btnLog = findViewById(R.id.btnLOG);
        Button btnsign = findViewById(R.id.btnSig);
        TextView contactTextView = findViewById(R.id.textView2);
        contactTextView.setMovementMethod(LinkMovementMethod.getInstance());


        // Set an OnClickListener on the Log In button
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // Create an Intent to start MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity7.class);
                startActivity(intent);
            }
        });
    }
}
