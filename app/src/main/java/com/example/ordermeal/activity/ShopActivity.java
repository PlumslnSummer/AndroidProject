package com.example.ordermeal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermeal.R;
import com.example.ordermeal.adapter.ShopAdapter;
import com.example.ordermeal.bean.ShopBean;
import com.example.ordermeal.utils.Constant;
import com.example.ordermeal.listview.ShopListView;
import com.example.ordermeal.utils.JsonParse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity {

    private TextView tv_back,tv_title;
    private ShopListView slv_list;
    private ShopAdapter adapter;
    public static final int MSG_SHOP_OK=1;
    private MHandler mHandler;
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mHandler=new MHandler();
        initData();
        init();
    }

    private void init() {
        tv_back=findViewById(R.id.tv_back);
        tv_title=findViewById(R.id.tv_title);
        tv_title.setText("店铺");
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.blue_color));

        tv_back.setVisibility(View.GONE);
        slv_list=(ShopListView) findViewById(R.id.slv_list);
        adapter=new ShopAdapter(this);
        slv_list.setAdapter(adapter);
    }

    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(Constant.WEB_SITE+Constant.REQUEST_SHOP_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public  void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res=response.body().string();
                Message msg=new Message();
                msg.what=MSG_SHOP_OK;
                msg.obj=res;
                mHandler.sendMessage(msg);
            }
        });
    }

    class MHandler extends Handler{
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_SHOP_OK:
                    if(msg.obj!=null){
                        String vlResult=(String) msg.obj;
                        List<ShopBean> pythonList= JsonParse.getInstance().getShopList(vlResult);
                        adapter.setData(pythonList);
                    }
                    break;
            }
        }
    }
    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK
            && event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(ShopActivity.this,"再按一次退出订餐应用",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                ShopActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}