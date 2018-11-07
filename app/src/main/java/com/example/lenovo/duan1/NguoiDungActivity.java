package com.example.lenovo.duan1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.lenovo.duan1.NguoiDungFragment.GioHangNguoiDungFragment;
import com.example.lenovo.duan1.NguoiDungFragment.ThanhToanNguoiDungFragment;
import com.example.lenovo.duan1.NguoiDungFragment.ThongTinNguoiDungFragment;
import com.example.lenovo.duan1.NguoiDungFragment.TrangChuNguoiDungFragment;

public class NguoiDungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new TrangChuNguoiDungFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.trangChuNguoiDung:
                    fragment = new TrangChuNguoiDungFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.thanhToan:
                    fragment = new ThanhToanNguoiDungFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.thongTinNguoiDung:
                    fragment = new ThongTinNguoiDungFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

