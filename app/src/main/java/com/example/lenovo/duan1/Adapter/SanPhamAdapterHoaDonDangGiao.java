package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.GioHangDangGiao;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class SanPhamAdapterHoaDonDangGiao extends BaseAdapter {
    ArrayList<GioHangDangGiao> dsghDangGiao=new ArrayList<GioHangDangGiao>();
    Context context;

    public SanPhamAdapterHoaDonDangGiao(ArrayList<GioHangDangGiao> dsghDangGiao, Context context) {
        this.dsghDangGiao = dsghDangGiao;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsghDangGiao.size();
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
        convertView=inflater.inflate(R.layout.one_item_list_san_pham_dang_giao,parent,false);
        TextView tv_tenSanPhamDangGiao=convertView.findViewById(R.id.tv_tenSanPhamDangGiao);
        TextView tv_soLuongSanPhamDangGiao=convertView.findViewById(R.id.tv_soLuongSanPhamDangGiao);
        TextView tv_tongTienSanPhamDangGiao=convertView.findViewById(R.id.tv_tongTienSanPhamDangGiao);
        GioHangDangGiao gioHangDangGiao=dsghDangGiao.get(position);
        tv_tenSanPhamDangGiao.setText(gioHangDangGiao.tenSanPham);
        tv_soLuongSanPhamDangGiao.setText(String.valueOf(gioHangDangGiao.soLuong));
        tv_tongTienSanPhamDangGiao.setText(String.valueOf(gioHangDangGiao.giaTien));

        return convertView;
    }
}
