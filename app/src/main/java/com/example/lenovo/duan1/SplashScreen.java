package com.example.lenovo.duan1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {
    ImageView ivLogo;
    Animation animation;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ivLogo = findViewById(R.id.ivLogo);
        // Ẩn action bar
        actionBar = getSupportActionBar();
        actionBar.hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        animation = AnimationUtils.loadAnimation(this,R.anim.trans_anim_logo);
        ivLogo.setAnimation(animation);

        Thread timer = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(5000);
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                super.run();
            }
        };
        timer.start();


    }
}
