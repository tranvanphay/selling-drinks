package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.lenovo.duan1.Adapter.HoaDonAdapter;
import com.example.lenovo.duan1.Adapter.LoaiApdaterAdmin;
import com.example.lenovo.duan1.Adapter.SanPhamAdapterHoaDon;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChuaGiaoFragment extends Fragment {
    RecyclerView recyclerViewChuaGiao;
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    public ChuaGiaoFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chua_giao, container, false);
        recyclerViewChuaGiao=v.findViewById(R.id.recyclerViewChuaGiao);
        loadHoaDon();
        viewHoaDon();
        return v;

    }
        private void loadHoaDon(){
            mData.child("HoaDon").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    HoaDon hoaDon=dataSnapshot.getValue(HoaDon.class);
                    dshd.add(hoaDon);
                    recyclerViewChuaGiao.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    recyclerViewChuaGiao.setLayoutManager(layoutManager);
                    HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(dshd,getContext());
                    recyclerViewChuaGiao.setAdapter(hoaDonAdapter);
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
    public void viewHoaDon() {
        recyclerViewChuaGiao.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewChuaGiao.setLayoutManager(layoutManager);
        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(dshd,getContext());
        recyclerViewChuaGiao.setAdapter(hoaDonAdapter);
    }
}
