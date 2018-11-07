package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChuaGiaoFragment extends Fragment {
    ListView lvchuagiao;
    FloatingActionButton flb_chuagiao;
    android.support.v7.widget.SearchView search_chuagiao;
    public ChuaGiaoFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chua_giao, container, false);
        lvchuagiao=v.findViewById(R.id.listchuagiao);
        flb_chuagiao=v.findViewById(R.id.flbchuagiao);
        search_chuagiao=v.findViewById(R.id.searchchuagiao);
        return v;

    }
}
