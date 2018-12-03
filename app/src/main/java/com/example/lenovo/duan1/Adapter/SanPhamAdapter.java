package com.example.lenovo.duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;

public class SanPhamAdapter extends ExpandableRecyclerViewAdapter<LoaiViewHolder,SanPhamViewHolder> {
    public SanPhamAdapter(ArrayList<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public LoaiViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_loai,parent,false);

        return new LoaiViewHolder(v);
    }

    @Override
    public SanPhamViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recycler_view_sanpham,parent,false);


        return new SanPhamViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(SanPhamViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final SanPham sanPham = (SanPham) group.getItems().get(childIndex);
        holder.bind(sanPham);
    }

    @Override
    public void onBindGroupViewHolder(LoaiViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Loai loai = (Loai) group;
        holder.bind(loai);
    }
}
