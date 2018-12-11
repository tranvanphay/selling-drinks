package com.example.lenovo.duan1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.duan1.ItemClickListener;
import com.example.lenovo.duan1.Model.Map;
import com.example.lenovo.duan1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiaChiAdapter extends RecyclerView.Adapter<DiaChiAdapter.ViewHolder> {


    ArrayList<Map> dsMap=new ArrayList<Map>();
    Context context;

    public DiaChiAdapter(ArrayList<Map> dsMap, Context context) {
        this.dsMap = dsMap;
        this.context = context;
    }

    @Override
    public DiaChiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.one_item_recycleview_map,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DiaChiAdapter.ViewHolder holder, int position) {
        holder.tv_diaChi.setText(dsMap.get(position).diaChi);
        Picasso.get().load(dsMap.get(position).linkHinh).into(holder.iv_hinhDiaChi);



    }

    @Override
    public int getItemCount() {
        return dsMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_diaChi;
        ImageView iv_hinhDiaChi;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_diaChi=itemView.findViewById(R.id.tv_diaChi);
            iv_hinhDiaChi=itemView.findViewById(R.id.iv_hinhDiaChi);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
