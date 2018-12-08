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
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    TextView tvTitle;
    ImageView ivLogo;
    EditText etUsername, etPassword;
    EditText edt_EmailDangKy,edt_matKhauDangKy,edt_nhapLaiMatKhau;
    LinearLayout linearLayoutLogin;
    Button btnDangNhap,btnDangKy;
    FirebaseAuth mAuth;
    private long thoiGian;
    private Toast thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
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
                AlertDialog alertDialog= new SpotsDialog.Builder().setContext(LoginActivity.this).build();
                alertDialog.setMessage("Đăng nhập");
                alertDialog.show();
                dangnhap();
            }
        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialogDangKy = new Dialog(LoginActivity.this);
                dialogDangKy.setContentView(R.layout.dialog_dangky);
                edt_EmailDangKy=dialogDangKy.findViewById(R.id.edt_EmailDangKy);
                edt_matKhauDangKy=dialogDangKy.findViewById(R.id.edt_PasswordDangKy);
                edt_nhapLaiMatKhau=dialogDangKy.findViewById(R.id.edt_nhapLaiMatKhau);
                Button bt_xacNhanDangKy=dialogDangKy.findViewById(R.id.bt_xacNhanDangKy);
                dialogDangKy.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
                bt_xacNhanDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dangky();

                    }
                });

                ImageView ivCloseDialogDangKy = dialogDangKy.findViewById(R.id.ivCloseDialogDangKy);
                ivCloseDialogDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDangKy.cancel();
                    }
                });
                dialogDangKy.show();
            }
        });
    }
    private boolean checkNetwork(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void dangky(){
        String email=edt_EmailDangKy.getText().toString();
        String matKhau=edt_matKhauDangKy.getText().toString();
        String nhapLaiMatKhau=edt_nhapLaiMatKhau.getText().toString();

        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if(!matKhau.equals(nhapLaiMatKhau)){
            edt_nhapLaiMatKhau.setText("");
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }
        else if (matKhau.trim().isEmpty()){
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        }
        else if(nhapLaiMatKhau.trim().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if(matKhau.length()<5){
            edt_nhapLaiMatKhau.setText("");
            edt_matKhauDangKy.setText("");
            Toast.makeText(this, "Mật khẩu phải lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
        }

        else if(matcher.find() && matKhau.equals(nhapLaiMatKhau)){
            mAuth.createUserWithEmailAndPassword(email, matKhau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                AlertDialog alertDialog= new SpotsDialog.Builder().setContext(LoginActivity.this).build();
                                alertDialog.setMessage("Đăng ký");
                                alertDialog.show();
                                edt_matKhauDangKy.setText("");
                                edt_nhapLaiMatKhau.setText("");
                                edt_EmailDangKy.setText("");
                                alertDialog.cancel();
                                Toast.makeText(LoginActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Lỗi đăng ký!", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
        else {
            edt_EmailDangKy.setText("");
            Toast.makeText(this, "Sai định dạng email", Toast.LENGTH_SHORT).show();
        }


    }
    private void dangnhap(){
        String email=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        if(email.equals("admin@admin.com")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this ,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập, kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }else if(etUsername.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        }
        else if(etPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if (etPassword.getText().toString().length()<6){
            Toast.makeText(this, "Mật khẩu phải lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i =new Intent(LoginActivity.this,NguoiDungActivity.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập, kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }
    @Override
    public void onBackPressed() {

        if(thoiGian+2000 > System.currentTimeMillis())
        {   thoat.cancel();
            super.onBackPressed();
            return;
        }else {
            thoat= Toast.makeText(this, "Nhấn lần nữa để thoát!!!", Toast.LENGTH_SHORT);
            thoat.show();
        }
        thoiGian=System.currentTimeMillis();

    }


}

