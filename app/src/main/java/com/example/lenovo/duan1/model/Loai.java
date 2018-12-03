package com.example.lenovo.duan1.Model;


import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Loai extends ExpandableGroup<SanPham> {
    public Loai(String title, List<SanPham> items) {
        super(title, items);
    }
}
