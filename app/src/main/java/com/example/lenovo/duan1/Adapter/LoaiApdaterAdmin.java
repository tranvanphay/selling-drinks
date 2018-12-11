package com.example.lenovo.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lenovo.duan1.AdminActivity;
import com.example.lenovo.duan1.AdminFragment.TrangChuAdminFragment;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class LoaiApdaterAdmin extends RecyclerView.Adapter<LoaiApdaterAdmin.ViewHolder> {
    ArrayList<Loai> dsl;
    Context context;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    ImageView imv_suaAnhLoai;


    public LoaiApdaterAdmin(ArrayList<Loai> dsl, Context context) {
        this.dsl = dsl;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_loai_admin, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvMaLoai.setText(dsl.get(position).maLoai);
        holder.tvTenLoai.setText(dsl.get(position).tenLoai);
        Picasso.get().load(dsl.get(position).hinhLoai).into(holder.ivHinhLoai);
        holder.ivMenuXoaSuaLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        android.widget.PopupMenu popupMenu = new android.widget.PopupMenu(context, v);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_xsloai, popupMenu.getMenu());

                        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.xoaLoai:
                                        AlertDialog xuLyXoa = new SpotsDialog.Builder().setContext(context).build();
                                        xuLyXoa.setMessage("Đang xóa");
                                        xuLyXoa.show();
                                        final Loai loai=dsl.get(position);
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        final DatabaseReference myRef = database.getReference("Loai");
                                        StorageReference storageReference=FirebaseStorage.getInstance().getReferenceFromUrl(loai.hinhLoai);
                                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                myRef.child(loai.getKeyLoai()).removeValue(new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

                                                    }
                                                });

                                            }
                                        });

                                        xuLyXoa.cancel();

                                        break;
                                    case R.id.suaLoai:
                                        final Dialog dialogSuaLoai = new Dialog(context) ;
                                        dialogSuaLoai.setContentView(R.layout.dialog_sualoai);
                                        imv_suaAnhLoai=dialogSuaLoai.findViewById(R.id.imv_suaAnhLoai);
                                        ImageView ivCloseDialogSuaLoai=dialogSuaLoai.findViewById(R.id.ivCloseDialogSuaLoai);
                                        final EditText edt_suaMaLoai=dialogSuaLoai.findViewById(R.id.edt_suaMaLoai);
                                        final EditText edt_suaTenLoai=dialogSuaLoai.findViewById(R.id.edt_suaTenLoai);
                                        Button bt_suaLoai=dialogSuaLoai.findViewById(R.id.bt_suaLoai);
                                        Picasso.get().load(dsl.get(position).hinhLoai).into(imv_suaAnhLoai);
                                        edt_suaMaLoai.setText(dsl.get(position).maLoai);
                                        edt_suaTenLoai.setText(dsl.get(position).tenLoai);
                                        imv_suaAnhLoai.setOnClickListener(new View.OnClickListener() {
                                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                            @Override
                                            public void onClick(View v) {
                                                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                                                i.setType("image/*");
                                                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                Intent group = Intent.createChooser(i, "Source");
                                                group.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cam});
                                                ((AdminActivity)context).startActivityForResult(group, 9);





                                            }
                                        });

                                        bt_suaLoai.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                AlertDialog xuLySua = new SpotsDialog.Builder().setContext(context).build();
                                                xuLySua.setMessage("Đang sửa");
                                                xuLySua.show();
                                                String key=dsl.get(position).getKeyLoai();
                                                String linkHinh=dsl.get(position).hinhLoai;
                                                String tenLoai=edt_suaTenLoai.getText().toString();
                                                String maLoai=edt_suaMaLoai.getText().toString();
                                                Loai loai1=new Loai(maLoai,tenLoai,linkHinh);
                                                mData.child("Loai").child(key).setValue(loai1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        dialogSuaLoai.dismiss();
                                                        Intent i=new Intent(context,AdminActivity.class);
                                                        context.startActivity(i);
                                                        ((Activity)context).finish();
                                                        Toast.makeText(context, "Sửa thành công!!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                xuLySua.cancel();
                                            }
                                        });
                                        ivCloseDialogSuaLoai.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialogSuaLoai.cancel();
                                            }
                                        });


                                        dialogSuaLoai.show();

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
        return dsl.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaLoai;
        TextView tvTenLoai;
        ImageView ivHinhLoai;
        ImageView ivMenuXoaSuaLoai;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaLoai = (TextView) itemView.findViewById(R.id.tvMaLoaiAdmin);
            tvTenLoai = (TextView) itemView.findViewById(R.id.tvTenLoaiAdmin);
            ivHinhLoai = (ImageView) itemView.findViewById(R.id.ivHinhLoaiAdmin);
            ivMenuXoaSuaLoai = (ImageView) itemView.findViewById(R.id.ivMenuLoaiAdmin);
        }



    }


}
