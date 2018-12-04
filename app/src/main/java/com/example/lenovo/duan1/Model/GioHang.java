package com.example.lenovo.duan1.Model;

public class GioHang {
    public String tenSanPham;
    public int soLuong;
    public int giaTien;
    public String linkHinh;
    public String user;

    public GioHang() {
    }

    public GioHang(String tenSanPham, int soLuong, int giaTien, String linkHinh, String user) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.linkHinh = linkHinh;
        this.user = user;
    }

}
