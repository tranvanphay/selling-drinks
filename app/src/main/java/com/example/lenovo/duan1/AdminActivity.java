package com.example.lenovo.duan1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.lenovo.duan1.AdminFragment.HoaDonFragment;
import com.example.lenovo.duan1.AdminFragment.LoaiFragment;
import com.example.lenovo.duan1.AdminFragment.ThongKeFragment;
import com.example.lenovo.duan1.AdminFragment.ThongTinAdminFragment;
import com.example.lenovo.duan1.AdminFragment.TrangChuAdminFragment;
import com.example.lenovo.duan1.NguoiDungFragment.ThanhToanNguoiDungFragment;
import com.example.lenovo.duan1.NguoiDungFragment.ThongTinNguoiDungFragment;
import com.example.lenovo.duan1.NguoiDungFragment.TrangChuNguoiDungFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_admin);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new TrangChuAdminFragment());

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
                case R.id.loai:
                    fragment = new LoaiFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.thongTinAdmin:
                    fragment = new ThongTinAdminFragment();
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

}
