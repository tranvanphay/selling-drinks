package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.duan1.Adapter.HoaDonAdapter;
import com.example.lenovo.duan1.Adapter.HoaDonDangGiaoAdapter;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.Model.HoaDonDangGiao;
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
public class DangGiaoFragment extends Fragment {

    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    ArrayList<HoaDonDangGiao> dshdDangGiao=new ArrayList<HoaDonDangGiao>();
    RecyclerView recyclerViewDangGiao;
    HoaDonDangGiaoAdapter hoaDonDangGiaoAdapter;
    public DangGiaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dang_giao,container,false);
        recyclerViewDangGiao=v.findViewById(R.id.recyclerViewDangGiao);
        loadHoaDonDangGiao();
        return  v;
    }
    private void loadHoaDonDangGiao(){
        mData.child("HoaDonDangGiao").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HoaDonDangGiao hoaDonDangGiao=dataSnapshot.getValue(HoaDonDangGiao.class);
                hoaDonDangGiao.setKeyHoaDonDangGiao(dataSnapshot.getKey());
                dshdDangGiao.add(hoaDonDangGiao);
                recyclerViewDangGiao.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recyclerViewDangGiao.setLayoutManager(layoutManager);
                hoaDonDangGiaoAdapter = new HoaDonDangGiaoAdapter(dshdDangGiao,getContext());
                recyclerViewDangGiao.setAdapter(hoaDonDangGiaoAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < dshdDangGiao.size(); i++) {
                    if (dshdDangGiao.get(i).getKeyHoaDonDangGiao().equals(key)) {
                        dshdDangGiao.remove(i);
                        break;
                    }

                }
                hoaDonDangGiaoAdapter.notifyDataSetChanged();
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
