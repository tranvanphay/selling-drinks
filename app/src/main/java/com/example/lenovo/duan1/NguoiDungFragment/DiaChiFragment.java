package com.example.lenovo.duan1.NguoiDungFragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.duan1.Adapter.BangTinAdapter;
import com.example.lenovo.duan1.Adapter.DiaChiAdapter;
import com.example.lenovo.duan1.Model.Map;
import com.example.lenovo.duan1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaChiFragment extends Fragment implements OnMapReadyCallback {
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    GoogleMap mMap;
    ArrayList<Map> dsMap = new ArrayList<Map>();
    RecyclerView recyclerViewDiaChi;
    DiaChiAdapter diaChiAdapter;
    ArrayList<Double> latLong = new ArrayList<>();

    public DiaChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dia_chi, container, false);
        recyclerViewDiaChi = view.findViewById(R.id.recyclerView_map);

        loadMap();
        // Inflate the layout for this fragment
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//bang M
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                xulyQuyen();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new
                        String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        } else {
            xulyQuyen();
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                // TODO Auto-generated method stub
                Marker mym2;
                mym2 = mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Địa điểm")
                                .snippet(latLng.latitude + "," + latLng.longitude)
                                .icon(BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_ROSE)));

            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker arg0) {
                View info = getLayoutInflater().inflate(R.layout.markerinfor, null);
                TextView tv1 = ((TextView) info.findViewById(R.id.textView));
                tv1.setText(arg0.getTitle());
                TextView tv2 = ((TextView) info.findViewById(R.id.textView2));
                tv2.setText(arg0.getSnippet());
                return info;

            }
        });


        // Add a marker in Sydney and move the camera


        UiSettings uisetting = mMap.getUiSettings();
        uisetting.setCompassEnabled(true);
        uisetting.setZoomControlsEnabled(true);
        uisetting.setScrollGesturesEnabled(true);
        uisetting.setTiltGesturesEnabled(true);
        uisetting.setMyLocationButtonEnabled(true);

    }


    public void loadMap() {
        mData.child("Map").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                Map map = dataSnapshot.getValue(Map.class);
                map.setKeyMap(dataSnapshot.getKey());
                dsMap.add(map);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                recyclerViewDiaChi.setLayoutManager(layoutManager);
                diaChiAdapter = new DiaChiAdapter(dsMap, getContext());
                recyclerViewDiaChi.setAdapter(diaChiAdapter);
                xulyQuyen();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void xulyQuyen() {
        mMap.setMyLocationEnabled(true);
        LocationManager service = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, true);
        Location location = service.getLastKnownLocation(provider);
        for (int i = 0; i < dsMap.size(); i++) {
            double viDo = dsMap.get(i).viDo;
            double kinhDo = dsMap.get(i).kinhDo;
//            Toast.makeText(getContext(), "vĩ độ" + viDo + "\n" + "kinh đọ" + kinhDo, Toast.LENGTH_SHORT).show();
            LatLng viTri = new LatLng(viDo, kinhDo);
            LatLng toado = new LatLng(location.getLatitude(), location.getLongitude());
            Marker marker = mMap.addMarker(
                    new MarkerOptions()
                            .position(viTri)
                            .title("Deer Tea")
                            .snippet("")
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_ROSE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(viTri, 15));

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(getActivity(), "xx", Toast.LENGTH_SHORT).show();
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "xin duoc quyen roi", Toast.LENGTH_SHORT).show();
            xulyQuyen();
        }
    }





    }
