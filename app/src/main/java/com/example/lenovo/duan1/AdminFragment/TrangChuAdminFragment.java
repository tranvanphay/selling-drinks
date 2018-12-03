package com.example.lenovo.duan1.AdminFragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuAdminFragment extends Fragment {
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    TextView tvCurrentDate;
    ImageView ivThemLoai, ivThemSanPham;
    RecyclerView recyclerViewBangTin,recyclerViewLoai,recyclerViewSanPham;

    public TrangChuAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_trang_chu_admin, container, false);
        recyclerViewLoai = view.findViewById(R.id.recyclerViewLoai);
        recyclerViewSanPham = view.findViewById(R.id.recyclerViewSanPham);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTin);
        tvCurrentDate = view.findViewById(R.id.tvCurrentDate);
        ivThemLoai = view.findViewById(R.id.ivThemLoai);
        ivThemSanPham = view.findViewById(R.id.ivThemSanPham);



        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
        String formattedDate = df.format(c);
        tvCurrentDate.setText(formattedDate);

        getBangTin();

        ivThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemLoai = new Dialog(getActivity());
                dialogThemLoai.setContentView(R.layout.dialog_addsanpham);
                dialogThemLoai.show();

                ImageView ivCloseDialogThemLoai = dialogThemLoai.findViewById(R.id.ivCloseDialogThemLoai);
                ivCloseDialogThemLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThemLoai.cancel();
                    }
                });
            }
        });

        ivThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemSanPham = new Dialog(getActivity());
                dialogThemSanPham.setContentView(R.layout.dialog_addsanpham);
                dialogThemSanPham.show();

                ImageView ivCloseDialogThemSanPham = dialogThemSanPham.findViewById(R.id.ivCloseDialogThemSanPham);
                ivCloseDialogThemSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThemSanPham.cancel();
                    }
                });
            }
        });



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





