package com.example.compete6.home.lvshi.tab.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.compete6.R;
import com.example.compete6.adapter.PingjiaAdapter;
import com.example.compete6.bean.PingjiaBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;

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
 * Use the {@link PingjiaBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PingjiaBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyListView mylistviewPingjia;
    private Button btnZixun;

    public PingjiaBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PingjiaBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PingjiaBlankFragment newInstance(String param1, String param2) {
        PingjiaBlankFragment fragment = new PingjiaBlankFragment();
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
    private Integer id;
    private PingjiaAdapter pingjiaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_pingjia_blank, container, false);
        }
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView(rootview);
        initData();
        btnZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"不免费了",Toast.LENGTH_SHORT);
            }
        });
        return rootview;
    }

    private void initData() {
        id=Integer.parseInt(mParam2);
        Call<ResponseBody> call=httpbinServices.getPingjia(mParam1,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<PingjiaBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getPingjiaList(res);
                    pingjiaAdapter.setRowsBeanList(rowsBeanList);
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
        mylistviewPingjia = (MyListView) rootview.findViewById(R.id.mylistview_pingjia);
        btnZixun = (Button) rootview.findViewById(R.id.btn_zixun);
        pingjiaAdapter=new PingjiaAdapter(getContext());
        mylistviewPingjia.setAdapter(pingjiaAdapter);
    }
}