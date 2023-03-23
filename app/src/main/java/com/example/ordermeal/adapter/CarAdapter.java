package com.example.ordermeal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ordermeal.R;
import com.example.ordermeal.bean.FoodBean;


import java.math.BigDecimal;
import java.util.List;

public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> fbl;
    private OnSelectLiatener onSelectListener;

    public CarAdapter(Context context, OnSelectLiatener onSelectListener) {
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }
    public void setData(List<FoodBean> fbl){
        this.fbl=fbl;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return fbl==null?0:fbl.size();
    }

    @Override
    public FoodBean getItem(int position) {
        return fbl==null?null:fbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(null==convertView){
            vh=new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.car_item, null);
            vh.tv_food_name=convertView.findViewById(R.id.tv_food_name);
            vh.tv_food_count=convertView.findViewById(R.id.tv_food_count);
            vh.tv_food_price=convertView.findViewById(R.id.tv_food_price);
            vh.iv_add=convertView.findViewById(R.id.iv_add);
            vh.iv_minus=convertView.findViewById(R.id.iv_minus);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        final FoodBean bean=getItem(position);
        if(bean!=null){
            vh.tv_food_name.setText(bean.getFoodName());
            vh.tv_food_count.setText(bean.getCount()+"");
            BigDecimal count=BigDecimal.valueOf(bean.getCount());
            vh.tv_food_price.setText("￥"+bean.getPrice().multiply(count));
        }
        vh.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectListener.onSelectAdd(position,vh.tv_food_price,vh.tv_food_count);
            }
        });
        vh.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectListener.onSelectMis(position,vh.tv_food_price,vh.tv_food_count);
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView tv_food_name,tv_food_count,tv_food_price;
        public ImageView iv_add,iv_minus;
    }
    public interface OnSelectLiatener{
        void onSelectAdd(int position,TextView tv_food_price,TextView tv_food_count);
        void onSelectMis(int position,TextView tv_food_price,TextView tv_food_count);
    }
}
