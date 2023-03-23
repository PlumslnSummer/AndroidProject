package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.ResultBean;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends BaseAdapter {
    private List<ResultBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public ResultAdapter(Context context) {
        this.context = context;
    }

    public List<ResultBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ResultBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ResultBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.result_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivResult = convertView.findViewById(R.id.iv_result);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ResultBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivResult);
        }
        return convertView;
    }
    class ViewHolder{
        private ImageView ivResult;
        private TextView tvName;
    }

}
