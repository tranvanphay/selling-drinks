package com.example.lenovo.duan1.NguoiDungFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.duan1.Adapter.HoaDonDaGiaoAdapter;
import com.example.lenovo.duan1.Model.HoaDonDaGiao;
import com.example.lenovo.duan1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuGiaoDichFragment extends Fragment {

    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    ArrayList<HoaDonDaGiao> dshdDaGiao=new ArrayList<HoaDonDaGiao>();
    RecyclerView recyclerViewDaGiao;
    HoaDonDaGiaoAdapter hoaDonDaGiaoAdapter;


    public LichSuGiaoDichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_da_giao,container,false);
        recyclerViewDaGiao=v.findViewById(R.id.recyclerViewDaGiao);
        loadLichSu();
        return  v;
    }
    private  void loadLichSu(){
        String user=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Query query =FirebaseDatabase.getInstance().getReference("HoaDonDaGiao").orderByChild("user").equalTo(user);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                HoaDonDaGiao lichSu=dataSnapshot.getValue(HoaDonDaGiao.class);
                lichSu.setKeyHoaDonDaGiao(dataSnapshot.getKey());
                dshdDaGiao.add(lichSu);
                recyclerViewDaGiao.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewDaGiao.setLayoutManager(layoutManager);
                hoaDonDaGiaoAdapter = new HoaDonDaGiaoAdapter(dshdDaGiao,getContext());
                recyclerViewDaGiao.setAdapter(hoaDonDaGiaoAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < dshdDaGiao.size(); i++) {
                    if (dshdDaGiao.get(i).getKeyHoaDonDaGiao().equals(key)) {
                        dshdDaGiao.remove(i);
                        break;
                    }

                }
                hoaDonDaGiaoAdapter.notifyDataSetChanged();

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
