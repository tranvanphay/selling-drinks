package com.example.lenovo.duan1.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SanPham implements Parcelable {
    public String maSanPham;
    public String maLoai;
    public String tenSanPham;
    public String chuThich;
    public String giaTien;

    public SanPham(String maSanPham, String maLoai, String tenSanPham, String chuThich, String giaTien) {
        this.maSanPham = maSanPham;
        this.maLoai = maLoai;
        this.tenSanPham = tenSanPham;
        this.chuThich = chuThich;
        this.giaTien = giaTien;
    }

    protected SanPham(Parcel in) {
        maSanPham = in.readString();
        maLoai = in.readString();
        tenSanPham = in.readString();
        chuThich = in.readString();
        giaTien = in.readString();
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(maSanPham);
        parcel.writeString(maLoai);
        parcel.writeString(tenSanPham);
        parcel.writeString(chuThich);
        parcel.writeString(giaTien);
    }
}
