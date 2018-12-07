package com.example.lenovo.duan1.Model;

import java.util.ArrayList;

public class GioHangDangGiao {
    public String tenSanPham;
    public int soLuong;
    public int giaTien;
    public String user;
    public String keyGioHang;
    ArrayList<SanPham> sanPhams;

    public GioHangDangGiao() {
    }

    public GioHangDangGiao(String tenSanPham, int soLuong, int giaTien, String user, ArrayList<SanPham> sanPhams) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.user = user;
        this.sanPhams = sanPhams;
    }

    public String getKeyGioHang() {
        return keyGioHang;
    }

    public void setKeyGioHang(String keyGioHang) {
        this.keyGioHang = keyGioHang;
    }
}
