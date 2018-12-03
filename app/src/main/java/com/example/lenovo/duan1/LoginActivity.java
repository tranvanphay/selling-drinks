package com.example.lenovo.duan1;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView ivLogo;
    EditText etUsername, etPassword;
    LinearLayout linearLayoutLogin;
    Button btnDangNhap,btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvTitle = findViewById(R.id.tvTitle);
        ivLogo = findViewById(R.id.ivLogo);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        linearLayoutLogin = findViewById(R.id.linearLayoutLogin);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
//        btn = findViewById(R.id.button);
        Animation animation_logo = AnimationUtils.loadAnimation(this,R.anim.anim_logo);
        Animation animation_title = AnimationUtils.loadAnimation(this,R.anim.anim_title);
        Animation animation_dangnhap = AnimationUtils.loadAnimation(this,R.anim.anim_khunglogin);
        Animation animation_buttondangnhap = AnimationUtils.loadAnimation(this,R.anim.anim_button_dangnhap);
        Animation animation_buttondangky = AnimationUtils.loadAnimation(this,R.anim.anim_button_dangky);


        AnimationSet animationSet_logo = new AnimationSet(false);//false means don't share interpolators

        animationSet_logo.addAnimation(animation_logo);
        animationSet_logo.setFillAfter(true);
        ivLogo.startAnimation(animationSet_logo);

        tvTitle.startAnimation(animation_title);
        linearLayoutLogin.startAnimation(animation_dangnhap);
        btnDangNhap.startAnimation(animation_buttondangnhap);
        btnDangKy.startAnimation(animation_buttondangky);


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,AdminActivity.class));
            }
        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_dangky);
                dialog.show();


            }
        });
    }

}

