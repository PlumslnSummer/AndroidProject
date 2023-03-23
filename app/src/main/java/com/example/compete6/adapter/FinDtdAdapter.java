package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.FindDtBean;
import com.example.compete6.home.congwutab.cwtab.WenzhenActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class FinDtdAdapter extends RecyclerView.Adapter<FinDtdAdapter.ViewHolder> {
    private List<FindDtBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private View view;
    private OnItemClickListener onItemClickListener;


    public FinDtdAdapter(Context context) {
        this.context = context;
    }

    public List<FindDtBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<FindDtBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.doctor_item_layout, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDoctorname.setText("医生姓名："+rowsBeanList.get(position).getName());
        holder.tvDoctorjobname.setText("职称："+rowsBeanList.get(position).getJobName());
        holder.tvGoodat.setText("擅长："+rowsBeanList.get(position).getGoodAt());
        holder.tvDoctorNo.setText("编号："+rowsBeanList.get(position).getPracticeNo());
        holder.tvDoctorWorkyears.setText("工作年限："+rowsBeanList.get(position).getWorkingYears());
        holder.itemView.setTag(position);
        Glide.with(context)
                .load(Contants.WEB_URL+rowsBeanList.get(position).getAvatar())
                .error(R.mipmap.ic_launcher)
                .into(holder.ivDoctor);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(rowsBeanList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }
    public interface OnItemClickListener{
        void onItemClick(FindDtBean.RowsBean rowsBean);
    }
    public void OnItemClickFangfa(OnItemClickListener onItemClickListener1){
        this.onItemClickListener=onItemClickListener1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDoctor;
        private TextView tvDoctorname;
        private TextView tvDoctorjobname;
        private TextView tvGoodat;
        private TextView tvDoctorNo;
        private TextView tvDoctorWorkyears;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDoctor = itemView.findViewById(R.id.iv_doctor);
            tvDoctorname = itemView.findViewById(R.id.tv_doctorname);
            tvDoctorjobname = itemView.findViewById(R.id.tv_doctorjobname);
            tvGoodat = itemView.findViewById(R.id.tv_goodat);
            tvDoctorNo = itemView.findViewById(R.id.tv_doctor_no);
            tvDoctorWorkyears = itemView.findViewById(R.id.tv_doctor_workyears);
        }
    }
}
