package com.example.hotelbooking.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hotelbooking.R;

public class BeginActivity extends AppCompatActivity {
Button btLog, btRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        anhxa();
        eventclick();
    }
    void anhxa(){
    btLog=findViewById(R.id.btnLogin);
    btRes=findViewById(R.id.btnRegister);
    }
    void eventclick(){
        btRes.setOnClickListener(ClickinRegister());
        btLog.setOnClickListener(ClickinLogin());
    }
    private View.OnClickListener ClickinRegister(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(BeginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }
    private View.OnClickListener ClickinLogin(){
    return new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(BeginActivity.this, LoginActivity.class);
            startActivity(i);
        }
    };
    }
}