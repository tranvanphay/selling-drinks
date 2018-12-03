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

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    ArrayList<SanPham> listsanpham;
    Context context;

    public SanPhamAdapter(ArrayList<SanPham> listsanpham, Context context) {
        this.listsanpham = listsanpham;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMaSanPham.setText(listsanpham.get(position).getMaSanPham());
        holder.tvTenSanPham.setText(listsanpham.get(position).getTenSanPham());
        holder.ivHinhSanPham.setImageResource(listsanpham.get(position).getHinhSanPham());
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
            tvMaSanPham = (TextView)itemView.findViewById(R.id.tvMaSanPham);
            tvTenSanPham = (TextView)itemView.findViewById(R.id.tvTenSanPham);
            ivHinhSanPham = (ImageView)itemView.findViewById(R.id.ivHinhSanPham);
        }
    }
}

