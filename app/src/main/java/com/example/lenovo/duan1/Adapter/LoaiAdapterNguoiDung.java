//package com.example.lenovo.duan1.Adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.lenovo.duan1.Model.Loai;
//import com.example.lenovo.duan1.R;
//
//import java.util.ArrayList;
//
//public class LoaiAdapterNguoiDung  extends RecyclerView.Adapter<LoaiAdapterNguoiDung.ViewHolder> {
//    ArrayList<Loai> listloai;
//    Context context;
//
//    public LoaiAdapterNguoiDung(ArrayList<Loai> listloai, Context context) {
//        this.listloai = listloai;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemView = layoutInflater.inflate(R.layout.oneitem_recyclerview_loai_nguoidung,parent,false);
//
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tvMaLoai.setText(listloai.get(position).getMaLoai());
//        holder.tvTenLoai.setText(listloai.get(position).getTenLoai());
//        holder.ivHinhLoai.setImageResource(Integer.parseInt(listloai.get(position).getHinhLoai()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return listloai.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView tvMaLoai;
//        TextView tvTenLoai;
//        ImageView ivHinhLoai;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            tvMaLoai = (TextView)itemView.findViewById(R.id.tvMaLoaiNguoiDung);
//            tvTenLoai = (TextView)itemView.findViewById(R.id.tvTenLoaiNguoiDung);
//            ivHinhLoai = (ImageView)itemView.findViewById(R.id.ivHinhLoaiNguoiDung);
//        }
//    }
//}
//
