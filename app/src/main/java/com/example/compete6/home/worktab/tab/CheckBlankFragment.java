package com.example.compete6.home.worktab.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.compete6.R;
import com.example.compete6.bean.CheckBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvSex;
    private TextView tvGongzuo;
    private TextView tvTop;
    private TextView tvAddress;
    private TextView tvZhiwei;
    private TextView tvXinzi;
    private TextView tvJiaoyu;
    private TextView tvGeren;

    public CheckBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckBlankFragment newInstance(String param1, String param2) {
        CheckBlankFragment fragment = new CheckBlankFragment();
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
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private CheckBean.DataBean dataBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_check_blank, container, false);
        }
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView(rootview);
        initData();
        return rootview;
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getCheckJian(mParam1,2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getjianli(res);
                    tvName.setText("test");
                    tvEmail.setText("3091814513@qq.com");
                    tvPhone.setText("13800000000");
                    tvSex.setText("男");
                    tvGongzuo.setText(dataBean.getExperience());
                    tvTop.setText(dataBean.getMostEducation());
                    tvAddress.setText(dataBean.getAddress());
                    tvZhiwei.setText(String.valueOf(dataBean.getPositionId()));
                    tvXinzi.setText(dataBean.getMoney());
                    tvJiaoyu.setText(dataBean.getEducation());
                    tvGeren.setText(dataBean.getIndividualResume());
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
        tvName = (TextView) rootview.findViewById(R.id.tv_name);
        tvEmail = (TextView) rootview.findViewById(R.id.tv_email);
        tvPhone = (TextView) rootview.findViewById(R.id.tv_phone);
        tvSex = (TextView) rootview.findViewById(R.id.tv_sex);
        tvGongzuo = (TextView) rootview.findViewById(R.id.tv_gongzuo);
        tvTop = (TextView) rootview.findViewById(R.id.tv_top);
        tvAddress = (TextView) rootview.findViewById(R.id.tv_address);
        tvZhiwei = (TextView) rootview.findViewById(R.id.tv_zhiwei);
        tvXinzi = (TextView) rootview.findViewById(R.id.tv_xinzi);
        tvJiaoyu = (TextView) rootview.findViewById(R.id.tv_jiaoyu);
        tvGeren = (TextView) rootview.findViewById(R.id.tv_geren);
    }
}