package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapterNguoiDung extends RecyclerView.Adapter<SanPhamAdapterNguoiDung.ViewHolder> {
    ArrayList<SanPham> dsspNguoiDung;
    Context context;

    public SanPhamAdapterNguoiDung(ArrayList<SanPham> dsspNguoiDung, Context context) {
        this.dsspNguoiDung = dsspNguoiDung;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham_nguoidung,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTenSanPham.setText(dsspNguoiDung.get(position).tenSanPham);
        holder.tvGiaTien.setText(dsspNguoiDung.get(position).giaTien);
        Picasso.get().load(dsspNguoiDung.get(position).hinhSanPham).into(holder.ivHinhSanPham);
    }

    @Override
    public int getItemCount() {
        return dsspNguoiDung.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSanPham;
        TextView tvGiaTien;
        ImageView ivHinhSanPham;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTenSanPham = (TextView)itemView.findViewById(R.id.tvTenSanPhamNguoiDung);
            tvGiaTien = (TextView)itemView.findViewById(R.id.tvGiaTienSanPhamNguoiDung);
            ivHinhSanPham = (ImageView)itemView.findViewById(R.id.ivHinhSanPhamNguoiDung);
        }
    }
}

