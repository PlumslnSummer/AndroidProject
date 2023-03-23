package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.compete6.R;
import com.example.compete6.bean.GongyiListBean;
import com.example.compete6.util.JsonParse;

import java.util.ArrayList;
import java.util.List;

public class GongyiListAdapter extends BaseAdapter {
    private List<GongyiListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public GongyiListAdapter(Context context) {
        this.context = context;
    }

    public List<GongyiListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<GongyiListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public GongyiListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.gongyi_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvType = (TextView) convertView.findViewById(R.id.tv_type);
            viewHolder.tvLeibie = (TextView) convertView.findViewById(R.id.tv_leibie);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
            viewHolder.tvMoneynow = (TextView) convertView.findViewById(R.id.tv_moneynow);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tvPeoplenum = (TextView) convertView.findViewById(R.id.tv_peoplenum);
            viewHolder.btnJuanzhu = (Button) convertView.findViewById(R.id.btn_juanzhu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        GongyiListBean.RowsBean rowsBean=getItem(position);
        GongyiListBean.RowsBean.TypeBean typeBean= JsonParse.getmInstance().getGongyiLeibie(res,position);
        if(rowsBean!=null){
            viewHolder.tvType.setText("项目类型:"+rowsBean.getName());
            viewHolder.tvLeibie.setText("公益类别:"+typeBean.getName());
            viewHolder.tvContent.setText("公益内容:"+rowsBean.getDescription());
            viewHolder.tvAuthor.setText("发布人:"+rowsBean.getAuthor());
            viewHolder.tvMoneynow.setText("已筹善款:"+rowsBean.getMoneyNow());
            viewHolder.tvTime.setText("项目时间:"+rowsBean.getActivityAt());
            viewHolder.tvPeoplenum.setText("参捐人数:"+rowsBean.getDonateCount());
            viewHolder.btnJuanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"捐助成功",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

    class ViewHolder{
        private TextView tvType;
        private TextView tvLeibie;
        private TextView tvContent;
        private TextView tvAuthor;
        private TextView tvMoneynow;
        private TextView tvTime;
        private TextView tvPeoplenum;
        private Button btnJuanzhu;
    }

}
