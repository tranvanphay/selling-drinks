package com.example.lenovo.duan1.NguoiDungFragment;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuNguoiDungFragment extends Fragment {
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    RecyclerView recyclerViewBangTin;

    public TrangChuNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trang_chu_nguoi_dung, container, false);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTinNguoiDung);
        getBangTin();
        return view;
    }
    private void getBangTin() {
        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/header-img.jpg");
        tenBangTin.add("CYBER MONDAY - Giảm giá 50% trên tổng hóa đơn");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img01.jpg");
        tenBangTin.add("Cùng trải nghiệm không gian mới của DEER TEA");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img02.jpg");
        tenBangTin.add("Cà phê DEER TEA cho ngày dài năng động!");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img04.jpg");
        tenBangTin.add("Sinh nhật DEER TEA, nhân đôi các loại thức uống");

        recyclerViewBangTin();
    }

    private void recyclerViewBangTin() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBangTin.setLayoutManager(layoutManager);
        BangTinAdapter bangTinAdapter = new BangTinAdapter(getActivity(), tenBangTin, hinhBangTin);
        recyclerViewBangTin.setAdapter(bangTinAdapter);
    }
}
