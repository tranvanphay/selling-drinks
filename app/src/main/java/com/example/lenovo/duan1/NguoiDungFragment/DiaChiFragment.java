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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.database.annotations.Nullable;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaChiFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    public DiaChiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dia_chi,container,false);



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
            if (ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                xulyQuyen();
                Toast.makeText(getActivity(), "e", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new
                        String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                Toast.makeText(getActivity(), "d", Toast.LENGTH_SHORT).show();
            }
        } else {
            xulyQuyen();
            Toast.makeText(getActivity(), "f", Toast.LENGTH_SHORT).show();
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
                                .title("dia diem")
                                .snippet(latLng.latitude +","+latLng.longitude)
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
                TextView tv1 = ((TextView)info.findViewById(R.id.textView));
                tv1.setText(arg0.getTitle());
                TextView tv2 = ((TextView)info.findViewById(R.id.textView2));
                tv2.setText(arg0.getSnippet());
                return  info;

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


    public void xulyQuyen() {
        mMap.setMyLocationEnabled(true);
        LocationManager service = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, true);
        Location location = service.getLastKnownLocation(provider);
        LatLng vitrihientai= new LatLng(10.782128,106.6542898);
        LatLng vitri3= new LatLng(10.7801134,106.6483486);
        LatLng vitri2=new LatLng(10.7877144,106.6343269);
        LatLng vitri4=new LatLng(10.7928644,106.650779);
        LatLng vitri5=new LatLng(10.7773374,106.6482842);
        LatLng vitri6=new LatLng(10.7868209,106.6401508);
        LatLng vitri7=new LatLng(10.7992751,106.6467218);
        LatLng vitri8=new LatLng(10.7960313,106.6410756);
        LatLng vitri9=new LatLng(10.79018,106.6217747);
        LatLng vitri10=new LatLng(10.7877144,106.6406021);
        LatLng toado = new LatLng(location.getLatitude(),location.getLongitude());
        Marker marker = mMap.addMarker(
                new MarkerOptions()
                        .position(vitrihientai)
                        .title("The Alley")
                        .snippet("")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_ROSE)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitrihientai, 15));
        Marker marker1 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri2)
                        .title("They Alley-Nguyễn Thị Minh Khai")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri2, 15));
        Marker marker2 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri3)
                        .title("They Alley-Nguyễn Thiện Thuật")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri3, 15));
        Marker marker3 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri4)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri4, 15));
        Marker marker4 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri5)
                        .title("They Alley-Nguyễn Văn Cừ")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri5, 15));
        Marker marker5 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri6)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri6, 15));
        Marker marker6 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri7)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri7, 15));
        Marker marker7 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri8)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri8, 15));
        Marker marker8 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri9)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri9, 15));
        Marker marker9 = mMap.addMarker(
                new MarkerOptions()
                        .position(vitri10)
                        .title("They Alley")
                        .snippet("noi dung")
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_RED)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vitri10, 15));



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
