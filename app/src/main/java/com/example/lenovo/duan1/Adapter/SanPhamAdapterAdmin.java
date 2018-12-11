package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.AdminActivity;
import com.example.lenovo.duan1.Model.GioHang;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class SanPhamAdapterAdmin extends RecyclerView.Adapter<SanPhamAdapterAdmin.ViewHolder> {
    ArrayList<SanPham> dssp;
    Context context;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();

    Spinner spnMaLoai;

    public SanPhamAdapterAdmin(ArrayList<SanPham> dssp, Context context) {
        this.dssp = dssp;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_sanpham_admin, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvMaSanPham.setText(dssp.get(position).maSanPham);
        holder.tvTenSanPham.setText(dssp.get(position).tenSanPham);
        Picasso.get().load(dssp.get(position).hinhSanPham).into(holder.ivHinhSanPham);
        holder.imv_menuSanPhamAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_xssanpham, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.xoaSanPham:
                                        AlertDialog xuLyXoa = new SpotsDialog.Builder().setContext(context).build();
                                        xuLyXoa.setMessage("Đang sửa");
                                        xuLyXoa.show();
                                        final SanPham sanPham=dssp.get(position);
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        StorageReference storageReference=FirebaseStorage.getInstance().getReferenceFromUrl(sanPham.hinhSanPham);
                                        final DatabaseReference myRef = database.getReference("SanPham");
                                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                myRef.child(sanPham.getKeySanPham()).removeValue(new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(@android.support.annotation.Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        });
                                        xuLyXoa.cancel();
                                        break;
                                    case R.id.suaSanPham:
                                        final Dialog dialogSuaSanPham = new Dialog(context);
                                        dialogSuaSanPham.setContentView(R.layout.dialog_suasanpham);
                                        ImageView imv_suaAnhSanPham=dialogSuaSanPham.findViewById(R.id.imv_suaAnhSanPham);
                                        ImageView ivCloseDialogThemSanPham=dialogSuaSanPham.findViewById(R.id.ivCloseDialogThemSanPham);
                                        final EditText edt_suaMaSanPham=dialogSuaSanPham.findViewById(R.id.edt_suaMaSanPham);
                                        final EditText edt_suaTenSanPham=dialogSuaSanPham.findViewById(R.id.edt_suaTenSanPham);
                                        spnMaLoai=dialogSuaSanPham.findViewById(R.id.spnMaLoai);
                                        final EditText edt_suaChuThich=dialogSuaSanPham.findViewById(R.id.edt_suaChuThich);
                                        final EditText edt_suaGiaSanPham=dialogSuaSanPham.findViewById(R.id.edt_suaGiaSanPham);
                                        Button bt_suaSanPham=dialogSuaSanPham.findViewById(R.id.bt_suaSanPham);
                                        Picasso.get().load(dssp.get(position).hinhSanPham).into(imv_suaAnhSanPham);
                                        edt_suaMaSanPham.setText(dssp.get(position).maSanPham);
                                        edt_suaTenSanPham.setText(dssp.get(position).tenSanPham);
                                        edt_suaGiaSanPham.setText(String.valueOf(dssp.get(position).giaTien));
                                        edt_suaChuThich.setText(dssp.get(position).chuThich);
                                        final ArrayList<Loai> dsl=new ArrayList<Loai>();
                                        mData.child("Loai").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                Loai loai = dataSnapshot.getValue(Loai.class);
                                                dsl.add(loai);
                                                LoaiSpinnerAdapter spinnerAdapter=new LoaiSpinnerAdapter(context,dsl);
                                                spnMaLoai.setAdapter(spinnerAdapter);
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                        dialogSuaSanPham.show();
                                        bt_suaSanPham.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                AlertDialog xuLySua = new SpotsDialog.Builder().setContext(context).build();
                                                xuLySua.setMessage("Đang sửa");
                                                xuLySua.show();
                                                String maSanPham=edt_suaMaSanPham.getText().toString();
                                                String tenSanPham=edt_suaTenSanPham.getText().toString();
                                                int giaSanPham= Integer.parseInt(edt_suaGiaSanPham.getText().toString());
                                                String chuThich=edt_suaChuThich.getText().toString();
                                                int index=spnMaLoai.getSelectedItemPosition();
                                                String maLoai= dsl.get(index).tenLoai;
                                                String linkHinh=dssp.get(position).hinhSanPham;
                                                String key=dssp.get(position).getKeySanPham();
                                                SanPham sanPham=new SanPham(maSanPham,maLoai,tenSanPham,chuThich,giaSanPham,linkHinh);
                                                mData.child("SanPham").child(key).setValue(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        dialogSuaSanPham.dismiss();
                                                        Intent i=new Intent(context,AdminActivity.class);
                                                        context.startActivity(i);
                                                        ((Activity)context).finish();
                                                        Toast.makeText(context, "Sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                xuLySua.cancel();
                                            }
                                        });
                                        ivCloseDialogThemSanPham.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogSuaSanPham.cancel();
                                            }
                                        });
                                        break;
                                }


                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });
            }

    @Override
    public int getItemCount() {
        return dssp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaSanPham;
        TextView tvTenSanPham;
        ImageView ivHinhSanPham, imv_menuSanPhamAdmin;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaSanPham = (TextView) itemView.findViewById(R.id.tvMaSanPhamAdmin);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPhamAdmin);
            ivHinhSanPham = (ImageView) itemView.findViewById(R.id.ivHinhSanPhamAdmin);
            imv_menuSanPhamAdmin = (ImageView) itemView.findViewById(R.id.ivMenuSanPhamAdmin);

        }
    }
    private void loadLoai(){

    }
}

