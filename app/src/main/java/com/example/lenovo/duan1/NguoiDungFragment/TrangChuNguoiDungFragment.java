package com.example.lenovo.duan1.NguoiDungFragment;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuNguoiDungFragment extends Fragment {
    ViewFlipper viewFlipper;

    public TrangChuNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_trang_chu_nguoi_dung, container, false);

        int image[] = {R.drawable.img01,R.drawable.img02,R.drawable.img03};

        viewFlipper = v.findViewById(R.id.view_flipper_nguoidung);

//        for (int i = 0; i<image.length; i++)
//        {
//            flipperImages(image[i]);
//        }

        for (int i: image)
        {
            flipperImages(i);
        }

        return v;
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }
}
