package com.example.lenovo.duan1.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class LoaiViewHolder extends GroupViewHolder {
    private TextView tvTenLoai;

    public LoaiViewHolder(View itemView) {
        super(itemView);

        tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
    }

    public void bind(Loai loai){
        tvTenLoai.setText(loai.getTitle());
    }
}
