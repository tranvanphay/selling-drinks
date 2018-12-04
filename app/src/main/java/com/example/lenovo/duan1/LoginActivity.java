package com.example.lenovo.duan1;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        if (checkNetwork()) {
            //Connected to the Internet

        } else {
            //Not connected
            final AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
            builder.setPositiveButton("Bật wifi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    @SuppressLint("WifiManagerLeak") WifiManager wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                }
            });
            builder.setTitle("Lỗi");
            builder.setMessage("Bạn chưa kết nối mạng!!!");
            AlertDialog dialog=builder.create();
            dialog.show();
        }

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
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_dangky);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();
                ImageView ivCloseDialogDangKy = dialog.findViewById(R.id.ivCloseDialogDangKy);
                ivCloseDialogDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });


            }
        });
    }
    private boolean checkNetwork(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}

