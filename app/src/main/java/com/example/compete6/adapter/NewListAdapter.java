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
import com.example.compete6.bean.NewListBean;
import com.example.compete6.news.NewDeActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class NewListAdapter extends BaseAdapter {
    private List<NewListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private CharSequence charSequence = null;
    private String token=null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NewListAdapter(Context context) {
        this.context = context;
    }

    public List<NewListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<NewListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public NewListBean.RowsBean getItem(int position) {
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
        NewListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvNewsTitle.setText(rowsBean.getTitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                charSequence= Html.fromHtml(rowsBean.getContent(),Html.FROM_HTML_MODE_LEGACY);
            }
            viewHolder.tvNewsContent.setText(charSequence);
            viewHolder.tvNewsCommentnum.setText("评论总数："+rowsBean.getCommentNum());
            viewHolder.tvNewsTime.setText("发布时间："+rowsBean.getPublishDate());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getCover())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivNews);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, NewDeActivity.class);
                intent.putExtra("id",rowsBean.getId());
                intent.putExtra("token",token);
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
