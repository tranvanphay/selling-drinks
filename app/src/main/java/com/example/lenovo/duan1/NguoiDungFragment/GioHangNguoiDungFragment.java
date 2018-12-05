package com.example.lenovo.duan1.NguoiDungFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.duan1.Adapter.GioHangAdapter;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangNguoiDungFragment extends Fragment {
    ArrayList<GioHang> dsgh = new ArrayList<>();
    SearchView searchViewgiohang;
    RecyclerView recyclerViewSanPhamGioHang;
    public GioHangNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gio_hang_nguoi_dung, container, false);
        searchViewgiohang=v.findViewById(R.id.svgiohang);
        recyclerViewSanPhamGioHang=v.findViewById(R.id.recyclerViewSanPhamGioHang);
        return v;
    }

    public void viewGioHang(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewSanPhamGioHang.setLayoutManager(layoutManager);
        recyclerViewSanPhamGioHang.setHasFixedSize(true);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(dsgh,getContext());
        recyclerViewSanPhamGioHang.setAdapter(gioHangAdapter);

    }

}
