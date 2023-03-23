package com.example.compete6.per;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.UserInforBean;
import com.example.compete6.per.tab.PerFeedActivity;
import com.example.compete6.per.tab.PerInforActivity;
import com.example.compete6.per.tab.PerOrderActivity;
import com.example.compete6.per.tab.PerPwdActivity;
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
 * Use the {@link PerBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerBlankFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvPerInfor;
    private TextView tvPerOrder;
    private TextView tvPerPwd;
    private TextView tvPerFeed;

    public PerBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerBlankFragment newInstance(String param1, String param2) {
        PerBlankFragment fragment = new PerBlankFragment();
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
    private UserInforBean.UserBean userBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_per_blank, container, false);
        }
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView(rootview);
        initData();
        return rootview;
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getUserInfor(mParam1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    userBean= JsonParse.getmInstance().getUserInfor(res);
                    Glide.with(getContext())
                            .load(Contants.WEB_URL+userBean.getAvatar())
                            .error(R.drawable.head)
                            .into(ivHead);
                    tvName.setText(userBean.getUserName());
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
        tvName = (TextView) rootview.findViewById(R.id.tv_name);
        tvPerInfor = (TextView) rootview.findViewById(R.id.tv_per_infor);
        tvPerOrder = (TextView) rootview.findViewById(R.id.tv_per_order);
        tvPerPwd = (TextView) rootview.findViewById(R.id.tv_per_pwd);
        tvPerFeed = (TextView) rootview.findViewById(R.id.tv_per_feed);

        tvPerInfor.setOnClickListener(this);
        tvPerOrder.setOnClickListener(this);
        tvPerPwd.setOnClickListener(this);
        tvPerFeed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.tv_per_infor:
                intent=new Intent(getContext(), PerInforActivity.class);
                intent.putExtra("token",mParam1);
                intent.putExtra("user",userBean);
                break;
            case R.id.tv_per_order:
                intent=new Intent(getContext(), PerOrderActivity.class);
                intent.putExtra("token",mParam1);
                break;
            case R.id.tv_per_pwd:
                intent=new Intent(getContext(), PerPwdActivity.class);
                intent.putExtra("token",mParam1);
                break;
            case R.id.tv_per_feed:
                intent=new Intent(getContext(), PerFeedActivity.class);
                intent.putExtra("token",mParam1);
                break;
        }
        startActivity(intent);
    }
}