package com.example.lenovo.duan1.AdminFragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.HinhAnhAdapter;
import com.example.lenovo.duan1.R;
import com.example.lenovo.duan1.model.TestStorage;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinAdminFragment extends Fragment {
 ImageView imv;
 Button bt_upload;
    ListView lv_hinhAnh;
 DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ArrayList<TestStorage> dsh=new ArrayList<TestStorage>();
    HinhAnhAdapter adapter;


    public ThongTinAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.test_storage,container,false);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://duan1-ac840.appspot.com");
        final EditText edtTenHinh=view.findViewById(R.id.ettenHinh);
        lv_hinhAnh=view.findViewById(R.id.listHinh);
        adapter=new HinhAnhAdapter(getActivity(),dsh);
        lv_hinhAnh.setAdapter(adapter);
        loadData();
        imv=view.findViewById(R.id.imv_getImage);
        bt_upload=view.findViewById(R.id.btUpload);
        mData=FirebaseDatabase.getInstance().getReference();
        imv.setOnClickListener(new View.OnClickListener() {
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
        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                final StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
                imv.setDrawingCacheEnabled(true);
                imv.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imv.getDrawable()).getBitmap();
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
                        edtTenHinh.setText("");
                        imv.setImageResource(android.R.drawable.ic_menu_report_image);
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
                                    String TenHinh=edtTenHinh.getText().toString();
                                    String link=downloadUri.toString();
                                    TestStorage HinhAnh=new TestStorage(TenHinh,link);

                                    mData.child("Images").push().setValue(HinhAnh, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                            if(databaseError == null){
                                                Toast.makeText(getActivity(), "Lưu link thành công", Toast.LENGTH_SHORT).show();
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

        return view;

    }
    private void loadData(){
        mData.child("Images").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                TestStorage hinhAnh=dataSnapshot.getValue(TestStorage.class);
                dsh.add(new TestStorage(hinhAnh.tenHinh,hinhAnh.linkHinh));
                adapter.notifyDataSetChanged();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999 && resultCode==RESULT_OK){
            if(data.getExtras()!= null){
                Bundle b= data.getExtras();
                Bitmap bitmap=(Bitmap)b.get("data");
                imv.setImageBitmap(bitmap);
            }
            else {
                Uri uri= data.getData();
                imv.setImageURI(uri);
            }
        }
    }
}
