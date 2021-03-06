package com.example.lenovo.duan1.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class SanPhamAdapterNguoiDung extends RecyclerView.Adapter<SanPhamAdapterNguoiDung.ViewHolder> {
    ArrayList<SanPham> dsspNguoiDung;
    Context context;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    public SanPhamAdapterNguoiDung(ArrayList<SanPham> dsspNguoiDung, Context context) {
        this.dsspNguoiDung = dsspNguoiDung;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham_nguoidung, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTenSanPham.setText(dsspNguoiDung.get(position).getTenSanPham());
        holder.tvGiaTien.setText(String.valueOf(dsspNguoiDung.get(position).getGiaTien()));
        Picasso.get().load(dsspNguoiDung.get(position).getHinhSanPham()).into(holder.ivHinhSanPham);

        holder.imv_themVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_thongtinsanpham);
                final EditText et_soluong=dialog.findViewById(R.id.etSoLuongSanPham);
                ImageView ivCloseDialogThemSanPham=dialog.findViewById(R.id.ivCloseDialogThemSanPham);
                ImageView ivHinhThongTinSanPham=dialog.findViewById(R.id.ivHinhThongTinSanPham);
                TextView tvTenSanPhamThemVaoGioHang=dialog.findViewById(R.id.tvTenSanPhamThemVaoGioHang);
                TextView tvChuThichSanPhamThemVaoGioHang=dialog.findViewById(R.id.tvChuThichSanPhamThemVaoGioHang);
                ivCloseDialogThemSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                tvTenSanPhamThemVaoGioHang.setText(dsspNguoiDung.get(position).getTenSanPham());
                tvChuThichSanPhamThemVaoGioHang.setText(dsspNguoiDung.get(position).getChuThich());
                Picasso.get().load(dsspNguoiDung.get(position).getHinhSanPham()).into(ivHinhThongTinSanPham);
                Button bt_them=dialog.findViewById(R.id.btnThemSanPhamVaoGioHang);
                ImageView iv_congsl=dialog.findViewById(R.id.ivcongsl);
                ImageView iv_trusl=dialog.findViewById(R.id.ivtrusl);

                iv_congsl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int a,b;
                        a=Integer.parseInt(et_soluong.getText().toString());
                        b=a+1;
                        et_soluong.setText(String.valueOf(b));
                    }
                });
                iv_trusl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int a,b;
                        a=Integer.parseInt(et_soluong.getText().toString());
                        b=a-1;
                        if (a==0)
                            b=0;
                        et_soluong.setText(String.valueOf(b));
                    }
                });
                dialog.show();
                bt_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog xuLyThem = new SpotsDialog.Builder().setContext(context).build();
                        xuLyThem.setMessage("Đang thêm");
                        xuLyThem.show();

                        SanPham sanPham = dsspNguoiDung.get(position);
                        String tenSanPham = sanPham.tenSanPham;

/*cc*/

                        if (et_soluong.getText().toString().isEmpty() || Integer.parseInt(et_soluong.getText().toString())==0) {
                            Toast.makeText(context, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();
                        } else {
                            int soLuong = Integer.parseInt(et_soluong.getText().toString());
                            int giaTien = sanPham.giaTien * soLuong;
                            String hinhSanPham=sanPham.hinhSanPham;
                            String user = mAuth.getCurrentUser().getEmail();
                            final GioHang gioHang = new GioHang(tenSanPham, soLuong, giaTien, user,hinhSanPham);
                            mData.child("GioHang").push().setValue(gioHang, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    if (databaseError == null) {
                                        Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                        et_soluong.setText("");
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        xuLyThem.cancel();
                    }
                });



            }
        });
    }

    @Override
    public int getItemCount() {
        return dsspNguoiDung.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham;
        TextView tvGiaTien;
        ImageView ivHinhSanPham,imv_themVaoGioHang;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPhamNguoiDung);
            tvGiaTien = (TextView) itemView.findViewById(R.id.tvGiaTienSanPhamNguoiDung);
            ivHinhSanPham = (ImageView) itemView.findViewById(R.id.ivHinhSanPhamNguoiDung);
            imv_themVaoGioHang=(ImageView)itemView.findViewById(R.id.imv_themVaoGioHang);
        }
    }

}

