package com.example.lenovo.duan1.Model;

import java.util.ArrayList;

public class HoaDonDaGiao {
    public String tenNguoiNhan;
    public String soDienThoai;
    public String diaChiNhanHang;
    public String chuThichDatHang;
    public ArrayList<GioHangDangGiao> gioHang;
    public String user;
    public String ngayDaGiao;
    public String thangDaGiao;
    public String namDaGiao;
    public String gioDaGiao;
    public int tongThanhToan;
    public String keyHoaDonDaGiao;

    public HoaDonDaGiao() {
    }

    public HoaDonDaGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHangDangGiao> gioHang, String user) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
    }

    public HoaDonDaGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHangDangGiao> gioHang, String user, String ngayDaGiao, String gioDaGiao) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDaGiao = ngayDaGiao;
        this.gioDaGiao = gioDaGiao;
    }

    public HoaDonDaGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHangDangGiao> gioHang, String user, String ngayDaGiao, String thangDaGiao, String namDaGiao, String gioDaGiao) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDaGiao = ngayDaGiao;
        this.thangDaGiao = thangDaGiao;
        this.namDaGiao = namDaGiao;
        this.gioDaGiao = gioDaGiao;
    }

    public HoaDonDaGiao(String tenNguoiNhan, String soDienThoai, String diaChiNhanHang, String chuThichDatHang, ArrayList<GioHangDangGiao> gioHang, String user, String ngayDaGiao, String thangDaGiao, String namDaGiao, String gioDaGiao, int tongThanhToan) {
        this.tenNguoiNhan = tenNguoiNhan;
        this.soDienThoai = soDienThoai;
        this.diaChiNhanHang = diaChiNhanHang;
        this.chuThichDatHang = chuThichDatHang;
        this.gioHang = gioHang;
        this.user = user;
        this.ngayDaGiao = ngayDaGiao;
        this.thangDaGiao = thangDaGiao;
        this.namDaGiao = namDaGiao;
        this.gioDaGiao = gioDaGiao;
        this.tongThanhToan = tongThanhToan;
    }

    public String getKeyHoaDonDaGiao() {
        return keyHoaDonDaGiao;
    }

    public void setKeyHoaDonDaGiao(String keyHoaDonDaGiao) {
        this.keyHoaDonDaGiao = keyHoaDonDaGiao;
    }
}
