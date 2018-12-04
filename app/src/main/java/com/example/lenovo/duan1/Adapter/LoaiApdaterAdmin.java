package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiApdaterAdmin extends RecyclerView.Adapter<LoaiApdaterAdmin.ViewHolder> {
    ArrayList<Loai> dsl;
    Context context;

    public LoaiApdaterAdmin(ArrayList<Loai> dsl, Context context) {
        this.dsl = dsl;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_loai_admin,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMaLoai.setText(dsl.get(position).maLoai);
        holder.tvTenLoai.setText(dsl.get(position).tenLoai);
        Picasso.get().load(dsl.get(position).hinhLoai).into(holder.ivHinhLoai);
    }

    @Override
    public int getItemCount() {
        return dsl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaLoai;
        TextView tvTenLoai;
        ImageView ivHinhLoai;
        public ViewHolder(View itemView) {
            super(itemView);
            tvMaLoai = (TextView)itemView.findViewById(R.id.tvMaLoaiAdmin);
            tvTenLoai = (TextView)itemView.findViewById(R.id.tvTenLoaiAdmin);
            ivHinhLoai = (ImageView)itemView.findViewById(R.id.ivHinhLoaiAdmin);
        }
    }
}
