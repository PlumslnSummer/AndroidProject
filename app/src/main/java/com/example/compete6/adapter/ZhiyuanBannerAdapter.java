package com.example.compete6.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.util.Contants;
import com.youth.banner.loader.ImageLoader;

public class ZhiyuanBannerAdapter extends ImageLoader {
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        ZhiyuanBannerBean.DataBean dataBean=(ZhiyuanBannerBean.DataBean)o;
        Glide.with(context)
                .load(Contants.WEB_URL+dataBean.getImgUrl())
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
