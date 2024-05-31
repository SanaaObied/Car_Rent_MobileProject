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


public class WelcomeAdmin extends AppCompatActivity {

     TextView txtWeclome ;
     EditText txtname ;
     EditText edtpass ;

     TextView txtinvalidName ;
     TextView txtInvalidPass ;
     Button btnLogin ;
     CheckBox  checkBox1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_admin);
        show ();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void show (){
        txtWeclome = findViewById(R.id.txtWeclome);
        txtname = findViewById(R.id.txtname);
        edtpass = findViewById(R.id.edtpass);
        txtinvalidName = findViewById(R.id.txtinvalidName);
        txtInvalidPass = findViewById(R.id.txtInvalidPass);
        btnLogin = findViewById(R.id.btnLogin);
        checkBox1 = findViewById(R.id.checkBox);



    }
}