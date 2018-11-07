package com.example.lenovo.duan1.AdminFragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lenovo.duan1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiFragment extends Fragment {
    ListView lv_loai;
    FloatingActionButton flb_loai;
    Context c;

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
        flb_loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button bt_them;
                ImageView img_close;
                final EditText et_ma_loai,et_ten_Loai;
                final Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_them_loai);
                bt_them=dialog.findViewById(R.id.bt_them_loai);
                img_close=dialog.findViewById(R.id.img_close_them_loai);
                et_ma_loai=dialog.findViewById(R.id.etMaLoai);
                et_ten_Loai=dialog.findViewById(R.id.etTenLoai);

                bt_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String maloai=et_ma_loai.getText().toString();
                        String tenloai=et_ten_Loai.getText().toString();
                    }
                });

                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });
        return v;
    }

}
