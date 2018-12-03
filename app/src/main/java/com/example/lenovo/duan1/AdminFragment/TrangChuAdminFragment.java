package com.example.lenovo.duan1.AdminFragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.Adapter.SanPhamAdapter;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuAdminFragment extends Fragment {
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    TextView tvCurrentDate;
    ImageView ivMenuThemLoai;
    ImageView imv_themAnh;
    ImageView ivCloseDialogThemLoai;
    RecyclerView recyclerViewBangTin,recyclerViewLoaiVaSanPham;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    ArrayList<Loai> dsLoai=new ArrayList<Loai>();
    public TrangChuAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trang_chu_admin,container,false);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://duan1-ac840.appspot.com");
        recyclerViewLoaiVaSanPham = view.findViewById(R.id.recyclerViewLoaiVaSanPham);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTin);
        tvCurrentDate = view.findViewById(R.id.tvCurrentDate);
        ivMenuThemLoai = view.findViewById(R.id.ivMenuThemLoai);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy"	);
        String formattedDate = df.format(c);
        tvCurrentDate.setText(formattedDate);


        getBangTin();

        ivMenuThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemLoai = new Dialog(getActivity());
                dialogThemLoai.setContentView(R.layout.dialog_addmenuloai);
                dialogThemLoai.show();
                final EditText edt_maLoai=dialogThemLoai.findViewById(R.id.edt_maLoai);
                final EditText edt_tenLoai=dialogThemLoai.findViewById(R.id.edt_tenLoai);
                Button bt_themLoai=dialogThemLoai.findViewById(R.id.bt_themLoai);
                imv_themAnh=dialogThemLoai.findViewById(R.id.imv_themAnh);
                ivCloseDialogThemLoai = dialogThemLoai.findViewById(R.id.ivCloseDialogThemLoai);
                ivCloseDialogThemLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThemLoai.cancel();
                    }
                });
                imv_themAnh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                        i.setType("image/*");
                        Intent cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Intent group=Intent.createChooser(i,"Source");
                        group.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{cam});
                        startActivityForResult(group,999);
                    }
                });
                bt_themLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        final StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
                        imv_themAnh.setDrawingCacheEnabled(true);
                        imv_themAnh.buildDrawingCache();
                        Bitmap bitmap = ((BitmapDrawable) imv_themAnh.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] data = baos.toByteArray();

                        final UploadTask uploadTask = mountainsRef.putBytes(data);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                // ...
//                                edtTenHinh.setText("");

                                imv_themAnh.setImageResource(R.drawable.ic_selecte_image);
                                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        if (!task.isSuccessful()) {
                                            throw task.getException();
                                        }

                                        // Continue with the task to get the download URL
                                        return mountainsRef.getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            String maLoai=edt_maLoai.getText().toString();
                                            String tenLoai=edt_tenLoai.getText().toString();
                                            String link=downloadUri.toString();
                                            List<SanPham> listNull=new ArrayList<>();
                                            Loai loai_test=new Loai(maLoai,tenLoai,link,listNull);

                                            mData.child("Loai").push().setValue(loai_test, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                    if(databaseError == null){
                                                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                                                        edt_maLoai.setText("");
                                                        edt_tenLoai.setText("");
                                                    }else {
                                                        Toast.makeText(getActivity(), "Lỗi!!!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                            Log.d("Link",downloadUri+"");
                                        } else {
                                            // Handle failures
                                            // ...
                                        }
                                    }
                                });
                            }
                        });
                    }


                });
            }
        });



        ArrayList<Loai> loais = new ArrayList<>();

        ArrayList<SanPham> sanPham1 = new ArrayList<>();
        sanPham1.add(new SanPham("1","maLoai","tra sua tran chau","rat ngon","50000"));
        sanPham1.add(new SanPham("2","maLoai","tra tran chau","rat ngon","50000"));

        Loai loai1 = new Loai("Loại 1", "1","....",sanPham1);
        loais.add(loai1);

        ArrayList<SanPham> sanPham2 = new ArrayList<>();
        sanPham2.add(new SanPham("4","maLoai","tra hoa qua","rat ngon","50000"));
        sanPham2.add(new SanPham("5","maLoai","sua tuoi","rat ngon","50000"));

        Loai loai2 = new Loai("Loại 2","1","....", sanPham2);
        loais.add(loai2);

        SanPhamAdapter adapter = new SanPhamAdapter(loais);
        recyclerViewLoaiVaSanPham.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewLoaiVaSanPham.setAdapter(adapter);

        return view;


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK){
            if(data.getExtras()!= null){
                Bundle b= data.getExtras();
                int x=imv_themAnh.getMaxWidth();
                int y=imv_themAnh.getMaxHeight();
                Bitmap bm=Bitmap.createScaledBitmap((Bitmap) b.get("data"),x,y,false);
                imv_themAnh.setImageBitmap(bm);
            }
            else {
                Uri uri= data.getData();
                imv_themAnh.setImageURI(uri);
            }
        }
    }
    private void loadData(){
        mData.child("Images").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Loai loai=dataSnapshot.getValue(Loai.class);
//                dsLoai.add(new Loai(loai.maLoai,loai.getTitle(),loai.linkHinh));
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
    }


    // Popup menu của loại
//    public void menuTXSLoai(View view) {
//        final ImageButton imgButtonMenuLoai = view.findViewById(R.id.imageButtonMenuLoai);
//
//        imgButtonMenuLoai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), imgButtonMenuLoai);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_txsloai, popupMenu.getMenu());
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//                            case R.id.xemLoai:
//                                Toast.makeText(MainActivity.this, "Xem", Toast.LENGTH_SHORT).show();
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//            }
//        });
//
//    }

    private void getBangTin(){
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

    private void recyclerViewBangTin(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewBangTin.setLayoutManager(layoutManager);
        BangTinAdapter bangTinAdapter = new BangTinAdapter(getActivity(),tenBangTin,hinhBangTin);
        recyclerViewBangTin.setAdapter(bangTinAdapter);

    }
}




