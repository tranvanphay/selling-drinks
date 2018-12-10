package com.example.lenovo.duan1;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lenovo.duan1.AdminFragment.HoaDonFragment;
import com.example.lenovo.duan1.AdminFragment.ThongKeFragment;
import com.example.lenovo.duan1.AdminFragment.TrangChuAdminFragment;

public class AdminActivity extends AppCompatActivity {
    private long thoiGian;
    private Toast thoat;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_admin);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new TrangChuAdminFragment());

    }

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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.trangChuAdmin:
                    fragment = new TrangChuAdminFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.hoaDon:
                    fragment = new HoaDonFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.thongKe:
                    fragment = new ThongKeFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_Admin, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void goToFragmentHoaDon(View view) {
        Fragment fragment = new HoaDonFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_Admin, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        bottomNavigationView.setSelectedItemId(R.id.hoaDon);

    }

    public void goToFragmentThongKe(View view) {
        Fragment fragment = new ThongKeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_Admin, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        bottomNavigationView.setSelectedItemId(R.id.thongKe);

    }

}
