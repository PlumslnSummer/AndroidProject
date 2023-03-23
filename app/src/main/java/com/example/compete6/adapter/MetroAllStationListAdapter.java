package com.example.compete6.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.MetroDeBean;

import java.util.ArrayList;
import java.util.List;

public class MetroAllStationListAdapter extends BaseAdapter {
    private List<MetroDeBean.DataBean.MetroStepListBean> metroStepListBeanList = new ArrayList<>();
    private Context context;
    private String station;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public MetroAllStationListAdapter(Context context) {
        this.context = context;
    }

    public List<MetroDeBean.DataBean.MetroStepListBean> getMetroStepListBeanList() {
        return metroStepListBeanList;
    }

    public void setMetroStepListBeanList(List<MetroDeBean.DataBean.MetroStepListBean> metroStepListBeanList) {
        this.metroStepListBeanList = metroStepListBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return metroStepListBeanList == null ? 0 : metroStepListBeanList.size();
    }

    @Override
    public MetroDeBean.DataBean.MetroStepListBean getItem(int position) {
        return metroStepListBeanList == null ? null : metroStepListBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.station_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvStation = (TextView) convertView.findViewById(R.id.tv_station);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        MetroDeBean.DataBean.MetroStepListBean metroStepListBean=getItem(position);
        if(metroStepListBean!=null){
            if(metroStepListBean.getName().equals(station)&&position!=1){
                viewHolder.tvStation.setTextColor(Color.parseColor("#08A7EF"));
            }
            viewHolder.tvStation.setText(metroStepListBean.getName());
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tvStation;
    }
}
