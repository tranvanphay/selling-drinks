package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class SanPhamAdapterHoaDon extends BaseAdapter {
    ArrayList<GioHang> dshd=new ArrayList<GioHang>();

    HoaDon hoaDon=new HoaDon();
    Context context;

    public SanPhamAdapterHoaDon() {
    }

    public SanPhamAdapterHoaDon(ArrayList<GioHang> dshd, Context context) {
        this.dshd = dshd;
        this.context = context;
    }

//    public SanPhamAdapterHoaDon(HoaDon hoaDon, Context context) {
//        this.hoaDon.gioHang = hoaDon.gioHang;
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        convertView=inflater.inflate(R.layout.one_item_listview_san_pham_hoa_don,null);
        TextView tv_tenSanPhamHoaDon= convertView.findViewById(R.id.tv_tenSanPhamHoaDon);
        TextView tv_soLuongSanPhamHoaDon=convertView.findViewById(R.id.tv_soLuongSanPhamHoaDon);
        TextView tv_tongTienSanPhamHoaDon=convertView.findViewById(R.id.tv_tongTienSanPhamHoaDon);
        HoaDon hoaDon=new HoaDon();
//        GioHang gioHang=hoaDon.gioHang.get(position);
        tv_tenSanPhamHoaDon.setText(hoaDon.gioHang.get(position).tenSanPham);
        tv_soLuongSanPhamHoaDon.setText(String.valueOf(hoaDon.gioHang.get(position).soLuong));
        tv_tongTienSanPhamHoaDon.setText(String.valueOf(hoaDon.gioHang.get(position).giaTien));
        return convertView;
    }
}
