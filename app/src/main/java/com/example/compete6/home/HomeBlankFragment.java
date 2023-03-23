package com.example.compete6.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.compete6.R;
import com.example.compete6.adapter.BannerAdapter;
import com.example.compete6.adapter.MyFragmentAdapter;
import com.example.compete6.adapter.NewListAdapter;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.NewListBean;
import com.example.compete6.home.aixin.AixinActivity;
import com.example.compete6.home.bus.BusTabActivity;
import com.example.compete6.home.congwutab.CongwuActivity;
import com.example.compete6.home.laji.LajiActivity;
import com.example.compete6.home.lvshi.LvshiActivity;
import com.example.compete6.home.metrotab.MetroActivity;
import com.example.compete6.home.moneytab.MoneyTabActivity;
import com.example.compete6.home.movie.MovieTabActivity;
import com.example.compete6.home.qingnian.QingnianActivity;
import com.example.compete6.home.shuzi.ShuziActivity;
import com.example.compete6.home.tab.BannerTab0Activity;
import com.example.compete6.home.tab.ThemeActivity;
import com.example.compete6.home.worktab.WorkTabActivity;
import com.example.compete6.home.wuliutab.WuliuActivity;
import com.example.compete6.home.yuyue.YuyueActivity;
import com.example.compete6.home.zhengfu.ZhengfuActivity;
import com.example.compete6.home.zhiyuan.ZhiyuanActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;
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
 * Use the {@link HomeBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeBlankFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SearchView search;
    private Banner bannerHome;
    private SwipeRefreshLayout refresh;
    private LinearLayout linearBus;
    private LinearLayout linearYuyue;
    private LinearLayout linearZhiyuan;
    private LinearLayout linearMovie;
    private LinearLayout linearMoreservices;
    private TextView tvClass0;
    private TextView tvClass1;
    private TextView tvClass2;
    private TextView tvClass3;
    private TextView tvClass4;
    private TextView tvClass5, tv_current;
    private ViewPager2 viewHome;
    private LinearLayout linearWuliu;
    private LinearLayout linearMetro;
    private LinearLayout linearCongwu;
    private LinearLayout linearTheme;
    private LinearLayout linearAixin;
    private LinearLayout linearLvshi;
    private LinearLayout linearMoney;
    private LinearLayout linearWork;
    private LinearLayout linearShuzi;
    private LinearLayout linearQinnian;
    private LinearLayout linearLaji;
    private MyListView mylistviewHome;

    public HomeBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeBlankFragment newInstance(String param1, String param2) {
        HomeBlankFragment fragment = new HomeBlankFragment();
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
    private MyFragmentAdapter myFragmentAdapter;
    private Handler handler;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;

    private NewListAdapter newListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_home_blank, container, false);
        }
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        handler = new Handler();
        initView(rootview);
        id=9;
        initData();
        initBanner();
        return rootview;
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getNewList(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<NewListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getNewsList(res);
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
                            intent = new Intent(getContext(), BannerTab0Activity.class);
                            intent.putExtra("id", rowsBeanList.get(i).getId());
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


    private void initView(View rootview) {
        search = (SearchView) rootview.findViewById(R.id.search);
        bannerHome = (Banner) rootview.findViewById(R.id.banner_home);
        refresh = (SwipeRefreshLayout) rootview.findViewById(R.id.refresh);
        linearBus = (LinearLayout) rootview.findViewById(R.id.linear_bus);
        linearYuyue = (LinearLayout) rootview.findViewById(R.id.linear_yuyue);
        linearZhiyuan = (LinearLayout) rootview.findViewById(R.id.linear_zhiyuan);
        linearMovie = (LinearLayout) rootview.findViewById(R.id.linear_movie);
        linearMoreservices = (LinearLayout) rootview.findViewById(R.id.linear_moreservices);
        tvClass0 = (TextView) rootview.findViewById(R.id.tv_class0);
        tvClass1 = (TextView) rootview.findViewById(R.id.tv_class1);
        tvClass2 = (TextView) rootview.findViewById(R.id.tv_class2);
        tvClass3 = (TextView) rootview.findViewById(R.id.tv_class3);
        tvClass4 = (TextView) rootview.findViewById(R.id.tv_class4);
        tvClass5 = (TextView) rootview.findViewById(R.id.tv_class5);


        tv_current = tvClass0;
        tvClass0.setSelected(true);

        tvClass0.setOnClickListener(this);
        tvClass1.setOnClickListener(this);
        tvClass2.setOnClickListener(this);
        tvClass3.setOnClickListener(this);
        tvClass4.setOnClickListener(this);
        tvClass5.setOnClickListener(this);
        refresh.setOnRefreshListener(this);

        linearBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BusTabActivity.class);
                startActivity(intent);
            }
        });
        linearMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieTabActivity.class);
                startActivity(intent);
            }
        });
        linearYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), YuyueActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearZhiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ZhiyuanActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });

        linearWuliu = (LinearLayout) rootview.findViewById(R.id.linear_wuliu);
        linearMetro = (LinearLayout) rootview.findViewById(R.id.linear_metro);
        linearCongwu = (LinearLayout) rootview.findViewById(R.id.linear_congwu);

        linearCongwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CongwuActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearMetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MetroActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearWuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WuliuActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearTheme = (LinearLayout) rootview.findViewById(R.id.linear_theme);
        linearTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemeActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearMoreservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ZhengfuActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearAixin = (LinearLayout) rootview.findViewById(R.id.linear_aixin);
        linearLvshi = (LinearLayout) rootview.findViewById(R.id.linear_lvshi);

        linearAixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AixinActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearLvshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LvshiActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearMoney = (LinearLayout) rootview.findViewById(R.id.linear_money);
        linearMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MoneyTabActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearWork = (LinearLayout) rootview.findViewById(R.id.linear_work);
        linearWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WorkTabActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearShuzi = rootview.findViewById(R.id.linear_shuzi);
        linearQinnian = rootview.findViewById(R.id.linear_qinnian);
        linearLaji = rootview.findViewById(R.id.linear_laji);
        linearShuzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShuziActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearQinnian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QingnianActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });
        linearLaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LajiActivity.class);
                intent.putExtra("token", mParam1);
                startActivity(intent);
            }
        });

        mylistviewHome = rootview.findViewById(R.id.mylistview_home);
        newListAdapter=new NewListAdapter(getContext());
        mylistviewHome.setAdapter(newListAdapter);
    }

    @Override
    public void onClick(View v) {
        tv_current.setSelected(false);
        switch (v.getId()){
            case R.id.tv_class0:
                id=9;
                tv_current=tvClass0;
                tvClass0.setSelected(true);
                initData();
                break;
            case R.id.tv_class1:
                id=17;
                tv_current=tvClass1;
                tvClass1.setSelected(true);
                initData();
                break;
            case R.id.tv_class2:
                id=19;
                tv_current=tvClass2;
                tvClass2.setSelected(true);
                initData();
                break;
            case R.id.tv_class3:
                id=20;
                tv_current=tvClass3;
                tvClass3.setSelected(true);
                initData();
                break;
            case R.id.tv_class4:
                id=21;
                tv_current=tvClass4;
                tvClass4.setSelected(true);
                initData();
                break;
            case R.id.tv_class5:
                id=22;
                tv_current=tvClass5;
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
                refresh.setRefreshing(false);
            }
        }, 2000);
    }
}