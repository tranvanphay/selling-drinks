package com.example.lenovo.duan1.Model;

import java.util.ArrayList;

public class HoaDon {
    public String tenNguoiNhan;
    public String soDienThoai;
    public String diaChiNhanHang;
    public String chuThichDatHang;
    public ArrayList<GioHang> gioHang;
    public String user;
    public String keyHoaDon;
    public String ngayDatHang;
    public String gioDatHang;
    public int tongThanhToan;
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

    public HoaDon(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user, String ngayDatHang, String gioDatHang) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDatHang = ngayDatHang;
        this.gioDatHang = gioDatHang;
    }

    public HoaDon(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHang> gioHang, String user, String ngayDatHang, String gioDatHang, int tongThanhToan) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDatHang = ngayDatHang;
        this.gioDatHang = gioDatHang;
        this.tongThanhToan = tongThanhToan;
    }

    public String getKeyHoaDon() {
        return keyHoaDon;
    }

    public void setKeyHoaDon(String keyHoaDon) {
        this.keyHoaDon = keyHoaDon;
    }
}
