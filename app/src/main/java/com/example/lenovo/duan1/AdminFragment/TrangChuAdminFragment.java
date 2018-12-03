package com.example.lenovo.duan1.AdminFragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.Adapter.SanPhamAdapter;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.SanPham;
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
    ImageView ivMenuThemLoai;
    RecyclerView recyclerViewBangTin,recyclerViewLoaiVaSanPham;
    public TrangChuAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trang_chu_admin,container,false);
        recyclerViewLoaiVaSanPham = view.findViewById(R.id.recyclerViewLoaiVaSanPham);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTin);
        tvCurrentDate = view.findViewById(R.id.tvCurrentDate);
        ivMenuThemLoai = view.findViewById(R.id.ivMenuThemLoai);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy"	);
        String formattedDate = df.format(c);
        tvCurrentDate.setText(formattedDate);


        getBangTin();

        ivMenuThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemLoai = new Dialog(getActivity());
                dialogThemLoai.setContentView(R.layout.dialog_addmenuloai);
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



        ArrayList<Loai> loais = new ArrayList<>();

        ArrayList<SanPham> sanPham1 = new ArrayList<>();
        sanPham1.add(new SanPham("1","maLoai","tra sua tran chau","rat ngon","50000"));
        sanPham1.add(new SanPham("2","maLoai","tra tran chau","rat ngon","50000"));

        Loai loai1 = new Loai("Loại 1", sanPham1);
        loais.add(loai1);

        ArrayList<SanPham> sanPham2 = new ArrayList<>();
        sanPham2.add(new SanPham("4","maLoai","tra hoa qua","rat ngon","50000"));
        sanPham2.add(new SanPham("5","maLoai","sua tuoi","rat ngon","50000"));

        Loai loai2 = new Loai("Loại 2", sanPham2);
        loais.add(loai2);

        SanPhamAdapter adapter = new SanPhamAdapter(loais);
        recyclerViewLoaiVaSanPham.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewLoaiVaSanPham.setAdapter(adapter);

        return view;


    }


    // Popup menu của loại
//    public void menuTXSLoai(View view) {
//        final ImageButton imgButtonMenuLoai = view.findViewById(R.id.imageButtonMenuLoai);
//
//        imgButtonMenuLoai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), imgButtonMenuLoai);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_txsloai, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//                            case R.id.xemLoai:
//                                Toast.makeText(MainActivity.this, "Xem", Toast.LENGTH_SHORT).show();
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });
//
//    }

    private void getBangTin(){
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

    private void recyclerViewBangTin(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBangTin.setLayoutManager(layoutManager);
        BangTinAdapter bangTinAdapter = new BangTinAdapter(getActivity(),tenBangTin,hinhBangTin);
        recyclerViewBangTin.setAdapter(bangTinAdapter);

    }
}




