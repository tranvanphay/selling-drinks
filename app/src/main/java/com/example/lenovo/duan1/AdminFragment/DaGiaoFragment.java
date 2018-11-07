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
public class DaGiaoFragment extends Fragment {
    ListView lvdagiao;
    android.support.v7.widget.SearchView search_dagiao;
    public DaGiaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_da_giao,container,false);
        lvdagiao=v.findViewById(R.id.listDagiao);
        search_dagiao=v.findViewById(R.id.searchDagiao);
        return  v;
    }

}
