package com.example.lenovo.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_loai_admin, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvMaLoai.setText(dsl.get(position).maLoai);
        holder.tvTenLoai.setText(dsl.get(position).tenLoai);
        Picasso.get().load(dsl.get(position).hinhLoai).into(holder.ivHinhLoai);
        holder.ivMenuXoaSuaLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_xsloai, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.xoaLoai:
                                        Toast.makeText(context, "Xóa sản phẩm", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.suaLoai:
                                        Dialog dialogSuaLoai = new Dialog(context);
                                        dialogSuaLoai.setContentView(R.layout.dialog_sualoai);
                                        dialogSuaLoai.show();
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
        return dsl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLoai;
        TextView tvTenLoai;
        ImageView ivHinhLoai;
        ImageView ivMenuXoaSuaLoai;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaLoai = (TextView) itemView.findViewById(R.id.tvMaLoaiAdmin);
            tvTenLoai = (TextView) itemView.findViewById(R.id.tvTenLoaiAdmin);
            ivHinhLoai = (ImageView) itemView.findViewById(R.id.ivHinhLoaiAdmin);
            ivMenuXoaSuaLoai = (ImageView) itemView.findViewById(R.id.ivMenuLoaiAdmin);
        }
    }
}
