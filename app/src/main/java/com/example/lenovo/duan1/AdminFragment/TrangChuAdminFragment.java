package com.example.lenovo.duan1.AdminFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuAdminFragment extends Fragment {
TextView tvRead;
    public TrangChuAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trang_chu_admin,container,false);
        tvRead=view.findViewById(R.id.tvRead);
        // Read from the database
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference();
        myRef.child("Tên").setValue("tvxg");
        // Read from the database
        myRef.child("Tên").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                tvRead.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                tvRead.setText("Failed");
            }
        });


        return view;

    }

}
