package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.AixintuijianBean;
import com.example.compete6.bean.NewListBean;
import com.example.compete6.home.aixin.tab.AixinDetailActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class AixintuijianAdapter extends BaseAdapter {
    private List<AixintuijianBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private CharSequence charSequence = null;


    public AixintuijianAdapter(Context context) {
        this.context = context;
    }

    public List<AixintuijianBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<AixintuijianBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public AixintuijianBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.news_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivNews = (ImageView) convertView.findViewById(R.id.iv_news);
            viewHolder.tvNewsTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
            viewHolder.tvNewsContent = (TextView) convertView.findViewById(R.id.tv_news_content);
            viewHolder.tvNewsCommentnum = (TextView) convertView.findViewById(R.id.tv_news_commentnum);
            viewHolder.tvNewsTime = (TextView) convertView.findViewById(R.id.tv_news_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        AixintuijianBean.RowsBean rowsBean=getItem(position);
       if(rowsBean!=null){
           viewHolder.tvNewsTitle.setText("公益项目名称："+rowsBean.getName());
           viewHolder.tvNewsContent.setText("发布组织："+rowsBean.getAuthor());
           viewHolder.tvNewsCommentnum.setText("捐赠人数:"+rowsBean.getDonateCount());
           viewHolder.tvNewsTime.setText("已筹善款："+rowsBean.getMoneyNow());
           Glide.with(context)
                   .load(Contants.WEB_URL+rowsBean.getImgUrl())
                   .error(R.mipmap.ic_launcher)
                   .into(viewHolder.ivNews);
        }
       convertView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, AixinDetailActivity.class);
               intent.putExtra("gongyi",rowsBean);
               context.startActivity(intent);
           }
       });
        return convertView;
    }

    class ViewHolder{
        private ImageView ivNews;
        private TextView tvNewsTitle;
        private TextView tvNewsContent;
        private TextView tvNewsCommentnum;
        private TextView tvNewsTime;
    }
}
