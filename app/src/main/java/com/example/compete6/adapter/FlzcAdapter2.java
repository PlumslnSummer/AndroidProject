package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.FlzcBean;
import com.example.compete6.home.lvshi.tab.FlzcListActivity;
import com.example.compete6.home.lvshi.tab.LvshiListActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class FlzcAdapter2 extends BaseAdapter {
    private List<FlzcBean.RowsBean> rowsBeanList=new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FlzcAdapter2(Context context) {
        this.context = context;
    }

    public List<FlzcBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<FlzcBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public FlzcBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.wuliu_item, null);
            viewHolder=new ViewHolder();
            viewHolder.linearWuliyItem = (LinearLayout) convertView.findViewById(R.id.linear_wuliy_item);
            viewHolder.ivWuliu = (ImageView) convertView.findViewById(R.id.iv_wuliu);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        FlzcBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivWuliu);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LvshiListActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private LinearLayout linearWuliyItem;
        private ImageView ivWuliu;
        private TextView tvName;
    }
}
