package com.example.compete6.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.compete6.R;

public class RefreshListView extends ListView {
    private int mHeight;
    private View footview;

    public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        //加载初始化布局
        intiRefreshListView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        //加载初始化布局
        intiRefreshListView();
    }

    public RefreshListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        //加载初始化布局
        intiRefreshListView();
    }
    /**
     * 初始化布局
     */
    public void intiRefreshListView(){
        //加载布局（用于显示加载的图标 ）
        footview=View.inflate(getContext(), R.layout.footview_layout, null);
        //将布局追加到ListView底部
        addFooterView(footview);
        //用尺子量高度（低版本安卓有严重的Bug,不支持相对布局，在Level 17版本后得到修复）
        footview.measure(0, 0);
        //-------宽偏移量---高偏移量

        //获取测量过后的高度
        mHeight=footview.getMeasuredHeight();
        //通过设置内边距，隐藏refresh_foot_view.xml布局
        footview.setPadding(0, mHeight, 0, 0);
        //设置监听ListView的滚动事件
        this.setOnScrollListener(new AbsListView.OnScrollListener() {
            /**
             * 滚动状态改变
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }
            /**
             * 滚动中
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                //当ListView滚动到最底部时
                if(getLastVisiblePosition()==getCount()-1){
                    //让refresh_foot_view.xml布局显示出来（显示加载中）
                    footview.setPadding(0, 0, 0, 0);
                    //判断是否在请求网络数据
                    if(loadingFlag==false){
                        if(onRefreshListener!=null){
                            onRefreshListener.RefeshData();
                            loadingFlag=true;//正在请求
                        }
                    }
                }
            }
        });
    }
    //是否正在请求网络,防止出现重复请求的情况（true表示正在请求，false不请求）
    private boolean loadingFlag=false;
    /**
     * 数据加载完毕要执行的方法
     */
    public void loadFinish(){
        //通过设置内边距，隐藏refresh_foot_view.xml布局
        footview.setPadding(0, mHeight, 0, 0);
        //请求完毕
        loadingFlag=true;
    }
    private OnRefreshListener onRefreshListener;

    /**
     * @param onRefreshListener the onRefreshListener to set
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * 自定义一个接口，用来刷新网络数据
     * @author Administrator
     *
     */
    public interface OnRefreshListener{
        void RefeshData();
    }
}
