package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiFragment extends Fragment {
    ListView lv_loai;
    FloatingActionButton flb_loai;


    public LoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_loai,container,false);
        lv_loai=v.findViewById(R.id.listloai);
        flb_loai=v.findViewById(R.id.flbloai);
        return v;
    }

}
