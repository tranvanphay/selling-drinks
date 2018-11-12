package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.R;
import com.example.lenovo.duan1.model.TestStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HinhAnhAdapter extends BaseAdapter {
    Context c;
    ArrayList<TestStorage> dsh=new ArrayList<TestStorage>();

    public HinhAnhAdapter(Context c, ArrayList<TestStorage> dsh) {
        this.c = c;
        this.dsh = dsh;
    }

    @Override
    public int getCount() {
        return dsh.size();
    }

    @Override
    public Object getItem(int position) {
        return dsh.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)c).getLayoutInflater();
        convertView=inflater.inflate(R.layout.test_one_item_hinh_anh,parent,false);
        TextView tv_tenhinh=convertView.findViewById(R.id.tv_tenhinh);
        ImageView imv_hinh=convertView.findViewById(R.id.imv_show);
        tv_tenhinh.setText(dsh.get(position).tenHinh);
        Picasso.get().load(dsh.get(position).linkHinh).into(imv_hinh);



        return convertView;
    }
}
