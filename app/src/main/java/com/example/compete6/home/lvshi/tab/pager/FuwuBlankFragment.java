package com.example.compete6.home.lvshi.tab.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.LvshiDetailBean;
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
 * Use the {@link FuwuBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuwuBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView ivHead;
    private TextView tvBeijin;
    private TextView tvWorktime;
    private TextView tvNo;
    private TextView tvDes;

    public FuwuBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FuwuBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FuwuBlankFragment newInstance(String param1, String param2) {
        FuwuBlankFragment fragment = new FuwuBlankFragment();
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
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_fuwu_blank, container, false);
        }
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView(rootview);
        initData();
        return rootview;
    }

    private void initData() {
        id=Integer.parseInt(mParam2);
        Call<ResponseBody> call=httpbinServices.getLvshidetail(mParam1,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    LvshiDetailBean.DataBean  dataBean= JsonParse.getmInstance().getLvshiDetail(res);
                    tvBeijin.setText("教育背景："+dataBean.getEduInfo());
                    tvWorktime.setText("从业年限："+dataBean.getWorkStartAt());
                    tvNo.setText("执业证号："+dataBean.getLicenseNo());
                    Glide.with(getContext())
                            .load(Contants.WEB_URL+dataBean.getCertificateImgUrl())
                            .error(R.drawable.head)
                            .into(ivHead);
                    tvDes.setText("个人简介："+dataBean.getBaseInfo());
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
        ivHead = (ImageView) rootview.findViewById(R.id.iv_head);
        tvBeijin = (TextView) rootview.findViewById(R.id.tv_beijin);
        tvWorktime = (TextView) rootview.findViewById(R.id.tv_worktime);
        tvNo = (TextView) rootview.findViewById(R.id.tv_no);
        tvDes = (TextView) rootview.findViewById(R.id.tv_des);
    }
}