package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_thong_ke,container,false);
        return view;
    }
    private void doanhThuTheoNgay() {
        Date homnay=Calendar.getInstance().getTime();
        SimpleDateFormat ngayFM=new SimpleDateFormat("dd");
        String ngay=ngayFM.format(homnay);


        Query query=FirebaseDatabase.getInstance().getReference("HoaDonDaGiao").orderByChild("NgayDaGiao").equalTo(ngay);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                HoaDonDaGiao hoaDonDaGiao=dataSnapshot.getValue(HoaDonDaGiao.class);

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











        mData.child("HoaDonDaGiao").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HoaDonDaGiao hoaDonDaGiao=dataSnapshot.getValue(HoaDonDaGiao.class);
                hoaDonDaGiao.setKeyHoaDonDaGiao(dataSnapshot.getKey());
                dshdDaGiao.add(hoaDonDaGiao);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
