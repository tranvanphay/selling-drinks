package com.example.lenovo.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder>  {
    ArrayList<GioHang> dsgh;
    Context context;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuhtor=FirebaseAuth.getInstance();

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
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_sua_gio_hang);
                final EditText et_soLuongNhapLai=dialog.findViewById(R.id.edt_soLuongThayDoi);
                ImageView imv_tru=dialog.findViewById(R.id.imv_tru);
                ImageView imv_cong=dialog.findViewById(R.id.imv_cong);
                Button bt_oke=dialog.findViewById(R.id.bt_xacNhanSuaGioHang);
                et_soLuongNhapLai.setText(String.valueOf(dsgh.get(position).soLuong));
//                imv_tru.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int i=Integer.parseInt(et_soLuongNhapLai.getText());
//                        et_soLuongNhapLai.setText(String.valueOf(i--));
//                    }
//                });
//                imv_cong.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int z=dsgh.get(position).soLuong;
//                        et_soLuongNhapLai.setText(String.valueOf(z++));
//                    }
//                });
                    bt_oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int soLuongCu=dsgh.get(position).soLuong;
                            int soLuong=Integer.parseInt(et_soLuongNhapLai.getText().toString());
                            String tenSanPham=dsgh.get(position).tenSanPham;
                            int giaTien=(dsgh.get(position).giaTien/soLuongCu)*soLuong;
                            String user=mAuhtor.getCurrentUser().getEmail();
                            String key=dsgh.get(position).getKeyGioHang();
                            GioHang gioHang=new GioHang(tenSanPham,soLuong,giaTien,user);
                            mData.child("GioHang").child(key).setValue(gioHang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                dialog.show();
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

