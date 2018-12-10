package com.example.lenovo.duan1.NguoiDungFragment;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.Adapter.LoaiApdaterAdmin;
import com.example.lenovo.duan1.Adapter.LoaiSpinnerAdapter;
import com.example.lenovo.duan1.Adapter.SanPhamAdapterAdmin;
import com.example.lenovo.duan1.Adapter.SanPhamAdapterNguoiDung;
import com.example.lenovo.duan1.Model.Loai;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuNguoiDungFragment extends Fragment {
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    ArrayList<SanPham> dsspNguoiDung = new ArrayList<SanPham>();
    ArrayList<Loai> dslNguoiDung=new ArrayList<Loai>();
    ArrayList<String> keySPLoc=new ArrayList<String>();
    ArrayList<String> keyLoai=new ArrayList<String>();
    ArrayList<String> keySP=new ArrayList<String>();
    Spinner spn_loaiMenuNguoiDung;
    Button bt_locLoai;
    TextView tvIDNguoiDung;
    RecyclerView recyclerViewBangTin,recyclerViewSanPhamNguoiDung;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuthor=FirebaseAuth.getInstance();
    SanPhamAdapterNguoiDung sanPhamAdapterNguoiDung;
    LoaiSpinnerAdapter adapter;


    public TrangChuNguoiDungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String user=mAuthor.getCurrentUser().getEmail();
        View view = inflater.inflate(R.layout.fragment_trang_chu_nguoi_dung, container, false);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTinNguoiDung);
        recyclerViewSanPhamNguoiDung = view.findViewById(R.id.recyclerViewSanPhamNguoiDung);
        tvIDNguoiDung=view.findViewById(R.id.tvIDNguoiDung);
        tvIDNguoiDung.setText(user);
        spn_loaiMenuNguoiDung=view.findViewById(R.id.spn_loaiMenuNguoiDung);
        bt_locLoai=view.findViewById(R.id.btnLocLoai);
        bt_locLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadbyID();

            }
        });

        loadLoai();
        loadSanPham();
        getBangTin();
        return view;
    }
    private void loadLoai(){
        mData.child("Loai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Loai loai=dataSnapshot.getValue(Loai.class);
                dslNguoiDung.add(loai);
                loai.setKeyLoai(dataSnapshot.getKey());
                keyLoai.add(dataSnapshot.getKey());
                adapter=new LoaiSpinnerAdapter(getActivity(),dslNguoiDung);
                spn_loaiMenuNguoiDung.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

//                String key=dataSnapshot.getKey();
//                Loai loai=dataSnapshot.getValue(Loai.class);
//                int index=keyLoai.indexOf(key);
//                dslNguoiDung.set(index,loai);
//                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                for (int i = 0; i < dslNguoiDung.size(); i++) {
                    if (dslNguoiDung.get(i).getKeyLoai().equals(key)) {
                        dslNguoiDung.remove(i);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void loadSanPham(){
        mData.child("SanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                dsspNguoiDung.add(sanPham);
                sanPham.setKeySanPham(dataSnapshot.getKey());
                keySP.add(dataSnapshot.getKey());
                recyclerViewSanPhamNguoiDung.setHasFixedSize(true);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                recyclerViewSanPhamNguoiDung.setLayoutManager(layoutManager);
                sanPhamAdapterNguoiDung = new SanPhamAdapterNguoiDung(dsspNguoiDung,getContext());
                recyclerViewSanPhamNguoiDung.setAdapter(sanPhamAdapterNguoiDung);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String key=dataSnapshot.getKey();
//                SanPham sanPham=dataSnapshot.getValue(SanPham.class);
//                int index=keySP.indexOf(key);
//                dsspNguoiDung.set(index,sanPham);
//                sanPhamAdapterNguoiDung.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                for (int i = 0; i < dsspNguoiDung.size(); i++) {
                    if (dsspNguoiDung.get(i).keySanPham.equals(key)) {
                        dsspNguoiDung.remove(i);
                        break;
                    }

                }
                sanPhamAdapterNguoiDung.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void loadbyID(){
        int index=spn_loaiMenuNguoiDung.getSelectedItemPosition();
        final ArrayList<SanPham> dsspLoc=new ArrayList<>();
        String tenLoai=dslNguoiDung.get(index).tenLoai;
        Query query1=FirebaseDatabase.getInstance().getReference("SanPham").orderByChild("maLoai").equalTo(tenLoai);
        query1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                sanPham.setKeySanPham(dataSnapshot.getKey());
                keySPLoc.add(dataSnapshot.getKey());
                dsspLoc.add(new SanPham(sanPham.maSanPham,sanPham.maLoai,sanPham.tenSanPham,sanPham.chuThich,sanPham.giaTien,sanPham.hinhSanPham));
                recyclerViewSanPhamNguoiDung.setHasFixedSize(true);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                recyclerViewSanPhamNguoiDung.setLayoutManager(layoutManager);
                SanPhamAdapterNguoiDung sanPhamAdapterNguoiDung = new SanPhamAdapterNguoiDung(dsspLoc,getContext());
                recyclerViewSanPhamNguoiDung.setAdapter(sanPhamAdapterNguoiDung);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                String key=dataSnapshot.getKey();
                SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                int index=keySPLoc.indexOf(key);
                dsspLoc.set(index,sanPham);
                sanPhamAdapterNguoiDung.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (int i = 0; i < dsspLoc.size(); i++) {
                    if (dsspLoc.get(i).getKeySanPham().equals(key)) {
                        dsspLoc.remove(i);
                        break;
                    }
                }
                recyclerViewSanPhamNguoiDung.notifyAll();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getBangTin() {
        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/header-img.jpg");
        tenBangTin.add("CYBER MONDAY - Giảm giá 50% trên tổng hóa đơn");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img01.jpg");
        tenBangTin.add("Cùng trải nghiệm không gian mới của DEER TEA");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img02.jpg");
        tenBangTin.add("Cà phê DEER TEA cho ngày dài năng động!");

        hinhBangTin.add("http://www.the-alley.ca/images/main-bg-box/img04.jpg");
        tenBangTin.add("Sinh nhật DEER TEA, nhân đôi các loại thức uống");

        recyclerViewBangTin();
    }

    private void recyclerViewBangTin() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBangTin.setLayoutManager(layoutManager);
        BangTinAdapter bangTinAdapter = new BangTinAdapter(getActivity(), tenBangTin, hinhBangTin);
        recyclerViewBangTin.setAdapter(bangTinAdapter);
    }


}
