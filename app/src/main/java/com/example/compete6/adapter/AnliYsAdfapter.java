package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.Anlibean;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class AnliYsAdfapter extends BaseAdapter {
    private Anlibean.RowsBean.DoctorBean doctorBeanList = new Anlibean.RowsBean.DoctorBean();
    private Context context;


    public AnliYsAdfapter(Context context) {
        this.context = context;
    }

    public Anlibean.RowsBean.DoctorBean getDoctorBeanList() {
        return doctorBeanList;
    }

    public void setDoctorBeanList(Anlibean.RowsBean.DoctorBean doctorBeanList) {
        this.doctorBeanList = doctorBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Anlibean.RowsBean.DoctorBean getItem(int position) {
//        return doctorBeanList == null ? null : doctorBeanList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.anli_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivYs = (ImageView) convertView.findViewById(R.id.iv_ys);
            viewHolder.tvCwysName = (TextView) convertView.findViewById(R.id.tv_cwys_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Anlibean.RowsBean.DoctorBean doctorBean=getItem(position);
        if(doctorBean!=null){
            viewHolder.tvCwysName.setText("医生姓名："+doctorBean.getName());

            Glide.with(context)
                    .load(Contants.WEB_URL+doctorBean.getAvatar())
                    .error(R.drawable.head)
                    .into(viewHolder.ivYs);
        }

        return convertView;
    }

    class ViewHolder{
        private ImageView ivYs;
        private TextView tvCwysName;
        private TextView tvCwysAnli;
    }

}
