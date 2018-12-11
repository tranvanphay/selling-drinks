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
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout_title;
    ImageView ivLogo;
    EditText etUsername, etPassword;
    EditText edt_EmailDangKy, edt_matKhauDangKy, edt_nhapLaiMatKhau;
    LinearLayout linearLayoutLogin;
    Button btnDangNhap, btnDangKy, btnQuenMatKhau;
    FirebaseAuth mAuth;
    private long thoiGian;
    AlertDialog alertDialog;
    private Toast thoat;
    GoogleApiClient googleApiClient;
    private CallbackManager mCallbackManager;
    public static final String TAG = "FACELOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        constraintLayout_title = findViewById(R.id.constraintLayout_title);
        ivLogo = findViewById(R.id.ivLogo);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        linearLayoutLogin = findViewById(R.id.linearLayoutLogin);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);

        GoogleSignInButton dangNhapGoogle = (GoogleSignInButton) findViewById(R.id.bt_dangNhapGoogle);


// Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        dangNhapGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).build();
                alertDialog.setMessage("Đăng nhập");
                alertDialog.show();
                signIn();
            }
        });


//Check kết nối mạng

        if (checkNetwork()) {
            //Connected to the Internet

        } else {
            //Not connected
            final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setPositiveButton("Bật wifi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                }
            });
            builder.setTitle("Lỗi");
            builder.setMessage("Bạn chưa kết nối mạng!!!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }


//Hiệu ứng animation
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Animation animation_logo = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        Animation animation_title = AnimationUtils.loadAnimation(this, R.anim.anim_title);
        Animation animation_dangnhap = AnimationUtils.loadAnimation(this, R.anim.anim_khunglogin);
        Animation animation_buttondangnhap = AnimationUtils.loadAnimation(this, R.anim.anim_button_dangnhap);
        Animation animation_buttondangky = AnimationUtils.loadAnimation(this, R.anim.anim_button_dangky);
        Animation animation_google = AnimationUtils.loadAnimation(this, R.anim.anim_button_dangnhap);

        AnimationSet animationSet_logo = new AnimationSet(false);//false means don't share interpolators

        animationSet_logo.addAnimation(animation_logo);
        animationSet_logo.setFillAfter(true);
        ivLogo.startAnimation(animationSet_logo);

        constraintLayout_title.startAnimation(animation_title);
        linearLayoutLogin.startAnimation(animation_dangnhap);
        btnDangNhap.startAnimation(animation_buttondangnhap);
        btnDangKy.startAnimation(animation_buttondangky);
        btnQuenMatKhau.startAnimation(animation_buttondangky);
        dangNhapGoogle.startAnimation(animation_google);


//Đăng nhập
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).build();
                alertDialog.setMessage("Đăng nhập");
                alertDialog.show();
                dangnhap();
            }
        });


//Đăng kí
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialogDangKy = new Dialog(LoginActivity.this);
                dialogDangKy.setContentView(R.layout.dialog_dangky);
                edt_EmailDangKy = dialogDangKy.findViewById(R.id.edt_EmailDangKy);
                edt_matKhauDangKy = dialogDangKy.findViewById(R.id.edt_PasswordDangKy);
                edt_nhapLaiMatKhau = dialogDangKy.findViewById(R.id.edt_nhapLaiMatKhau);
                Button bt_xacNhanDangKy = dialogDangKy.findViewById(R.id.bt_xacNhanDangKy);
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


//Quên mật khẩu
        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogQuenMK = new Dialog(LoginActivity.this);
                dialogQuenMK.setContentView(R.layout.dialog_quenmatkhau);
                dialogQuenMK.show();

                final EditText edt_emailQuenMatKhau = dialogQuenMK.findViewById(R.id.edt_emailQuenMatKhau);
                Button bt_xacNhanQuenMatKhau = dialogQuenMK.findViewById(R.id.bt_xacNhanQuenMatKhau);
                bt_xacNhanQuenMatKhau.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        String emailAddress = edt_emailQuenMatKhau.getText().toString();

                        auth.sendPasswordResetEmail(emailAddress)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Email sent.");
                                            Toast.makeText(LoginActivity.this, "Đã gửi mail đến hộp thư của bạn!", Toast.LENGTH_SHORT).show();
                                            dialogQuenMK.dismiss();
                                        }
                                    }
                                });
                    }
                });

                ImageView ivCloseDialogQuenMK = dialogQuenMK.findViewById(R.id.ivCloseDialogQuenMatKhau);
                ivCloseDialogQuenMK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogQuenMK.cancel();
                    }
                });
            }
        });

        //Facebook_login
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = findViewById(R.id.bt_loginFacebook);
//        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.startAnimation(animation_buttondangnhap);
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Intent intent=new Intent(LoginActivity.this,NguoiDungActivity.class);
//                startActivity(intent);
//                finish();
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//                Toast.makeText(LoginActivity.this, "Hủy", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//                Toast.makeText(LoginActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

//Login with Google authencetion
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("google", "Google sign in failed", e);
                // ...
            }
        }
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("google", "signInWithCredential:success");
                            Intent i = new Intent(LoginActivity.this, NguoiDungActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("google", "signInWithCredential:failure", task.getException());

                        }

                        // ...
                    }
                });
    }


//Check network
    private boolean checkNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

//Đăng ký
    private void dangky() {
        String email = edt_EmailDangKy.getText().toString();
        String matKhau = edt_matKhauDangKy.getText().toString();
        String nhapLaiMatKhau = edt_nhapLaiMatKhau.getText().toString();

        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (!matKhau.equals(nhapLaiMatKhau)) {
            edt_nhapLaiMatKhau.setText("");
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        } else if (matKhau.trim().isEmpty()) {
            Toast.makeText(this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        } else if (nhapLaiMatKhau.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (matKhau.length() < 5) {
            edt_nhapLaiMatKhau.setText("");
            edt_matKhauDangKy.setText("");
            Toast.makeText(this, "Mật khẩu phải lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
        } else if (matcher.find() && matKhau.equals(nhapLaiMatKhau)) {
            mAuth.createUserWithEmailAndPassword(email, matKhau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                AlertDialog alertDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).build();
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
        } else {
            edt_EmailDangKy.setText("");
            Toast.makeText(this, "Sai định dạng email", Toast.LENGTH_SHORT).show();
        }


    }


//Đăng nhập
    private void dangnhap() {
        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (email.equals("admin@admin.com")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập, kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                                alertDialog.cancel();
                            }

                            // ...
                        }
                    });

        } else if (etUsername.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
            alertDialog.cancel();
        } else if (etPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            alertDialog.cancel();
        } else if (etPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Mật khẩu phải lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
            alertDialog.cancel();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(LoginActivity.this, NguoiDungActivity.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập, kiểm tra lại tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                                alertDialog.cancel();
                            }

                        }
                    });
        }
    }


//delay màn hình đăng nhập 2 giây
    @Override
    public void onBackPressed() {

        if (thoiGian + 2000 > System.currentTimeMillis()) {
            thoat.cancel();
            super.onBackPressed();
            return;
        } else {
            thoat = Toast.makeText(this, "Nhấn lần nữa để thoát!!!", Toast.LENGTH_SHORT);
            thoat.show();
        }
        thoiGian = System.currentTimeMillis();

    }
//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        // ...
//                    }
//                });
//    }

}

