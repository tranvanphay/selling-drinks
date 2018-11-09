package com.example.lenovo.duan1.AdminFragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.LoaiAdapter;
import com.example.lenovo.duan1.R;
import com.example.lenovo.duan1.model.Loai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiFragment extends Fragment {
    ListView lv_loai;
    FloatingActionButton flb_loai;
    private DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    Loai loai;
    ArrayList<Loai> dsl=new ArrayList<Loai>();
    LoaiAdapter adapter=new LoaiAdapter(dsl,getActivity());
    public LoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_loai,container,false);
        lv_loai=v.findViewById(R.id.listloai);
        flb_loai=v.findViewById(R.id.flbloai);
        flb_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt_them;
                ImageView img_close;
                final EditText et_ma_loai,et_ten_Loai;
                final Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_them_loai);
                bt_them=dialog.findViewById(R.id.bt_them_loai);
                img_close=dialog.findViewById(R.id.img_close_them_loai);
                et_ten_Loai=dialog.findViewById(R.id.etTenLoai);
                et_ma_loai=dialog.findViewById(R.id.etMaLoai);

                bt_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenloai=et_ten_Loai.getText().toString();
                        String maloai=et_ma_loai.getText().toString();
                        loai=new Loai(maloai,tenloai);
                        mData=FirebaseDatabase.getInstance().getReference();
                        mData.child("Loai_table").push().setValue(loai, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        dialog.dismiss();
                    }
                });

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });
        mData.child("Loai_table").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Loai loai=dataSnapshot.getValue(Loai.class);
                loai.setKey(dataSnapshot.getKey());
                dsl.add(loai);
                adapter = new LoaiAdapter(dsl, getActivity());
                lv_loai.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                lv_loai.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < dsl.size(); i++) {
                    if (dsl.get(i).key.equals(key)) {
                        dsl.remove(i);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();

/*
                    adapter.notifyDataSetChanged();
                lv_loai.setAdapter(adapter);*/
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    adapter.notifyDataSetChanged();
                lv_loai.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                adapter.notifyDataSetChanged();
                lv_loai.setAdapter(adapter);
            }
        });

        return v;
    }




}
