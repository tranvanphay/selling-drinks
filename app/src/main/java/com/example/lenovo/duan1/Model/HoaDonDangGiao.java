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
    public String ngayDangGiao;
    public String gioDangGiao;
    public int tongThanhToan;

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

    public HoaDonDangGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user, String ngayDangGiao, String gioDangGiao) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDangGiao = ngayDangGiao;
        this.gioDangGiao = gioDangGiao;
    }

    public HoaDonDangGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user, String ngayDangGiao, String gioDangGiao, int tongThanhToan) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDangGiao = ngayDangGiao;
        this.gioDangGiao = gioDangGiao;
        this.tongThanhToan = tongThanhToan;
    }

    public String getKeyHoaDonDangGiao() {
        return keyHoaDonDangGiao;
    }

    public void setKeyHoaDonDangGiao(String keyHoaDonDangGiao) {
        this.keyHoaDonDangGiao = keyHoaDonDangGiao;
    }
}
