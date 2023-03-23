package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.compete6.R;
import com.example.compete6.bean.PinglunBean;

import java.util.ArrayList;
import java.util.List;

public class PinglunAdapter extends BaseAdapter {
    private List<PinglunBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;


    public PinglunAdapter(Context context) {
        this.context = context;
    }

    public List<PinglunBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<PinglunBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public PinglunBean.DataBean getItem(int position) {
        return dataBeanList == null ? null : dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pinglun_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvContent = convertView.findViewById(R.id.tv_content);
            viewHolder.tvLikenum = convertView.findViewById(R.id.tv_likenum);
            viewHolder.btnDianzan = convertView.findViewById(R.id.btn_dianzan);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        PinglunBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText("评论人："+dataBean.getUserName());
            viewHolder.tvContent.setText("评论内容："+dataBean.getContent());
            viewHolder.tvLikenum.setText("点赞数："+dataBean.getLikeCount());
            viewHolder.btnDianzan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点赞成功",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
        private TextView tvContent;
        private TextView tvLikenum;
        private Button btnDianzan;
    }

}
