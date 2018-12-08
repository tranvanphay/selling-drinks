package com.example.lenovo.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapterAdmin extends RecyclerView.Adapter<SanPhamAdapterAdmin.ViewHolder> {
    ArrayList<SanPham> dssp;
    Context context;

    public SanPhamAdapterAdmin(ArrayList<SanPham> dssp, Context context) {
        this.dssp = dssp;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham_admin, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvMaSanPham.setText(dssp.get(position).maSanPham);
        holder.tvTenSanPham.setText(dssp.get(position).tenSanPham);
        Picasso.get().load(dssp.get(position).hinhSanPham).into(holder.ivHinhSanPham);
        holder.imv_menuSanPhamAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_xssanpham, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.xoaSanPham:
                                        Toast.makeText(context, "Xóa sản phẩm", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.suaSanPham:
                                        Dialog dialogSuaSanPham = new Dialog(context);
                                        dialogSuaSanPham.setContentView(R.layout.dialog_suasanpham);
                                        dialogSuaSanPham.show();
                                        break;
                                }


                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });
            }

    @Override
    public int getItemCount() {
        return dssp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaSanPham;
        TextView tvTenSanPham;
        ImageView ivHinhSanPham, imv_menuSanPhamAdmin;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaSanPham = (TextView) itemView.findViewById(R.id.tvMaSanPhamAdmin);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPhamAdmin);
            ivHinhSanPham = (ImageView) itemView.findViewById(R.id.ivHinhSanPhamAdmin);
            imv_menuSanPhamAdmin = (ImageView) itemView.findViewById(R.id.ivMenuSanPhamAdmin);

        }
    }
}

