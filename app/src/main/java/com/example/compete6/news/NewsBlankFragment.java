package com.example.compete6.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.compete6.R;
import com.example.compete6.adapter.BannerAdapter;
import com.example.compete6.adapter.NewListAdapter;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.NewListBean;
import com.example.compete6.home.tab.BannerTab0Activity;
import com.example.compete6.home.tab.BannerTab1Activity;
import com.example.compete6.home.tab.BannerTab2Activity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.RefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsBlankFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Banner bannerHome;
    private TextView tvClass0;
    private TextView tvClass1;
    private TextView tvClass2;
    private TextView tvClass3;
    private TextView tvClass4;
    private TextView tvClass5, tv_current;
    private SwipeRefreshLayout refresh;
    private ViewPager2 viewHome;
    private RefreshListView relistview;

    public NewsBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsBlankFragment newInstance(String param1, String param2) {
        NewsBlankFragment fragment = new NewsBlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View rootview = null;
    private Handler handler;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private NewListAdapter newListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_news_blank, container, false);
        }
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        handler = new Handler();
        id = 9;
        initView(rootview);
        initBanner();

        return rootview;
    }

    private void initBanner() {
        Call<ResponseBody> call = httpbinServices.getHomeBanner();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<BannerBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getBannerList(res);
                    bannerHome.setImages(rowsBeanList);
                    bannerHome.setImageLoader(new BannerAdapter());
                    bannerHome.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            Intent intent = null;
                            if (i == 0) {
                                intent = new Intent(getContext(), BannerTab0Activity.class);
                            } else if (i == 1) {
                                intent = new Intent(getContext(), BannerTab1Activity.class);
                            } else if (i == 2) {
                                intent = new Intent(getContext(), BannerTab2Activity.class);
                            }
                            startActivity(intent);
                        }
                    });
                    bannerHome.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getNewList(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<NewListBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getNewsList(res);
                    newListAdapter.setRowsBeanList(rowsBeanList);
                    newListAdapter.setToken(mParam1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initView(View rootview) {
        bannerHome = (Banner) rootview.findViewById(R.id.banner_home);
        tvClass0 = (TextView) rootview.findViewById(R.id.tv_class0);
        tvClass1 = (TextView) rootview.findViewById(R.id.tv_class1);
        tvClass2 = (TextView) rootview.findViewById(R.id.tv_class2);
        tvClass3 = (TextView) rootview.findViewById(R.id.tv_class3);
        tvClass4 = (TextView) rootview.findViewById(R.id.tv_class4);
        tvClass5 = (TextView) rootview.findViewById(R.id.tv_class5);
        refresh = (SwipeRefreshLayout) rootview.findViewById(R.id.refresh);
        relistview =rootview.findViewById(R.id.relistview);

        tv_current = tvClass0;
        tvClass0.setSelected(true);

        tvClass0.setOnClickListener(this);
        tvClass1.setOnClickListener(this);
        tvClass2.setOnClickListener(this);
        tvClass3.setOnClickListener(this);
        tvClass4.setOnClickListener(this);
        tvClass5.setOnClickListener(this);
        refresh.setOnRefreshListener(this);
       newListAdapter=new NewListAdapter(getContext());
       relistview.setAdapter(newListAdapter);
       relistview.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
           @Override
           public void RefeshData() {
               initData();
               relistview.loadFinish();
           }
       });
    }

    @Override
    public void onClick(View v) {
        tv_current.setSelected(false);
        switch (v.getId()) {
            case R.id.tv_class0:
                id = 9;
                tv_current = tvClass0;
                tvClass0.setSelected(true);
                initData();
                break;
            case R.id.tv_class1:
                id = 17;
                tv_current = tvClass1;
                tvClass1.setSelected(true);
                initData();
                break;
            case R.id.tv_class2:
                id = 19;
                tv_current = tvClass2;
                tvClass2.setSelected(true);
                initData();
                break;
            case R.id.tv_class3:
                id = 20;
                tv_current = tvClass3;
                tvClass3.setSelected(true);
                initData();
                break;
            case R.id.tv_class4:
                id = 21;
                tv_current = tvClass4;
                tvClass4.setSelected(true);
                initData();
                break;
            case R.id.tv_class5:
                id = 22;
                tv_current = tvClass5;
                tvClass5.setSelected(true);
                initData();
                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                refresh.setRefreshing(false);
            }
        }, 2000);
    }
}