package com.example.lenovo.duan1.NguoiDungFragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.LoginActivity;
import com.example.lenovo.duan1.NguoiDungActivity;
import com.example.lenovo.duan1.R;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinNguoiDungFragment extends Fragment {
    Button bt_doiMatKhau, bt_dangXuat, btn_doipw;
    EditText doipw;


    FirebaseAuth mAuthor = FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    public ThongTinNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        Button bt_dangXuat = view.findViewById(R.id.bt_dangXuatNguoiDung);

        bt_dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mAuthor.removeAuthStateListener(mAuthListener);
                mAuthor.signOut();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    String name = firebaseUser.getDisplayName();
                    String email = firebaseUser.getEmail();
                    Uri uri = firebaseUser.getPhotoUrl();
                } else {
                    Log.d("state", "onStateChange");
                }
            }
        };

        CardView cardViewDoiPW = view.findViewById(R.id.cardViewDoiPW);
        cardViewDoiPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogDoiMK = new Dialog(getActivity());
                dialogDoiMK.setContentView(R.layout.dialog_doimatkhau);
                dialogDoiMK.show();

                ImageView ivCloseDialogDoiPW = dialogDoiMK.findViewById(R.id.ivCloseDialogDoiMatKhau);
                Button btn_xacNhan = dialogDoiMK.findViewById(R.id.bt_doiMatKhau);
                final EditText et_updatePassword = dialogDoiMK.findViewById(R.id.edt_MatKhauMoi);
                final EditText et_nhaplaipw = dialogDoiMK.findViewById(R.id.edt_nhapLaiMatKhauMoi);

                btn_xacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null && et_nhaplaipw.getText().toString() != et_updatePassword.getText().toString()) {
                            user.updatePassword(et_updatePassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                        dialogDoiMK.dismiss();
                                    } else
                                        Toast.makeText(getActivity(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });

                ivCloseDialogDoiPW.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDoiMK.cancel();
                    }
                });
            }
        });

        return view;
    }


}
