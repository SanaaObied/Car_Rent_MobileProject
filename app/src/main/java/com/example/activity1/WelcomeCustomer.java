package com.example.activity1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeCustomer extends AppCompatActivity {

    TextView txtWeclome1 ;
    EditText txtname1 ;
    EditText edtTxtPAss ;

    TextView txtinvalidName1 ;
    TextView txtInvalidPass1 ;
    Button btnLogin1 ;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_customer);
        show ();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void show (){
        txtWeclome1 = findViewById(R.id.txtWeclome1);
        txtname1 = findViewById(R.id.txtname1);
        edtTxtPAss = findViewById(R.id.edtTxtPAss);
        txtinvalidName1 = findViewById(R.id.txtinvalidName1);
        txtInvalidPass1 = findViewById(R.id.txtInvalidPass1);
        btnLogin1 = findViewById(R.id.btnLogin1);
        checkBox = findViewById(R.id.checkBox);


    }
}