package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.duan1.Model.GioHangDaGiao;
import com.example.lenovo.duan1.Model.GioHangDangGiao;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class SanPhamAdapterDaGiao extends BaseAdapter {

    ArrayList<GioHangDaGiao> dsghDaGiao=new ArrayList<GioHangDaGiao>();
    Context context;

    public SanPhamAdapterDaGiao(ArrayList<GioHangDaGiao> dsghDaGiao, Context context) {
        this.dsghDaGiao = dsghDaGiao;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsghDaGiao.size();
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
        convertView=inflater.inflate(R.layout.one_item_list_san_pham_da_giao,parent,false);
        TextView tv_tenSanPhamDaGiao=convertView.findViewById(R.id.tv_tenSanPhamDaGiao);
        TextView tv_soLuongSanPhamDaGiao=convertView.findViewById(R.id.tv_soLuongSanPhamDaGiao);
        TextView tv_tongTienSanPhamDaGiao=convertView.findViewById(R.id.tv_tongTienSanPhamDaGiao);
        GioHangDaGiao gioHangDaGiao=dsghDaGiao.get(position);
        tv_tenSanPhamDaGiao.setText(gioHangDaGiao.tenSanPham);
        tv_soLuongSanPhamDaGiao.setText(String.valueOf(gioHangDaGiao.soLuong));
        tv_tongTienSanPhamDaGiao.setText(String.valueOf(gioHangDaGiao.giaTien));

        return convertView;
    }
}
