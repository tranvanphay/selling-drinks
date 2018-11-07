package com.example.lenovo.duan1.NguoiDungFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichSuGiaoDichFragment extends Fragment {
    SearchView sv_lichsugiaohang;
    ListView lv_lichsugiaohang;

    public LichSuGiaoDichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lich_su_giao_dich, container, false);
       sv_lichsugiaohang=v.findViewById(R.id.sv_lichsugiaodich);
        lv_lichsugiaohang=v.findViewById(R.id.lv_lichsugiaodich);
        return v;
    }

}
