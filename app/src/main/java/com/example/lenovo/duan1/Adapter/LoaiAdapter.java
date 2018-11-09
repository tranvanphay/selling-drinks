package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.AdminActivity;
import com.example.lenovo.duan1.AdminFragment.LoaiFragment;
import com.example.lenovo.duan1.MainActivity;
import com.example.lenovo.duan1.R;
import com.example.lenovo.duan1.model.Loai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoaiAdapter extends BaseAdapter {
    ArrayList<Loai> dsl;
    Context c;
    public LoaiAdapter(ArrayList<Loai> dsl, Context c) {
        this.dsl = dsl;
        this.c = c;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dsl.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.one_item_loai,null);
        final TextView tv_one_item_loai=convertView.findViewById(R.id.tv_one_item_loai);
        ImageView img_delete=convertView.findViewById(R.id.img_delete);
        final Loai loai = dsl.get(position);
        tv_one_item_loai.setText(loai._idLoai+"\n"+loai.tenLoai);
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Loai_table");
                myRef.child(loai.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(c, "Xóa thành công", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });
        return convertView;
    }


}
