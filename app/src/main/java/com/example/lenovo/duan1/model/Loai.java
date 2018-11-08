package com.example.lenovo.duan1.model;

public class Loai {
    public String key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
