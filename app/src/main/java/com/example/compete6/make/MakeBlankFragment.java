package com.example.compete6.make;

import android.content.Context;
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
import com.example.compete6.make.tab.JiankangActivity;
import com.example.compete6.make.tab.JizhongActivity;
import com.example.compete6.make.tab.PingtaiActivity;
import com.example.compete6.make.tab.XunjianActivity;
import com.example.compete6.make.tab.YuyueylActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MakeBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeBlankFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Banner bannerMake;
    private TextView tvJiangkang;
    private TextView tvYuyueyl;
    private TextView tvPingtai;
    private TextView tvXunjian;
    private TextView tvJizhong;

    public MakeBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MakeBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeBlankFragment newInstance(String param1, String param2) {
        MakeBlankFragment fragment = new MakeBlankFragment();
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
    private static List<Integer> slist = new ArrayList<>();

    static {
        slist.add(R.drawable.m1);
        slist.add(R.drawable.m2);
        slist.add(R.drawable.m3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_make_blank, container, false);
        }
        initView(rootview);
        initBanner();
        return rootview;
    }

    private void initBanner() {
        bannerMake.setImages(slist);
        bannerMake.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context)
                        .load(o)
                        .into(imageView);
            }
        });
        bannerMake.start();
    }

    private void initView(View rootview) {
        bannerMake = (Banner) rootview.findViewById(R.id.banner_make);
        tvJiangkang = (TextView) rootview.findViewById(R.id.tv_jiangkang);
        tvYuyueyl = (TextView) rootview.findViewById(R.id.tv_yuyueyl);
        tvPingtai = (TextView) rootview.findViewById(R.id.tv_pingtai);
        tvXunjian = (TextView) rootview.findViewById(R.id.tv_xunjian);
        tvJizhong = (TextView) rootview.findViewById(R.id.tv_jizhong);

        tvJiangkang.setOnClickListener(this);
        tvYuyueyl.setOnClickListener(this);
        tvPingtai.setOnClickListener(this);
        tvXunjian.setOnClickListener(this);
        tvJizhong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.tv_jiangkang:
                intent=new Intent(getContext(), JiankangActivity.class);
                break;
            case R.id.tv_yuyueyl:
                intent=new Intent(getContext(), YuyueylActivity.class);
                break;
            case R.id.tv_pingtai:
                intent=new Intent(getContext(), PingtaiActivity.class);
                break;
            case R.id.tv_xunjian:
                intent=new Intent(getContext(), XunjianActivity.class);
                break;
            case R.id.tv_jizhong:
                intent=new Intent(getContext(), JizhongActivity.class);
                break;
        }
        startActivity(intent);
    }
}