package com.example.activity1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {
    TextView txtReserve ;
    TextView txtaccount ;
    TextView txtFristName ;
    TextView txtLastName;
    TextView txtEmail;
    TextView txtxPhone ;

    TextView txtDriver ;
    RadioGroup RGender ;
    RadioButton rBtnMale ;
    RadioButton rBtnfemale ;
    TextView txtPass ;
    TextView txtPass2 ;
    Button  btnStart ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        Show();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void Show (){
        txtReserve = findViewById(R.id.txtReserve);
        txtaccount = findViewById(R.id.txtaccount);
        txtFristName = findViewById(R.id.txtFristName);
        txtLastName = findViewById(R.id.txtLastName);
        txtEmail = findViewById(R.id.txtEmail);
        txtxPhone = findViewById(R.id.txtxPhone);
        txtDriver = findViewById(R.id.txtDriver);
        RGender = findViewById(R.id.RGender);
        rBtnMale = findViewById(R.id.rBtnMale);
        rBtnfemale = findViewById(R.id.rBtnfemale);
        txtPass = findViewById(R.id.txtPass);
        txtPass2 = findViewById(R.id.txtPass2);
        btnStart = findViewById(R.id.btnStart);

    }
}