package com.example.lenovo.duan1.NguoiDungFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangNguoiDungFragment extends Fragment {
    SearchView searchViewgiohang;
    ListView lvgiohang;
    public GioHangNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gio_hang_nguoi_dung, container, false);
        searchViewgiohang=v.findViewById(R.id.svgiohang);
        lvgiohang=v.findViewById(R.id.lv_giohang);
        return v;
    }
}
