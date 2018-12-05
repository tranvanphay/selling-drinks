package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    ArrayList<GioHang> dsgh;
    Context context;

    public GioHangAdapter(ArrayList<GioHang> dsgh, Context context) {
        this.dsgh = dsgh;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_giohang,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTenSanPhamGioHang.setText(dsgh.get(position).tenSanPham);
        holder.tvSoLuongSanPhamGioHang.setText(dsgh.get(position).soLuong);
        holder.tvGiaTienSanPhamGioHang.setText(dsgh.get(position).giaTien);
        Picasso.get().load(dsgh.get(position).linkHinh).into(holder.ivHinhSanPhamGioHang);
    }

    @Override
    public int getItemCount() {
        return dsgh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSanPhamGioHang;
        TextView tvSoLuongSanPhamGioHang;
        TextView tvGiaTienSanPhamGioHang;
        ImageView ivHinhSanPhamGioHang;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTenSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvTenSanPhamGioHang);
            tvSoLuongSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvSoLuongSanPhamGioHang);
            tvGiaTienSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvGiaTienSanPhamGioHang);
            ivHinhSanPhamGioHang = (ImageView)itemView.findViewById(R.id.ivHinhSanPhamGioHang);
        }
    }
}

