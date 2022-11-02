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
import android.widget.Toast;

import com.example.hotelbooking.Model.User;
import com.example.hotelbooking.R;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private final Gson gson=new Gson();
    private EditText edPhone;
    private EditText edPass;
    private EditText edConfirmPass;
    private Button btnReg, btnTransact;
    ImageButton imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedPreferences=getSharedPreferences(Utils.SHARE_PREFERENCES_APPS, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        anhxa();
        eventclick();
    }
    void anhxa(){
        btnReg=findViewById(R.id.btnRegister);
        btnTransact=findViewById(R.id.btnTransaction);
        imgBack=findViewById(R.id.imBack);
        edPhone=findViewById(R.id.edPhoneRe);
        edPass=findViewById(R.id.edPassRe);
        edConfirmPass=findViewById(R.id.edConfirmPassRe);
    }
    void eventclick(){
        btnTransact.setOnClickListener(ClickinTransaction());
        imgBack.setOnClickListener(ClickinBack());
        btnReg.setOnClickListener(v ->registerevent());
    }
    void registerevent(){
        String phone=edPhone.getText().toString().trim();
        String pass=edPass.getText().toString().trim();
        String confirmpass=edConfirmPass.getText().toString().trim();
        boolean isvalid=checkphone(phone)&&checkpass(pass,confirmpass);
        if(isvalid){
            User usernew=new User();
            usernew.setPhonenumber(phone);
            usernew.setPass(pass);
            String userStr=gson.toJson(usernew);
            editor.putString(Utils.KEY_USER,userStr);
            editor.commit();
            Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private View.OnClickListener ClickinTransaction(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        };
    }
    private View.OnClickListener ClickinBack(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(RegisterActivity.this, BeginActivity.class);
                startActivity(i);
            }
        };
    }
    private boolean checkphone(@NonNull String phone){
        if (phone.isEmpty()){
            edPhone.setError("Không được bỏ trống");
            return  false;
        }
        if(phone.length()<12){
            edPhone.setError("SĐT không được quá 12 số");
            return false;
        }
        return true;
    }
    private boolean checkpass(@NonNull String pass, String confirm){
        if (pass.isEmpty()){
            edPass.setError("Không được bỏ trống");
            return  false;
        }
        if(!pass.equals(confirm)){
            edConfirmPass.setError("Mật khẩu không trùng khớp");
            return false;
        }
        return true;
    }
}