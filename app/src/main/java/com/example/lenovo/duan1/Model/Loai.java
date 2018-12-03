package com.example.lenovo.duan1.Model;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Loai extends ExpandableGroup<SanPham> {
    public String maLoai,tenLoai,linkHinh;


    public Loai(String title,String maLoai,String linkHinh, List<SanPham> items) {
        super(title,items);
        this.maLoai=maLoai;
        this.linkHinh=linkHinh;
    }


}
