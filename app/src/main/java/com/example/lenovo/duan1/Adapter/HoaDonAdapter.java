package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    ArrayList<HoaDon> dshd=new ArrayList<HoaDon>();
    ArrayList<GioHang> dsgh=new ArrayList<GioHang>();
    Context context;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_tenNguoiNhanHoaDon.setText(dshd.get(position).tenNguoiNhan);
        holder.tv_sdtNhanHoaDon.setText(dshd.get(position).soDienThoai);
        holder.tv_diaChiNhanHangHoaDon.setText(dshd.get(position).diaChiNhanHang);
        holder.tv_chuThichNhanHangHoaDon.setText(dshd.get(position).chuThichDatHang);
//        HoaDon hoaDon=new HoaDon();
//        SanPhamAdapterHoaDon adapter=new SanPhamAdapterHoaDon(hoaDon.gioHang,context);
//        holder.lv_sanPhamHoaDon.setAdapter(adapter);






    }

    @Override
    public int getItemCount() {
        return dshd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tenNguoiNhanHoaDon;
        TextView tv_sdtNhanHoaDon;
        ListView lv_sanPhamHoaDon;
        TextView tv_diaChiNhanHangHoaDon;
        TextView tv_chuThichNhanHangHoaDon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_tenNguoiNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_tenNguoiNhanHoaDon);
            tv_sdtNhanHoaDon=(TextView)itemView.findViewById(R.id.tv_sdtNhanHoaDon);
            lv_sanPhamHoaDon=(ListView) itemView.findViewById(R.id.lv_sanPhamHoaDon);
            tv_diaChiNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_diaChiNhanHangHoaDon);
            tv_chuThichNhanHangHoaDon=(TextView)itemView.findViewById(R.id.tv_chuThichNhanHangHoaDon);


        }
    }
}
