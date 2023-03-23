package com.example.compete6.bean;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MeMoryBitmap {
    public static MeMoryBitmap mInstance=null;

    public MeMoryBitmap() {
    }

    public static MeMoryBitmap getmInstance() {
        if(mInstance==null){
            mInstance=new MeMoryBitmap();
        }
        return mInstance;
    }
    /**
     * 网络获取俩，解析url获取图片
     * @param path
     * @return
     * @throws IOException
     */
    public static Bitmap getBitmap(String path) throws IOException {
        try {
            Log.d("url","bitmapurl000:"+path);
            URL url = new URL(path);
            //网络请求接口HttpURLConnection，可以实现简单的基于URL请求、响应功能。得到connection对象。
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //设置请求方式
            conn.setRequestMethod("GET");
            ////判断得到响应码
            if (conn.getResponseCode() == 200) {
                ////得到响应流
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解决内存泄漏问题
     * @param
     */
    public void recycleImageView(ImageView ivPhotoshow) {
        if(ivPhotoshow==null){return;}
        if(ivPhotoshow instanceof ImageView){
            Drawable drawable=ivPhotoshow.getDrawable();
            //instanceof 是 Java 的保留关键字，它的作用是测试它左边的对象是否是它右边的类的实例
            if(drawable instanceof BitmapDrawable){
                Bitmap bmp=((BitmapDrawable) drawable).getBitmap();
                if(bmp!=null&&!bmp.isRecycled()){
                    ivPhotoshow.setImageBitmap(null);
                    bmp.recycle();

                }
            }
        }
    }

    /**
     * 动态获取图片的缩放值
     *
     * @param options
     *            BitmapFactory.Options
     * @param reqWidth
     *            设定的Img控件宽度
     * @param reqHeight
     *            设定的Img控件高度
     * @return inSampleSize
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth)
        {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth)
            {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
