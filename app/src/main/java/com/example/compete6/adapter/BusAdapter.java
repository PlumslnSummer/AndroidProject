package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.BusBean;
import com.example.compete6.home.bus.BusDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends BaseAdapter {
    private List<BusBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public BusAdapter(Context context) {
        this.context = context;
    }

    public List<BusBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<BusBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public BusBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.bus_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvBusName = (TextView) convertView.findViewById(R.id.tv_bus_name);
            viewHolder.tvBusStart = (TextView) convertView.findViewById(R.id.tv_bus_start);
            viewHolder.tvBusEnd = (TextView) convertView.findViewById(R.id.tv_bus_end);
            viewHolder.tvBusTime = (TextView) convertView.findViewById(R.id.tv_bus_time);
            viewHolder.tvBusPrice = (TextView) convertView.findViewById(R.id.tv_bus_price);
            viewHolder.tvBusMile = (TextView) convertView.findViewById(R.id.tv_bus_mile);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        BusBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvBusName.setText("路线名称："+rowsBean.getName());
            viewHolder.tvBusStart.setText("出发点："+rowsBean.getFirst());
            viewHolder.tvBusEnd.setText("终点："+rowsBean.getEnd());
            viewHolder.tvBusTime.setText("运行时间："+rowsBean.getStartTime()+"——"+rowsBean.getEndTime());
            viewHolder.tvBusPrice.setText("票价："+rowsBean.getPrice()+"元");
            viewHolder.tvBusMile.setText("里程："+rowsBean.getMileage()+"km");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BusDetailActivity.class);
                intent.putExtra("bus",rowsBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvBusName;
        private TextView tvBusStart;
        private TextView tvBusEnd;
        private TextView tvBusTime;
        private TextView tvBusPrice;
        private TextView tvBusMile;
    }

}
