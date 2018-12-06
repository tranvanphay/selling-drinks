package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder>  {
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
        holder.tvSoLuongSanPhamGioHang.setText(String.valueOf(dsgh.get(position).soLuong));
        holder.tvGiaTienSanPhamGioHang.setText(String.valueOf(dsgh.get(position).giaTien));
        holder.imv_xoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("GioHang");
                GioHang gioHang=dsgh.get(position);
                myRef.child(gioHang.getKeyGioHang()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posittion) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dsgh.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTenSanPhamGioHang;
        TextView tvSoLuongSanPhamGioHang;
        TextView tvGiaTienSanPhamGioHang;
        ImageView imv_xoaGioHang;
        private ItemClickListener itemClickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTenSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvTenSanPhamGioHang);
            tvSoLuongSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvSoLuongSanPhamGioHang);
            tvGiaTienSanPhamGioHang = (TextView)itemView.findViewById(R.id.tvGiaTienSanPhamGioHang);
            imv_xoaGioHang = (ImageView) itemView.findViewById(R.id.imv_xoaGioHang);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

}

