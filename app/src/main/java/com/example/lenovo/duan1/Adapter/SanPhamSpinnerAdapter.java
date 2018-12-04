package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class SanPhamSpinnerAdapter extends BaseAdapter {
    ArrayList<Loai> dsl=new ArrayList<Loai>();
    Context c;
    public SanPhamSpinnerAdapter(Context c , ArrayList<Loai> dsl){
        this.c=c;
        this.dsl=dsl;
    }

    @Override
    public int getCount() {
        return dsl.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //gan layout
        LayoutInflater inf=((Activity)c).getLayoutInflater();
        view=inf.inflate(R.layout.one_item_spinner_loai,viewGroup,false);
        TextView tv_tenloai=view.findViewById(R.id.tv_spinner);
        Loai loai= dsl.get(i);
        tv_tenloai.setText( loai.tenLoai);


        return view;
    }
}
