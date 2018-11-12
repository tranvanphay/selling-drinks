package com.example.lenovo.duan1.AdminFragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.duan1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinAdminFragment extends Fragment {
 ImageView imv;
 Button bt_upload;
    FirebaseStorage storage = FirebaseStorage.getInstance();


    public ThongTinAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.test_storage,container,false);
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://duan1-ac840.appspot.com");

        imv=view.findViewById(R.id.imv_getImage);
        bt_upload=view.findViewById(R.id.btUpload);
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
                StorageReference mountainsRef = storageRef.child("image"+calendar.getTimeInMillis()+".png");
                imv.setDrawingCacheEnabled(true);
                imv.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imv.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
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
                        Task<Uri> download=taskSnapshot.getStorage().getDownloadUrl();
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        Log.d("Link hình: ",download+"");

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
                imv.setImageBitmap(bitmap);
            }
            else {
                Uri uri= data.getData();
                imv.setImageURI(uri);
            }
        }
    }
}
