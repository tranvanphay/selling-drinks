package com.example.lenovo.duan1;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Animation animation;
 ActionBar actionBar;
 EditText etUserName,etPassword;
 Button btDangNhap;
 TextView tvDangKy,tvQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        btDangNhap=findViewById(R.id.btDangNhap);
        tvDangKy=findViewById(R.id.tvDangKy);
        tvQuenMK=findViewById(R.id.tvQuenMK);
        actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation = AnimationUtils.loadAnimation(this,R.anim.trans_anim_form);
        etUserName.setAnimation(animation);
        etPassword.setAnimation(animation);
        btDangNhap.setAnimation(animation);
        tvQuenMK.setAnimation(animation);
        tvDangKy.setAnimation(animation);



        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUserName.getText().toString().equals("admin")){
                    Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(LoginActivity.this,NguoiDungActivity.class);
                    startActivity(intent);
                }
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_dang_ky);
                dialog.show();
            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_quen_mat_khau);
                dialog.show();
            }
        });



    }
}
