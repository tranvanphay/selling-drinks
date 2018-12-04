package com.example.lenovo.duan1.Model;

public class SanPham {
    public String maSanPham;
    public String maLoai;
    public String tenSanPham;
    public String chuThich;
    public int giaTien;
    public String hinhSanPham;

    public SanPham() {
    }

    public SanPham(String maSanPham, String maLoai, String tenSanPham, String chuThich, int giaTien, String hinhSanPham) {
        this.maSanPham = maSanPham;
        this.maLoai = maLoai;
        this.tenSanPham = tenSanPham;
        this.chuThich = chuThich;
        this.giaTien = giaTien;
        this.hinhSanPham = hinhSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getChuThich() {
        return chuThich;
    }

    public void setChuThich(String chuThich) {
        this.chuThich = chuThich;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }
}
