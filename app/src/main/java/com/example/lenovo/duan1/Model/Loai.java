package com.example.lenovo.duan1.Model;

public class Loai {
    public String maLoai;
    public String tenLoai;
    public String hinhLoai;

    public Loai() {
    }

    public Loai(String maLoai, String tenLoai, String hinhLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.hinhLoai = hinhLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getHinhLoai() {
        return hinhLoai;
    }

    public void setHinhLoai(String hinhLoai) {
        this.hinhLoai = hinhLoai;
    }
}
