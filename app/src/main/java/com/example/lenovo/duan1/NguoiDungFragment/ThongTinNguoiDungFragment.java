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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.duan1.LoginActivity;
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
    FirebaseAuth auth=FirebaseAuth.getInstance();

    public ThongTinNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        bt_doiMatKhau = view.findViewById(R.id.bt_doiMatKhau);
        bt_dangXuat = view.findViewById(R.id.bt_dangXuat);
        doipw = view.findViewById(R.id.ed_doipw);


        btn_doipw = view.findViewById(R.id.btn_doipw);
        btn_doipw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// đổi pass. chỗ này là chỗ đổi pass. vứt vào đâu cũng dc
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    user.updatePassword(doipw.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity(), "thanh cong", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getActivity(), ""+doipw.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getActivity(), "that bai", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                ///
            }
        });



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


        return view;
    }


}
