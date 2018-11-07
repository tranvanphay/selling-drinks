package com.example.lenovo.duan1.model;

public class Loai {
    public String _idLoai;
    public String tenLoai;
    public Loai() {
    }
    public Loai(String _idLoai, String tenLoai) {
        this._idLoai = _idLoai;
        this.tenLoai = tenLoai;
    }

    public Loai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
