package com.example.compete6.home.worktab.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.compete6.R;
import com.example.compete6.bean.AddJianBean;
import com.example.compete6.bean.SuccessBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etTopxue;
    private EditText etJiaoyu;
    private EditText etAddress;
    private EditText etWork;
    private EditText etJianjie;
    private EditText etMoney;
    private EditText etPos;
    private EditText etWenjian;
    private Button btnSubmit;

    public AddBlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBlankFragment newInstance(String param1, String param2) {
        AddBlankFragment fragment = new AddBlankFragment();
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
    private AddJianBean addJianBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_add_blank, container, false);
        }
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView(rootview);
        initData();
        return rootview;
    }

    private void initData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topxue=etTopxue.getText().toString();
                String jiaoyu=etJiaoyu.getText().toString();
                String address=etAddress.getText().toString();
                String gongzuo=etWork.getText().toString();
                String jianjie=etJianjie.getText().toString();
                String salary=etMoney.getText().toString();
                Integer zhiwei=Integer.parseInt(etPos.getText().toString());
                String wen=etWenjian.getText().toString();
                addJianBean=new AddJianBean();
                addJianBean.setMostEducation(topxue);
                addJianBean.setEducation(jiaoyu);
                addJianBean.setAddress(address);
                addJianBean.setExperience(gongzuo);
                addJianBean.setIndividualResume(jianjie);
                addJianBean.setMoney(salary);
                addJianBean.setPositionId(zhiwei);
                addJianBean.setFiles(wen);
                Call<SuccessBean> call=httpbinServices.postjob(mParam1,addJianBean);
                call.enqueue(new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        Toast.makeText(getContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable throwable) {

                    }
                });
            }
        });
    }

    private void initView(View rootview) {
        etTopxue = (EditText) rootview.findViewById(R.id.et_topxue);
        etJiaoyu = (EditText) rootview.findViewById(R.id.et_jiaoyu);
        etAddress = (EditText) rootview.findViewById(R.id.et_address);
        etWork = (EditText) rootview.findViewById(R.id.et_work);
        etJianjie = (EditText) rootview.findViewById(R.id.et_jianjie);
        etMoney = (EditText) rootview.findViewById(R.id.et_money);
        etPos = (EditText) rootview.findViewById(R.id.et_pos);
        etWenjian = (EditText) rootview.findViewById(R.id.et_wenjian);
        btnSubmit = (Button) rootview.findViewById(R.id.btn_submit);
    }
}