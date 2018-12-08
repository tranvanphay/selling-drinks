package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.duan1.Adapter.HoaDonDaGiaoAdapter;
import com.example.lenovo.duan1.Model.HoaDonDaGiao;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    ArrayList<HoaDonDaGiao> dshdDaGiao=new ArrayList<HoaDonDaGiao>();
    TextView tv_doanhThuTheoNgay,tv_doanhThuTheoThang,tv_doanhThuTheoNam;
    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_thong_ke,container,false);
        tv_doanhThuTheoNgay=view.findViewById(R.id.tv_doanhThuTheoNgay);
        tv_doanhThuTheoThang=view.findViewById(R.id.tv_doanhThuTheoThang);
        tv_doanhThuTheoNam=view.findViewById(R.id.tv_doanhThuTheoNam);
        doanhThuTheoNgay();
        doanhThuTheoThang();
        doanhThuTheoNam();
        return view;
    }
    private void doanhThuTheoNgay() {
        Date homnay=Calendar.getInstance().getTime();
        SimpleDateFormat ngayFM=new SimpleDateFormat("dd/MM/yyyy");
        String ngay=ngayFM.format(homnay);


        Query query=FirebaseDatabase.getInstance().getReference("HoaDonDaGiao").orderByChild("ngayDaGiao").equalTo(ngay);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                HoaDonDaGiao hoaDonDaGiao=dataSnapshot.getValue(HoaDonDaGiao.class);
                dshdDaGiao.add(hoaDonDaGiao);
                int tongTienThuTheoNgay=0;
                for(int i=0; i<dshdDaGiao.size(); i++){
                    tongTienThuTheoNgay += dshdDaGiao.get(i).tongThanhToan;
                }
                tv_doanhThuTheoNgay.setText(String.valueOf(tongTienThuTheoNgay));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void doanhThuTheoThang() {
        Date thangNay=Calendar.getInstance().getTime();
        SimpleDateFormat thangFM=new SimpleDateFormat("MM/yyyy");
        String thang=thangFM.format(thangNay);


        Query query=FirebaseDatabase.getInstance().getReference("HoaDonDaGiao").orderByChild("thangDaGiao").equalTo(thang);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                HoaDonDaGiao hoaDonDaGiao=dataSnapshot.getValue(HoaDonDaGiao.class);
                dshdDaGiao.add(hoaDonDaGiao);
                int tongTienThuTheoThang=0;
                for(int i=0; i<dshdDaGiao.size(); i++){
                    tongTienThuTheoThang += dshdDaGiao.get(i).tongThanhToan;
                }
                tv_doanhThuTheoThang.setText(String.valueOf(tongTienThuTheoThang));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void doanhThuTheoNam() {
        Date namNay=Calendar.getInstance().getTime();
        SimpleDateFormat namFM=new SimpleDateFormat("yyyy");
        String nam=namFM.format(namNay);


        Query query=FirebaseDatabase.getInstance().getReference("HoaDonDaGiao").orderByChild("namDaGiao").equalTo(nam);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                HoaDonDaGiao hoaDonDaGiao=dataSnapshot.getValue(HoaDonDaGiao.class);
                dshdDaGiao.add(hoaDonDaGiao);
                int tongTienThuTheoNam=0;
                for(int i=0; i<dshdDaGiao.size(); i++){
                    tongTienThuTheoNam += dshdDaGiao.get(i).tongThanhToan;
                }
                tv_doanhThuTheoNam.setText(String.valueOf(tongTienThuTheoNam));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
