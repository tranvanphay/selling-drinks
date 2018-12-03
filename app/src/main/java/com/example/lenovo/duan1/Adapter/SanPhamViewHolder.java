package com.example.lenovo.duan1.Adapter;

import android.view.View;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SanPhamViewHolder extends ChildViewHolder {
    private TextView tvTenSanPham;

    public SanPhamViewHolder(View itemView) {
        super(itemView);
        tvTenSanPham = itemView.findViewById(R.id.tvSanPham);
    }

    public void bind(SanPham sp) {
        tvTenSanPham.setText(sp.tenSanPham);
    }
}
