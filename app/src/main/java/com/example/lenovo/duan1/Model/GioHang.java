package com.example.lenovo.duan1.Model;

public class GioHang {
    public String tenSanPham;
    public int soLuong;
    public int giaTien;
    public String user;
    public String keyGioHang;

    public GioHang() {
    }

    public GioHang(String tenSanPham, int soLuong, int giaTien, String user) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.user = user;
    }




    public String getKeyGioHang() {
        return keyGioHang;
    }

    public void setKeyGioHang(String keyGioHang) {
        this.keyGioHang = keyGioHang;
    }
}
