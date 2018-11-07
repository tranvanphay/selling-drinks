package com.example.lenovo.duan1.model;

public class AccountNguoiDung {
    public String tenDangNhap;
    public String matKhau;
    public String soDienThoai;
    public String tenNguoiDung;

    public AccountNguoiDung() {

    }

    public AccountNguoiDung(String tenDangNhap, String matKhau, String soDienThoai, String tenNguoiDung) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.tenNguoiDung = tenNguoiDung;
    }

    public AccountNguoiDung(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }
}
