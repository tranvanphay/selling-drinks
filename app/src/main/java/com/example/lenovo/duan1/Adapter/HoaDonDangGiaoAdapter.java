package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.GioHangDangGiao;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.Model.HoaDonDaGiao;
import com.example.lenovo.duan1.Model.HoaDonDangGiao;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HoaDonDangGiaoAdapter extends RecyclerView.Adapter<HoaDonDangGiaoAdapter.ViewHolder> {
    ArrayList<HoaDonDangGiao> dshdDangGiao=new ArrayList<HoaDonDangGiao>();
    Context context;
    ListView lv_sanPhamDangGiao;
    String keyHoaDonDangGiao;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    SanPhamAdapterHoaDonDangGiao sanPhamAdapterHoaDonDangGiao;
    public HoaDonDangGiaoAdapter(ArrayList<HoaDonDangGiao> dshdDangGiao, Context context) {
        this.dshdDangGiao = dshdDangGiao;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.one_item_recycleview_dang_giao, parent, false);
        return new HoaDonDangGiaoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_tenNguoiNhanDangGiao.setText(dshdDangGiao.get(position).tenNguoiNhan);
        holder.tv_sdtNhanDangGiao.setText(dshdDangGiao.get(position).soDienThoai);
        holder.tv_diaChiNhanHangDangGiao.setText(dshdDangGiao.get(position).diaChiNhanHang);
        holder.tv_chuThichNhanHangDangGiao.setText(dshdDangGiao.get(position).chuThichDatHang);
        holder.tv_ngayDangGiao.setText(dshdDangGiao.get(position).ngayDangGiao);
        holder.tv_gioDangGiao.setText(dshdDangGiao.get(position).gioDangGiao);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int posittion) {
                final ArrayList<GioHangDangGiao> dsghDangGiao=new ArrayList<GioHangDangGiao>();
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_san_pham_dang_giao);
                lv_sanPhamDangGiao=dialog.findViewById(R.id.lv_sanPhamDangGiao);
                ImageView imv_xacNhanGiaoHang=dialog.findViewById(R.id.imv_xacNhanDaGiao);
                ImageView imv_huyDonHang=dialog.findViewById(R.id.imv_huyDonHang);
                keyHoaDonDangGiao=dshdDangGiao.get(position).getKeyHoaDonDangGiao();
                mData.child("HoaDonDangGiao").child(keyHoaDonDangGiao).child("gioHang").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        GioHangDangGiao gioHangDangGiao=dataSnapshot.getValue(GioHangDangGiao.class);
                        gioHangDangGiao.setKeyGioHang(dataSnapshot.getKey());

                        dsghDangGiao.add(gioHangDangGiao);

                        sanPhamAdapterHoaDonDangGiao = new SanPhamAdapterHoaDonDangGiao(dsghDangGiao,context);

                        lv_sanPhamDangGiao.setAdapter(sanPhamAdapterHoaDonDangGiao);
                        sanPhamAdapterHoaDonDangGiao.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        String key = dataSnapshot.getKey();
                        for (int i = 0; i < dsghDangGiao.size(); i++) {
                            if (dsghDangGiao.get(i).getKeyGioHang().equals(key)) {
                                dsghDangGiao.remove(i);
                                break;
                            }

                        }
                        sanPhamAdapterHoaDonDangGiao.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                imv_xacNhanGiaoHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            String tenNguoiNhan=dshdDangGiao.get(position).tenNguoiNhan;
                            String soDienThoai=dshdDangGiao.get(position).soDienThoai;
                            String diaChiNhanHang=dshdDangGiao.get(position).diaChiNhanHang;
                            String chuThich=dshdDangGiao.get(position).chuThichDatHang;
                            String user=dshdDangGiao.get(position).user;

                            Date ngay = Calendar.getInstance().getTime();
                            SimpleDateFormat ngayFM = new SimpleDateFormat("dd");
                            String ngayDaGiao = ngayFM.format(ngay);

                            Date thang = Calendar.getInstance().getTime();
                            SimpleDateFormat thangFM = new SimpleDateFormat("MM");
                            String thangDaGiao = thangFM.format(thang);

                            Date nam = Calendar.getInstance().getTime();
                            SimpleDateFormat namFM = new SimpleDateFormat("yyyy");
                            String namDaGiao = namFM.format(nam);

                            Date gio=Calendar.getInstance().getTime();
                            SimpleDateFormat hf=new SimpleDateFormat("hh:mm");
                            String gioDaGiao=hf.format(gio);
                            HoaDonDaGiao hoaDonDaGiao=new HoaDonDaGiao(tenNguoiNhan,soDienThoai,diaChiNhanHang,chuThich,dsghDangGiao,user,ngayDaGiao,thangDaGiao,namDaGiao,gioDaGiao);
                            mData.child("HoaDonDaGiao").push().setValue(hoaDonDaGiao, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    if(databaseError == null){
                                        mData.child("HoaDonDangGiao").child(dshdDangGiao.get(position).getKeyHoaDonDangGiao()).removeValue();
                                        dialog.dismiss();
                                        Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    }
                });
                imv_huyDonHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String key=dshdDangGiao.get(position).getKeyHoaDonDangGiao();
                        mData.child("HoaDonDangGiao").child(key).removeValue();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dshdDangGiao.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_tenNguoiNhanDangGiao;
        TextView tv_sdtNhanDangGiao;
        TextView tv_diaChiNhanHangDangGiao;
        TextView tv_chuThichNhanHangDangGiao;
        TextView tv_ngayDangGiao;
        TextView tv_gioDangGiao;
        private ItemClickListener itemClickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_tenNguoiNhanDangGiao=(TextView)itemView.findViewById(R.id.tv_tenNguoiNhanDangGiao);
            tv_sdtNhanDangGiao=(TextView)itemView.findViewById(R.id.tv_sdtNhanDangGiao);
            tv_diaChiNhanHangDangGiao=(TextView)itemView.findViewById(R.id.tv_diaChiNhanHangDangGiao);
            tv_chuThichNhanHangDangGiao=(TextView)itemView.findViewById(R.id.tv_chuThichNhanHangDangGiao);
            tv_ngayDangGiao=(TextView)itemView.findViewById(R.id.tv_ngayDangGiao);
            tv_gioDangGiao=(TextView)itemView.findViewById(R.id.tv_gioDangGiao);
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
