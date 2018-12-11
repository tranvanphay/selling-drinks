package com.example.lenovo.duan1;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.duan1.Model.Loai;
import com.example.lenovo.duan1.Model.Map;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import dmax.dialog.SpotsDialog;

public class DiaChiActivity extends AppCompatActivity {
    EditText edt_kinhDo,edt_viDo,edt_diaChi;
    Button bt_themDiaDiem;
    ImageView imv_hinhDiaDiem;
    DatabaseReference mData=FirebaseDatabase.getInstance().getReference();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://duan1-ac840.appspot.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi);
        edt_kinhDo=findViewById(R.id.edt_kinhDo);
        edt_viDo=findViewById(R.id.edt_viDo);
        edt_diaChi=findViewById(R.id.edt_diaChi);
        bt_themDiaDiem=findViewById(R.id.bt_themDiaDiem);

        imv_hinhDiaDiem=findViewById(R.id.imv_hinhDiaDiem);
        imv_hinhDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent group = Intent.createChooser(i, "Source");
                group.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cam});
                startActivityForResult(group, 1);
            }
        });

        bt_themDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new SpotsDialog.Builder().setContext(DiaChiActivity.this).build();
                alertDialog.setMessage("Đang thêm");
                alertDialog.show();
                Calendar calendar = Calendar.getInstance();
                final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + ".png");
                imv_hinhDiaDiem.setDrawingCacheEnabled(true);
                imv_hinhDiaDiem.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imv_hinhDiaDiem.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                final UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(DiaChiActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                        imv_hinhDiaDiem.setImageResource(R.drawable.ic_selecte_image);
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
                                    String diaChi=edt_diaChi.getText().toString();
                                    double kinhDo= Double.parseDouble(edt_kinhDo.getText().toString());
                                    double viDo= Double.parseDouble(edt_viDo.getText().toString());
                                    String hinh = downloadUri.toString();
                                    if(diaChi.isEmpty()){
                                        Toast.makeText(DiaChiActivity.this, "Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
                                    }

                                    else{
                                        Map map = new Map(diaChi, kinhDo,viDo, hinh);

                                        mData.child("Map").push().setValue(map, new DatabaseReference.CompletionListener() {
                                            @Override
                                            public void onComplete(@com.google.firebase.database.annotations.Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                                if (databaseError == null) {
                                                    Toast.makeText(DiaChiActivity.this, "Lưu loại thành công", Toast.LENGTH_SHORT).show();
                                                    edt_diaChi.setText("");
                                                    edt_kinhDo.setText("");
                                                    edt_viDo.setText("");
                                                    alertDialog.cancel();
                                                } else {
                                                    Toast.makeText(DiaChiActivity.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }

                                    Log.d("Link", downloadUri + "");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&& resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                Bundle b = data.getExtras();
                Bitmap bitmap = (Bitmap) b.get("data");
                imv_hinhDiaDiem.setImageBitmap(bitmap);
            } else {
                Uri uri = data.getData();
                imv_hinhDiaDiem.setImageURI(uri);
            }
        }
    }
}
