package com.example.lenovo.duan1.NguoiDungFragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lenovo.duan1.LoginActivity;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinNguoiDungFragment extends Fragment {
    Button bt_doiMatKhau;
    FirebaseAuth mAuthor=FirebaseAuth.getInstance();



    public ThongTinNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        bt_doiMatKhau=view.findViewById(R.id.bt_doiMatKhau);
        bt_doiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {
                        String email=mAuthor.getCurrentUser().getEmail();
                        mAuthor.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(), "Vui lòng kiểm tra trong hộp thư Gmail", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setTitle("Đổi mật khẩu");
                builder.setMessage("Bạn có thực sự muốn đổi mật khẩu?");
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        return view;
    }

}
