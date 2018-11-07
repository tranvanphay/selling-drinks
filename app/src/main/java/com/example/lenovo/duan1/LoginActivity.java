package com.example.lenovo.duan1;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.model.AccountNguoiDung;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Animation animation;
 ActionBar actionBar;
 EditText etUserName,etPassword;
 Button btDangNhap;
 TextView tvDangKy,tvQuenMK;
 AccountNguoiDung accountNguoiDung;
 DatabaseReference mData;
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
                Button bt_dangky;
                ImageView image_close;
                final EditText et_ten_nguoi_dung,et_ten_dang_nhap,et_mat_khau,et_sdt;
                final Dialog dialog=new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_dang_ky);
                et_ten_nguoi_dung=dialog.findViewById(R.id.et_tennguoidung);
                et_ten_dang_nhap=dialog.findViewById(R.id.et_tendangnhap);
                et_mat_khau=dialog.findViewById(R.id.et_matkhau);
                et_sdt=dialog.findViewById(R.id.et_sodienthoai);
                bt_dangky=dialog.findViewById(R.id.bt_dang_ky);
                image_close=dialog.findViewById(R.id.img_close_form_register);
                // Bắt sự kiện nút đăng ký
                bt_dangky.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tennguoidung=et_ten_nguoi_dung.getText().toString();
                        String tendangnhap=et_ten_dang_nhap.getText().toString();
                        String matkhau=et_mat_khau.getText().toString();
                        String sodienthoai=et_sdt.getText().toString();
                        accountNguoiDung=new AccountNguoiDung(tendangnhap,matkhau,sodienthoai,tennguoidung);
                        mData=FirebaseDatabase.getInstance().getReference();
                        mData.child("Account Người Dùng").push().setValue(accountNguoiDung, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    Toast.makeText(LoginActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        dialog.dismiss();


                    }
                });
                image_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();


            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button bt_xac_nhan;
                ImageView img_close;
                final EditText et_ten_dang_nhap,et_sdt;
                final Dialog dialog=new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.dialog_quen_mat_khau);
                bt_xac_nhan=dialog.findViewById(R.id.bt_xac_nhan);
                img_close=dialog.findViewById(R.id.img_close_forgot_form);
                et_ten_dang_nhap=dialog.findViewById(R.id.et_tendn);
                et_sdt=dialog.findViewById(R.id.et_sdt);
                bt_xac_nhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tendangnhap=et_ten_dang_nhap.getText().toString();
                        String sdt=et_sdt.getText().toString();
                    }
                });
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });



    }
}
