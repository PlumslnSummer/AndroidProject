package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.ZhiyuanNewListBean;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class ZhiyuanNewListAdapter extends BaseAdapter {
    private List<ZhiyuanNewListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public ZhiyuanNewListAdapter(Context context) {
        this.context = context;
    }

    public List<ZhiyuanNewListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ZhiyuanNewListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ZhiyuanNewListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.zhiyuan_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivZhiyuanNews = (ImageView) convertView.findViewById(R.id.iv_zhiyuan_news);
            viewHolder.tvZhiyuanContent = (TextView) convertView.findViewById(R.id.tv_zhiyuan_content);
            viewHolder.tvZhiyuanTime = (TextView) convertView.findViewById(R.id.tv_zhiyuan_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ZhiyuanNewListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvZhiyuanContent.setText(rowsBean.getSummary());
            viewHolder.tvZhiyuanTime.setText("发布时间："+rowsBean.getCreateTime());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivZhiyuanNews);
        }
        return convertView;
    }
    class ViewHolder{
        private ImageView ivZhiyuanNews;
        private TextView tvZhiyuanContent;
        private TextView tvZhiyuanTime;
    }

}
