package com.example.lenovo.duan1.Adapter;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.Model.HoaDonDangGiao;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    Context context;
    ListView lv_sanPhamHoaDon;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    SanPhamAdapterHoaDon sanPhamAdapterHoaDon;
    String keyHoaDon;
    public HoaDonAdapter(ArrayList<HoaDon> dshd, Context context) {
        this.dshd = dshd;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.one_item_chua_giao, parent, false);
        return new HoaDonAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_tenNguoiNhanHoaDon.setText(dshd.get(position).tenNguoiNhan);
        holder.tv_sdtNhanHoaDon.setText(dshd.get(position).soDienThoai);
        holder.tv_diaChiNhanHangHoaDon.setText(dshd.get(position).diaChiNhanHang);
        holder.tv_chuThichNhanHangHoaDon.setText(dshd.get(position).chuThichDatHang);
        holder.tv_ngayDatHang.setText(dshd.get(position).ngayDatHang);
        holder.tv_tongThanhToanChuaGiao.setText(String.valueOf(dshd.get(position).tongThanhToan));
        holder.tv_gioDatHang.setText(dshd.get(position).gioDatHang);
        holder.tv_callXacMinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_CALL);
                String sdt=dshd.get(position).soDienThoai;
                if(sdt.trim().isEmpty() || sdt.length()<10 ||sdt.length()>10){
                    Toast.makeText(context, "Sai số ", Toast.LENGTH_SHORT).show();
                }else {
                    i.setData(Uri.parse("tel:"+sdt));
                }

                if(ActivityCompat.checkSelfPermission(context,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                 requestPermission();
                }else {
                    context.startActivity(i);
                }
            }
        });
       holder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClick(View view, int posittion) {
               final ArrayList<GioHang> dsgh=new ArrayList<GioHang>();
               final Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.dialog_san_pham_hoa_don);
               lv_sanPhamHoaDon=dialog.findViewById(R.id.lv_sanPhamHoaDon);
               Button bt_giaoHang=dialog.findViewById(R.id.bt_giaoHang);
               keyHoaDon=dshd.get(position).getKeyHoaDon();
               mData.child("HoaDon").child(keyHoaDon).child("gioHang").addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                       GioHang giohang=dataSnapshot.getValue(GioHang.class);
                       giohang.setKeyGioHang(dataSnapshot.getKey());

                       dsgh.add(giohang);

                       sanPhamAdapterHoaDon = new SanPhamAdapterHoaDon(dsgh,context);

                       lv_sanPhamHoaDon.setAdapter(sanPhamAdapterHoaDon);
                       sanPhamAdapterHoaDon.notifyDataSetChanged();
                   }

                   @Override
                   public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   }

                   @Override
                   public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                       String key = dataSnapshot.getKey();
                       for (int i = 0; i < dsgh.size(); i++) {
                           if (dsgh.get(i).getKeyGioHang().equals(key)) {
                               dsgh.remove(i);
                               break;
                           }

                       }
                       sanPhamAdapterHoaDon.notifyDataSetChanged();
                   }

                   @Override
                   public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
                bt_giaoHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenNguoiNhan=dshd.get(position).tenNguoiNhan;
                        String soDienThoai=dshd.get(position).soDienThoai;
                        String diaChiNhanHang=dshd.get(position).diaChiNhanHang;
                        String chuThich=dshd.get(position).chuThichDatHang;
                        String user=dshd.get(position).user;

                        int tongThanhToan=0;
                        for(int i=0; i<dsgh.size(); i++){
                            tongThanhToan += dsgh.get(i).giaTien;
                        }

                        Date ngay = Calendar.getInstance().getTime();
                        SimpleDateFormat ngayFM = new SimpleDateFormat("dd/MM/yyyy");
                        String ngayDangGiao = ngayFM.format(ngay);

                        Date gio=Calendar.getInstance().getTime();
                        SimpleDateFormat hf=new SimpleDateFormat("hh:mm");
                        String gioDangGiao=hf.format(gio);

                        HoaDonDangGiao hoaDonDangGiao=new HoaDonDangGiao(tenNguoiNhan,soDienThoai,diaChiNhanHang,chuThich,dsgh,user,ngayDangGiao,gioDangGiao,tongThanhToan);
                        mData.child("HoaDonDangGiao").push().setValue(hoaDonDangGiao, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    mData.child("HoaDon").child(dshd.get(position).getKeyHoaDon()).removeValue();
                                    dialog.dismiss();
                                    Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(context, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

               dialog.show();

           }
       });

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PHONE},1);
    }




    @Override
    public int getItemCount() {
        return dshd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_tenNguoiNhanHoaDon;
        TextView tv_sdtNhanHoaDon;
        TextView tv_diaChiNhanHangHoaDon;
        TextView tv_chuThichNhanHangHoaDon;
        TextView tv_callXacMinh;
        TextView tv_ngayDatHang;
        TextView tv_gioDatHang;
        TextView tv_tongThanhToanChuaGiao;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_tenNguoiNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_tenNguoiNhanHoaDon);
            tv_sdtNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_sdtNhanHoaDon);
            tv_diaChiNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_diaChiNhanHangHoaDon);
            tv_chuThichNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_chuThichNhanHangHoaDon);
            tv_ngayDatHang=(TextView)itemView.findViewById(R.id.tv_ngayDatHang);
            tv_gioDatHang=(TextView)itemView.findViewById(R.id.tv_gioDatHang);
            tv_tongThanhToanChuaGiao=(TextView)itemView.findViewById(R.id.tv_tongThanhToanChuaGiao);
            tv_callXacMinh=(TextView)itemView.findViewById(R.id.tv_callXacMinh);
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
