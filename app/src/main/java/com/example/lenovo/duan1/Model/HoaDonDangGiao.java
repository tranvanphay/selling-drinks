package com.example.lenovo.duan1.Model;

import java.util.ArrayList;

public class HoaDonDangGiao {
    public String tenNguoiNhan;
    public String soDienThoai;
    public String diaChiNhanHang;
    public String chuThichDatHang;
    public ArrayList<GioHang> gioHang;
    public String user;
    public String keyHoaDonDangGiao;

    public HoaDonDangGiao() {
    }

    public HoaDonDangGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
    }

    public String getKeyHoaDonDangGiao() {
        return keyHoaDonDangGiao;
    }

    public void setKeyHoaDonDangGiao(String keyHoaDonDangGiao) {
        this.keyHoaDonDangGiao = keyHoaDonDangGiao;
    }
}
