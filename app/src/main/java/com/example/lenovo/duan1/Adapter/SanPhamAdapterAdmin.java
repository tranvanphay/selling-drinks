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

import java.util.ArrayList;

public class SanPhamAdapterAdmin extends RecyclerView.Adapter<SanPhamAdapterAdmin.ViewHolder> {
    ArrayList<SanPham> listsanpham;
    Context context;

    public SanPhamAdapterAdmin(ArrayList<SanPham> listsanpham, Context context) {
        this.listsanpham = listsanpham;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham_admin,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMaSanPham.setText(listsanpham.get(position).getMaSanPham());
        holder.tvTenSanPham.setText(listsanpham.get(position).getTenSanPham());
        holder.ivHinhSanPham.setImageResource(Integer.parseInt(listsanpham.get(position).getHinhSanPham()));
    }

    @Override
    public int getItemCount() {
        return listsanpham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaSanPham;
        TextView tvTenSanPham;
        ImageView ivHinhSanPham;
        public ViewHolder(View itemView) {
            super(itemView);
            tvMaSanPham = (TextView)itemView.findViewById(R.id.tvMaSanPhamAdmin);
            tvTenSanPham = (TextView)itemView.findViewById(R.id.tvTenSanPhamAdmin);
            ivHinhSanPham = (ImageView)itemView.findViewById(R.id.ivHinhSanPhamAdmin);
        }
    }
}

