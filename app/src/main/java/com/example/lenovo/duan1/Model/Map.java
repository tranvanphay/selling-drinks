package com.example.lenovo.duan1.Model;

public class Map {
    public String diaChi;
    public double viDo;
    public double kinhDo;
    public String keyMap;
    public String linkHinh;

    public Map() {
    }

    public Map(String diaChi, double viDo, double kinhDo, String linkHinh) {
        this.diaChi = diaChi;
        this.viDo = viDo;
        this.kinhDo = kinhDo;
        this.linkHinh = linkHinh;
    }

    public String getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(String keyMap) {
        this.keyMap = keyMap;
    }


}
