package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.Anlibean;
import com.example.compete6.home.congwutab.cwtab.AnliDetailActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

public class AnliAdapter extends BaseAdapter {
    private List<Anlibean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    public Anlibean.RowsBean.DoctorBean doctorBean=new Anlibean.RowsBean.DoctorBean();
    private String res=null;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public AnliAdapter(Context context) {
        this.context = context;
    }

    public List<Anlibean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<Anlibean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return rowsBeanList.size();
    }

    @Override
    public Anlibean.RowsBean getItem(int position) {
        return rowsBeanList == null ? null : rowsBeanList.get(position);
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
            viewHolder.tvCwysAnli = (TextView) convertView.findViewById(R.id.tv_cwys_anli);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Anlibean.RowsBean rowsBean=getItem(position);
        doctorBean=JsonParse.getmInstance().getAlys(res,position);

        if(rowsBean!=null){

            viewHolder.tvCwysName.setText("医生姓名："+doctorBean.getName());
            viewHolder.tvCwysAnli.setText("案例描述："+rowsBean.getQuestion());
            Glide.with(context)
                    .load(Contants.WEB_URL+doctorBean.getAvatar())
                    .error(R.drawable.head)
                    .into(viewHolder.ivYs);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AnliDetailActivity.class);
                intent.putExtra("anli",rowsBean);
                intent.putExtra("doctor",doctorBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private ImageView ivYs;
        private TextView tvCwysName;
        private TextView tvCwysAnli;
    }

}
