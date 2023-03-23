package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.LajifenBean;
import com.example.compete6.bean.SearchHotBean;
import com.example.compete6.home.laji.LajiActivity;

import java.util.ArrayList;
import java.util.List;

public class LajiFenAdapter extends BaseAdapter {
    private List<SearchHotBean.DataBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public LajiFenAdapter(Context context) {
        this.context = context;
    }

    public List<SearchHotBean.DataBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<SearchHotBean.DataBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public SearchHotBean.DataBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.lajifen_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        SearchHotBean.DataBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getKeyword());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LajiActivity.class);
                intent.putExtra("id",rowsBean.getId());
                intent.putExtra("token",token);
                context.startActivity(intent);

            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
    }

}
