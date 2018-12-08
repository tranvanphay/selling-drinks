package com.example.lenovo.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.GioHangDaGiao;
import com.example.lenovo.duan1.Model.GioHangDangGiao;
import com.example.lenovo.duan1.Model.HoaDonDaGiao;
import com.example.lenovo.duan1.Model.HoaDonDangGiao;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HoaDonDaGiaoAdapter extends RecyclerView.Adapter<HoaDonDaGiaoAdapter.ViewHolder> {
    ArrayList<HoaDonDaGiao> dshdDaGiao=new ArrayList<HoaDonDaGiao>();
    Context context;
    ListView lv_sanPhamDaGiao;
    String keyHoaDonDaGiao;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    SanPhamAdapterDaGiao sanPhamAdapterDaGiao;

    public HoaDonDaGiaoAdapter(ArrayList<HoaDonDaGiao> dshdDaGiao, Context context) {
        this.dshdDaGiao = dshdDaGiao;
        this.context = context;
    }

    @Override
    public HoaDonDaGiaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.one_item_recycleview_da_giao, parent, false);
        return new HoaDonDaGiaoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_tenNguoiNhanDaGiao.setText(dshdDaGiao.get(position).tenNguoiNhan);
        holder.tv_sdtNhanDaGiao.setText(dshdDaGiao.get(position).soDienThoai);
        holder.tv_diaChiNhanHangDaGiao.setText(dshdDaGiao.get(position).diaChiNhanHang);
        holder.tv_chuThichNhanHangDaGiao.setText(dshdDaGiao.get(position).chuThichDatHang);
        holder.tv_ngayDaGiao.setText(dshdDaGiao.get(position).ngayDaGiao);
        holder.tv_thangDaGiao.setText(dshdDaGiao.get(position).thangDaGiao);
        holder.tv_namDaGiao.setText(dshdDaGiao.get(position).namDaGiao);
        holder.tv_gioDaGiao.setText(dshdDaGiao.get(position).gioDaGiao);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posittion) {
                final ArrayList<GioHangDaGiao> dsghDaGiao=new ArrayList<GioHangDaGiao>();
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_san_pham_da_giao);
                lv_sanPhamDaGiao=dialog.findViewById(R.id.lv_sanPhamDaGiao);
                keyHoaDonDaGiao=dshdDaGiao.get(position).getKeyHoaDonDaGiao();
                mData.child("HoaDonDaGiao").child(keyHoaDonDaGiao).child("gioHang").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        GioHangDaGiao gioHangDaGiao=dataSnapshot.getValue(GioHangDaGiao.class);
                        gioHangDaGiao.setKeyGioHang(dataSnapshot.getKey());

                        dsghDaGiao.add(gioHangDaGiao);

                        sanPhamAdapterDaGiao = new SanPhamAdapterDaGiao(dsghDaGiao,context);

                        lv_sanPhamDaGiao.setAdapter(sanPhamAdapterDaGiao);
                        sanPhamAdapterDaGiao.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        String key = dataSnapshot.getKey();
                        for (int i = 0; i < dsghDaGiao.size(); i++) {
                            if (dsghDaGiao.get(i).getKeyGioHang().equals(key)) {
                                dsghDaGiao.remove(i);
                                break;
                            }

                        }
                        sanPhamAdapterDaGiao.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialog.show();
            }
        });
        holder.imv_xoaHoaDonDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=dshdDaGiao.get(position).keyHoaDonDaGiao;
                mData.child("HoaDonDaGiao").child(key).removeValue();
            }
        });




    }



    @Override
    public int getItemCount() {
        return dshdDaGiao.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_tenNguoiNhanDaGiao;
        TextView tv_sdtNhanDaGiao;
        TextView tv_diaChiNhanHangDaGiao;
        TextView tv_chuThichNhanHangDaGiao;
        ImageView imv_xoaHoaDonDaGiao;
        TextView tv_ngayDaGiao;
        TextView tv_thangDaGiao;
        TextView tv_namDaGiao;
        TextView tv_gioDaGiao;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_tenNguoiNhanDaGiao=(TextView)itemView.findViewById(R.id.tv_tenNguoiNhanDaGiao);
            tv_sdtNhanDaGiao=(TextView)itemView.findViewById(R.id.tv_sdtNhanDaGiao);
            tv_diaChiNhanHangDaGiao=(TextView)itemView.findViewById(R.id.tv_diaChiNhanHangDaGiao);
            tv_chuThichNhanHangDaGiao=(TextView)itemView.findViewById(R.id.tv_chuThichNhanHangDaGiao);
            tv_ngayDaGiao=(TextView)itemView.findViewById(R.id.tv_ngayDaGiao);
            tv_thangDaGiao=(TextView)itemView.findViewById(R.id.tv_thangDaGiao);
            tv_namDaGiao=(TextView)itemView.findViewById(R.id.tv_namDaGiao);
            tv_gioDaGiao=(TextView)itemView.findViewById(R.id.tv_gioDaGiao);
            imv_xoaHoaDonDaGiao=(ImageView) itemView.findViewById(R.id.imv_xoaHoaDonDaGiao);
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
