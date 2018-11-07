package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeFragment extends Fragment {


    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_thong_ke,container,false);
        // Inflate the layout for this fragment
        return view;
    }

}
