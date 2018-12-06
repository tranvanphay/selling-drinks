package com.example.lenovo.duan1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    ArrayList<GioHang> dsgh=new ArrayList<GioHang>();
    Context context;
    ListView lv_sanPhamHoaDon;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    SanPhamAdapterHoaDon sanPhamAdapterHoaDon;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_tenNguoiNhanHoaDon.setText(dshd.get(position).tenNguoiNhan);
        holder.tv_sdtNhanHoaDon.setText(dshd.get(position).soDienThoai);
        holder.tv_diaChiNhanHangHoaDon.setText(dshd.get(position).diaChiNhanHang);
        holder.tv_chuThichNhanHangHoaDon.setText(dshd.get(position).chuThichDatHang);
       holder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClick(View view, int posittion) {
               String user= dshd.get(position).user;
               Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.dialog_san_pham_hoa_don);
               lv_sanPhamHoaDon=dialog.findViewById(R.id.lv_sanPhamHoaDon);
              String keyHoaDon=dshd.get(position).getKeyHoaDon();
               Query query=FirebaseDatabase.getInstance().getReference("HoaDon").child(keyHoaDon).child("gioHang").orderByChild("user").equalTo(user);
               query.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                       GioHang giohang=dataSnapshot.getValue(GioHang.class);
                       giohang.setKeyGioHang(dataSnapshot.getKey());
                       dsgh.add(giohang);

                   }

                   @Override
                   public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

                       sanPhamAdapterHoaDon.notifyDataSetChanged();
                   }

                   @Override
                   public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                       String key = dataSnapshot.getKey();
                       for (int i = 0; i < dsgh.size(); i++) {
                           if (dsgh.get(i).keyGioHang.equals(key)) {
                               dsgh.remove(i);
                               break;
                           }

                       }
                       sanPhamAdapterHoaDon.notifyDataSetChanged();


                   }

                   @Override
                   public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });


//               mData.child("HoaDon").child(keyHoaDon).child("gioHang").addChildEventListener(new ChildEventListener() {
//                   @Override
//                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                       GioHang giohang=dataSnapshot.getValue(GioHang.class);
//                       giohang.setKeyGioHang(dataSnapshot.getKey());
//                       dsgh.add(giohang);
//
//                   }
//
//                   @Override
//                   public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                   }
//
//                   @Override
//                   public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                       String key = dataSnapshot.getKey();
//                       for (int i = 0; i < dsgh.size(); i++) {
//                           if (dsgh.get(i).getKeyGioHang().equals(key)) {
//                               dsgh.remove(i);
//                               break;
//                           }
//
//                       }
//                       sanPhamAdapterHoaDon.notifyDataSetChanged();
//                   }
//
//                   @Override
//                   public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                   }
//
//                   @Override
//                   public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                   }
//               });
               dialog.show();
               sanPhamAdapterHoaDon = new SanPhamAdapterHoaDon(dsgh,context);
               lv_sanPhamHoaDon.setAdapter(sanPhamAdapterHoaDon);
           }
       });







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
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_tenNguoiNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_tenNguoiNhanHoaDon);
            tv_sdtNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_sdtNhanHoaDon);
            tv_diaChiNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_diaChiNhanHangHoaDon);
            tv_chuThichNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_chuThichNhanHangHoaDon);


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
