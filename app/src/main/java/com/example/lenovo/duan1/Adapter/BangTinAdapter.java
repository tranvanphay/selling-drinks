package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.duan1.R;

import java.util.ArrayList;

public class BangTinAdapter extends RecyclerView.Adapter<BangTinAdapter.ViewHolder> {

    private static final String TAG = "BangTinAdapter";
    private ArrayList<String> tenBangTin = new ArrayList<>();
    private ArrayList<String> hinhBangTin = new ArrayList<>();
    private Context context;

    public BangTinAdapter(Context context,ArrayList<String> tenBangTin, ArrayList<String> hinhBangTin) {
        this.tenBangTin = tenBangTin;
        this.hinhBangTin = hinhBangTin;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_bangtin, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(context)
                .asBitmap()
                .load(hinhBangTin.get(position))
                .into(holder.iv_bangtin);

        holder.tv_bangtin.setText(tenBangTin.get(position));
        holder.iv_bangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + tenBangTin.get(position));
                Toast.makeText(context, tenBangTin.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return hinhBangTin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_bangtin;
        TextView tv_bangtin;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_bangtin = itemView.findViewById(R.id.iv_bangtin);
            tv_bangtin = itemView.findViewById(R.id.tv_bangtin);
        }
    }
}
