package com.example.lenovo.duan1.Model;

import java.util.ArrayList;

public class HoaDon {
    public String tenNguoiNhan;
    public String soDienThoai;
    public String diaChiNhanHang;
    public String chuThichDatHang;
    public ArrayList<GioHang> gioHang;
    public String user;

    public HoaDon() {
    }

    public HoaDon(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
    }
}
