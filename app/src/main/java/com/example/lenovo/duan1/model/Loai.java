package com.example.lenovo.duan1.Model;

public class Loai {
    private String maLoai;
    private String tenLoai;
    private int hinhLoai;

    public Loai(String maLoai, String tenLoai, int hinhLoai) {
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

    public int getHinhLoai() {
        return hinhLoai;
    }

    public void setHinhLoai(int hinhLoai) {
        this.hinhLoai = hinhLoai;
    }
}
