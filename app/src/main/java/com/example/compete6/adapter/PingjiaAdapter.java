package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.PingjiaBean;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class PingjiaAdapter extends BaseAdapter {
    private List<PingjiaBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public PingjiaAdapter(Context context) {
        this.context = context;
    }

    public List<PingjiaBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<PingjiaBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public PingjiaBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.pingjia_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivHead = (ImageView) convertView.findViewById(R.id.iv_head);
            viewHolder.tvPingjiaTime = (TextView) convertView.findViewById(R.id.tv_pingjia_time);
            viewHolder.tvPingjiaContent = (TextView) convertView.findViewById(R.id.tv_pingjia_content);
            viewHolder.tvLikenum = (TextView) convertView.findViewById(R.id.tv_likenum);
            viewHolder.ivLike = (ImageView) convertView.findViewById(R.id.iv_like);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        PingjiaBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvPingjiaTime.setText("评价时间："+rowsBean.getCreateTime());
            viewHolder.tvPingjiaContent.setText("评价内容："+rowsBean.getEvaluateContent());
            viewHolder.tvLikenum.setText("点赞数量："+rowsBean.getLikeCount());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getFromUserAvatar())
                    .error(R.drawable.head)
                    .into(viewHolder.ivHead);
            viewHolder.ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
    class ViewHolder{
        private ImageView ivHead;
        private TextView tvPingjiaTime;
        private TextView tvPingjiaContent;
        private TextView tvLikenum;
        private ImageView ivLike;
    }

}
