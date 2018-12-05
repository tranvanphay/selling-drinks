package com.example.lenovo.duan1.NguoiDungFragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.GioHangAdapter;
import com.example.lenovo.duan1.Adapter.SanPhamAdapterNguoiDung;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.HoaDon;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangNguoiDungFragment extends Fragment {
    ArrayList<GioHang> dsgh = new ArrayList<>();
    SearchView searchViewgiohang;
    RecyclerView recyclerViewSanPhamGioHang;
    Button bt_thanhToan;
    GioHangAdapter gioHangAdapter;
    FirebaseAuth mAuthor=FirebaseAuth.getInstance();
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    String user=mAuthor.getCurrentUser().getEmail();
    public GioHangNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gio_hang_nguoi_dung, container, false);
        searchViewgiohang=v.findViewById(R.id.svgiohang);
        recyclerViewSanPhamGioHang=v.findViewById(R.id.recyclerViewSanPhamGioHang);
        bt_thanhToan=v.findViewById(R.id.bt_thanhToan);
        bt_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               thanhToan();
            }
        });
        loadGioHang();
        viewGioHang();
        return v;
    }
    private void loadGioHang(){

        Query query=FirebaseDatabase.getInstance().getReference("GioHang").orderByChild("user").equalTo(user);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                GioHang gioHang=dataSnapshot.getValue(GioHang.class);
                gioHang.setKeyGioHang(dataSnapshot.getKey());
                dsgh.add(gioHang);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewSanPhamGioHang.setLayoutManager(layoutManager);
                recyclerViewSanPhamGioHang.setHasFixedSize(true);
                 gioHangAdapter = new GioHangAdapter(dsgh,getContext());
                recyclerViewSanPhamGioHang.setAdapter(gioHangAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < dsgh.size(); i++) {
                    if (dsgh.get(i).keyGioHang.equals(key)) {
                        dsgh.remove(i);
                        break;
                    }

                }
                gioHangAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void thanhToan(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_thanh_toan);
        final EditText et_Ten=dialog.findViewById(R.id.et_tenDatHang);
        final EditText et_sdt=dialog.findViewById(R.id.et_sdtDatHang);
        final EditText et_diachi=dialog.findViewById(R.id.et_diaChiNhanHang);
        final EditText et_chuThich=dialog.findViewById(R.id.et_chuThichDatHang);
        Button bt_xacNhanDatHang=dialog.findViewById(R.id.bt_xacNhanThanhToan);
        bt_xacNhanDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDatHang=et_Ten.getText().toString();
                String sdt=et_sdt.getText().toString();
                String diaChi=et_diachi.getText().toString();
                String chuThich=et_chuThich.getText().toString();
                HoaDon hoaDon=new HoaDon(tenDatHang,sdt,diaChi,chuThich,dsgh,user);
                mData.child("HoaDon").push().setValue(hoaDon, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@android.support.annotation.Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError == null){
                            Toast.makeText(getActivity(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            et_Ten.setText("");
                            et_sdt.setText("");
                            et_diachi.setText("");
                            et_chuThich.setText("");
                            dialog.dismiss();
                            xoaGioHang();
                        }else {
                            Toast.makeText(getActivity(), "Lỗi dặt hàng!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        dialog.show();
    }

    public void viewGioHang(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewSanPhamGioHang.setLayoutManager(layoutManager);
        recyclerViewSanPhamGioHang.setHasFixedSize(true);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(dsgh,getContext());
        recyclerViewSanPhamGioHang.setAdapter(gioHangAdapter);

    }
    private void xoaGioHang(){
        Query query1=FirebaseDatabase.getInstance().getReference("GioHang").orderByChild("user").equalTo(user);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot giohang: dataSnapshot.getChildren()){
                    giohang.getRef().removeValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
