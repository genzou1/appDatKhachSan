package com.example.hotelbooking.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hotelbooking.MainActivity;
import com.example.hotelbooking.Model.User;
import com.example.hotelbooking.R;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    private final Gson gson=new Gson();
    SharedPreferences sharedPreferences;
    Button btnLog, btnTransact;
    ImageButton imgBack;
    EditText edPhone,edPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();
        sharedPreferences=getSharedPreferences(Utils.SHARE_PREFERENCES_APPS, Context.MODE_PRIVATE);
        eventclick();
    }
    void anhxa(){
        btnLog=findViewById(R.id.btnLogin);
        btnTransact=findViewById(R.id.btnTransaction);
        imgBack=findViewById(R.id.imBack);
        edPhone=findViewById(R.id.edPhoneL);
        edPass=findViewById(R.id.edPassL);
    }
    void eventclick(){
        btnLog.setOnClickListener(view -> checklogin());
        btnTransact.setOnClickListener(ClickinTransaction());
        imgBack.setOnClickListener(ClickinBack());
    }

    private View.OnClickListener ClickinTransaction(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }
    private View.OnClickListener ClickinBack(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginActivity.this, BeginActivity.class);
                startActivity(i);
            }
        };
    }
    private void checklogin(){
        String userPref=sharedPreferences.getString(Utils.KEY_USER,null);
        User user=gson.fromJson(userPref,User.class);
        String phone=edPhone.getText().toString().trim();
        String pass=edPass.getText().toString().trim();
        if(user==null){
            return;
        }
        boolean checkLogin=checkphone(phone)&&checkpass(pass);
        if(!checkLogin)
            Toast.makeText(LoginActivity.this,"Vui lòng nhập đúng tài khoản",Toast.LENGTH_LONG).show();
        boolean isValid=phone.equals(user.getPhonenumber())&&pass.equals(user.getPass());
        if(isValid){
            Intent i=new Intent(LoginActivity.this, MainActivity.class);
            Bundle goi=new Bundle();
            goi.putSerializable(Utils.KEY_USER_PROFILE,user);
            i.putExtras(goi);
            startActivity(i);
            finish();
        }
    }
    private boolean checkphone(@NonNull String phone){
        if (phone.isEmpty()){
            edPhone.setError("Không được bỏ trống");
            return  false;
        }
        return true;
    }
    private boolean checkpass(@NonNull String pass){
        if (pass.isEmpty()){
            edPass.setError("Không được bỏ trống");
            return  false;
        }
        return true;
    }
}