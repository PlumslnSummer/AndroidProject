package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.compete6.R;
import com.example.compete6.bean.ZyPartyListBean;
import com.example.compete6.home.zhiyuan.tab.PartyDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ZyPartAdapter extends BaseAdapter {
    private List<ZyPartyListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public ZyPartAdapter(Context context) {
        this.context = context;
    }

    public List<ZyPartyListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ZyPartyListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ZyPartyListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.zhiyuan_party_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvZhiyuanTitle = (TextView) convertView.findViewById(R.id.tv_zhiyuan_title);
            viewHolder.tvZhiyuanChenban = (TextView) convertView.findViewById(R.id.tv_zhiyuan_chenban);
            viewHolder.tvZhiyuanPartytime = (TextView) convertView.findViewById(R.id.tv_zhiyuan_partytime);
            viewHolder.tvZhiyuanRen = (TextView) convertView.findViewById(R.id.tv_zhiyuan_ren);
            viewHolder.btnSubmit = (Button) convertView.findViewById(R.id.btn_submit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ZyPartyListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvZhiyuanTitle.setText(rowsBean.getTitle());
            viewHolder.tvZhiyuanChenban.setText("承办单位："+rowsBean.getUndertaker());
            viewHolder.tvZhiyuanPartytime.setText("活动开始时间："+rowsBean.getStartAt());
            viewHolder.tvZhiyuanRen.setText("人员要求："+rowsBean.getRequireText());
            viewHolder.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"报名成功",Toast.LENGTH_SHORT).show();
                }
            });
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PartyDetailActivity.class);
                intent.putExtra("party",rowsBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvZhiyuanTitle;
        private TextView tvZhiyuanChenban;
        private TextView tvZhiyuanPartytime;
        private TextView tvZhiyuanRen;
        private Button btnSubmit;
    }

}
