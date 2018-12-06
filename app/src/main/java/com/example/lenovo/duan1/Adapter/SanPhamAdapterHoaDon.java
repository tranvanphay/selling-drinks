package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class SanPhamAdapterHoaDon extends BaseAdapter {
    ArrayList<GioHang> dsSanPhamHoaDon=new ArrayList<GioHang>();
    Context context;

    public SanPhamAdapterHoaDon(ArrayList<GioHang> dsSanPhamHoaDon, Context context) {
        this.dsSanPhamHoaDon = dsSanPhamHoaDon;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsSanPhamHoaDon.size();
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
        convertView=inflater.inflate(R.layout.one_item_listview_san_pham_hoa_don,parent,false);
        TextView tv_tenSanPhamHoaDon=convertView.findViewById(R.id.tv_tenSanPhamHoaDon);
        TextView tv_soLuongSanPhamHoaDon=convertView.findViewById(R.id.tv_soLuongSanPhamHoaDon);
        TextView tv_tongTienSanPhamHoaDon=convertView.findViewById(R.id.tv_tongTienSanPhamHoaDon);
        GioHang gioHang=dsSanPhamHoaDon.get(position);
        tv_tenSanPhamHoaDon.setText(gioHang.tenSanPham);
        tv_soLuongSanPhamHoaDon.setText(String.valueOf(gioHang.soLuong));
        tv_tongTienSanPhamHoaDon.setText(String.valueOf(gioHang.giaTien));

        return convertView;
    }
}
