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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.Adapter.LoaiApdaterAdmin;
import com.example.lenovo.duan1.Adapter.SanPhamAdapterAdmin;
import com.example.lenovo.duan1.Adapter.SanPhamSpinnerAdapter;
import com.example.lenovo.duan1.LoginActivity;
import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.SanPham;
import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuAdminFragment extends Fragment {
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    FirebaseAuth mAuthor=FirebaseAuth.getInstance();
    ArrayList<Loai> dsl=new ArrayList<Loai>();
    ArrayList<SanPham> dssp=new ArrayList<SanPham>();
    TextView tvCurrentDate,tv_welcomeback;
    ImageView ivThemLoai, ivThemSanPham,imv_themAnh,imv_themAnhSanPham,iv_Logout;
    RecyclerView recyclerViewBangTin,recyclerViewLoai,recyclerViewSanPham;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://duan1-ac840.appspot.com");
    public TrangChuAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_trang_chu_admin, container, false);
        recyclerViewLoai = view.findViewById(R.id.recyclerViewLoai);
        recyclerViewSanPham = view.findViewById(R.id.recyclerViewSanPham);
        recyclerViewBangTin = view.findViewById(R.id.recyclerViewBangTin);
        tvCurrentDate = view.findViewById(R.id.tvCurrentDate);
        ivThemLoai = view.findViewById(R.id.ivThemLoai);
        ivThemSanPham = view.findViewById(R.id.ivThemSanPham);
        iv_Logout=view.findViewById(R.id.imv_Logout);
        tv_welcomeback=view.findViewById(R.id.tv_welcomeback);
        tv_welcomeback.setText("Welcome back "+mAuthor.getCurrentUser().getEmail());
        loadLoai();
        loadSanPham();
        viewLoai();
        viewSanPham();

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
        String formattedDate = df.format(c);
        tvCurrentDate.setText(formattedDate);
        iv_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        getBangTin();

        ivThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemLoai = new Dialog(getActivity());
                dialogThemLoai.setContentView(R.layout.dialog_addloai);
                final EditText edt_maLoai=dialogThemLoai.findViewById(R.id.edt_maLoai);
                final EditText edt_tenLoai=dialogThemLoai.findViewById(R.id.edt_tenLoai);
                Button bt_themLoai=dialogThemLoai.findViewById(R.id.bt_themLoai);
                imv_themAnh=dialogThemLoai.findViewById(R.id.imv_themAnhLoai);
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
                                            String hinhLoai=downloadUri.toString();
                                            Loai loai=new Loai(maLoai,tenLoai,hinhLoai);

                                            mData.child("Loai").push().setValue(loai, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                    if(databaseError == null){
                                                        Toast.makeText(getActivity(), "Lưu loại thành công", Toast.LENGTH_SHORT).show();
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

                dialogThemLoai.show();

                ImageView ivCloseDialogThemLoai = dialogThemLoai.findViewById(R.id.ivCloseDialogThemLoai);
                ivCloseDialogThemLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThemLoai.cancel();
                    }
                });
            }
        });

        ivThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogThemSanPham = new Dialog(getActivity());
                dialogThemSanPham.setContentView(R.layout.dialog_addsanpham);
                imv_themAnhSanPham=dialogThemSanPham.findViewById(R.id.imv_themAnhSanPham);
                final EditText edt_maSanPham=dialogThemSanPham.findViewById(R.id.edt_maSanPham);
                final EditText edt_tenSanPham=dialogThemSanPham.findViewById(R.id.edt_tenSanPham);
                final Spinner spn_maLoai=dialogThemSanPham.findViewById(R.id.spnMaLoai);
                final EditText edt_chuThich=dialogThemSanPham.findViewById(R.id.edt_chuThich);
                final EditText edt_giaSanPham=dialogThemSanPham.findViewById(R.id.edt_giaSanPham);
                Button bt_themSanPham=dialogThemSanPham.findViewById(R.id.bt_themSanPham);
                dialogThemSanPham.show();
                SanPhamSpinnerAdapter adapter=new SanPhamSpinnerAdapter(getActivity(),dsl);
                spn_maLoai.setAdapter(adapter);
                imv_themAnhSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                        i.setType("image/*");
                        Intent cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Intent group=Intent.createChooser(i,"Source");
                        group.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{cam});
                        startActivityForResult(group,99);
                    }
                });
                bt_themSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        final StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
                        imv_themAnhSanPham.setDrawingCacheEnabled(true);
                        imv_themAnhSanPham.buildDrawingCache();
                        Bitmap bitmap = ((BitmapDrawable) imv_themAnhSanPham.getDrawable()).getBitmap();
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
                                imv_themAnhSanPham.setImageResource(R.drawable.ic_selecte_image);
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
                                            String maSanPham=edt_maSanPham.getText().toString();
                                            String tenSanPham=edt_tenSanPham.getText().toString();
                                            int index=spn_maLoai.getSelectedItemPosition();
                                            String tenLoai=dsl.get(index).tenLoai;
                                            String chuThich=edt_chuThich.getText().toString();
                                            int giaSanPham=Integer.parseInt(edt_giaSanPham.getText().toString());
                                            String hinhSanPham=downloadUri.toString();
                                            SanPham sanPham=new SanPham(maSanPham,tenLoai,tenSanPham,chuThich,giaSanPham,hinhSanPham);
                                            mData.child("SanPham").push().setValue(sanPham, new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                    if(databaseError == null){
                                                        Toast.makeText(getActivity(), "Lưu sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                                        edt_maSanPham.setText("");
                                                        edt_tenSanPham.setText("");
                                                        spn_maLoai.setSelection(0);
                                                        edt_chuThich.setText("");
                                                        edt_giaSanPham.setText("");

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

                ImageView ivCloseDialogThemSanPham = dialogThemSanPham.findViewById(R.id.ivCloseDialogThemSanPham);
                ivCloseDialogThemSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogThemSanPham.cancel();
                    }
                });
            }
        });



        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK){
            if(data.getExtras()!= null){
                Bundle b= data.getExtras();
                Bitmap bitmap=(Bitmap)b.get("data");
                imv_themAnh.setImageBitmap(bitmap);
            }
            else {
                Uri uri= data.getData();
                imv_themAnh.setImageURI(uri);
            }
        }
        if (requestCode==99 && resultCode==RESULT_OK){
            if(data.getExtras()!= null){
                Bundle b= data.getExtras();
                Bitmap bitmap=(Bitmap)b.get("data");
                imv_themAnhSanPham.setImageBitmap(bitmap);
            }
            else {
                Uri uri= data.getData();
                imv_themAnhSanPham.setImageURI(uri);
            }
        }
    }

    private void loadLoai(){
        mData.child("Loai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Loai loai=dataSnapshot.getValue(Loai.class);
                dsl.add(new Loai(loai.maLoai,loai.tenLoai,loai.hinhLoai));
                recyclerViewLoai.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewLoai.setLayoutManager(layoutManager);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),layoutManager.getOrientation());
                recyclerViewLoai.addItemDecoration(dividerItemDecoration);
                LoaiApdaterAdmin loaiApdaterAdmin = new LoaiApdaterAdmin(dsl,getContext());
                recyclerViewLoai.setAdapter(loaiApdaterAdmin);
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
    private void loadSanPham(){
        mData.child("SanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SanPham sanPham=dataSnapshot.getValue(SanPham.class);
                dssp.add(new SanPham(sanPham.maSanPham,sanPham.maLoai,sanPham.tenSanPham,sanPham.chuThich,sanPham.giaTien,sanPham.hinhSanPham));
                recyclerViewSanPham.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewSanPham.setLayoutManager(layoutManager);
                SanPhamAdapterAdmin sanPhamAdapterAdmin = new SanPhamAdapterAdmin(dssp,getContext());
                recyclerViewSanPham.setAdapter(sanPhamAdapterAdmin);
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

    public void viewLoai() {
        recyclerViewLoai.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewLoai.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),layoutManager.getOrientation());
        recyclerViewLoai.addItemDecoration(dividerItemDecoration);
        LoaiApdaterAdmin loaiApdaterAdmin = new LoaiApdaterAdmin(dsl,getContext());
        recyclerViewLoai.setAdapter(loaiApdaterAdmin);
    }

    public void viewSanPham() {
        recyclerViewSanPham.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewSanPham.setLayoutManager(layoutManager);
        SanPhamAdapterAdmin sanPhamAdapterAdmin = new SanPhamAdapterAdmin(dssp,getContext());
        recyclerViewSanPham.setAdapter(sanPhamAdapterAdmin);
    }
}






