package com.example.compete6.util;

import android.content.Context;
import android.util.Log;

import com.example.compete6.adapter.AnliAdapter;
import com.example.compete6.bean.AixintuijianBean;
import com.example.compete6.bean.Anlibean;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.BusBean;
import com.example.compete6.bean.CheckBean;
import com.example.compete6.bean.ChongzhiBean;
import com.example.compete6.bean.FenleiBean;
import com.example.compete6.bean.FindDtBean;
import com.example.compete6.bean.FlzcBean;
import com.example.compete6.bean.GongyiListBean;
import com.example.compete6.bean.JobDetaBean;
import com.example.compete6.bean.LajiNewsDeBean;
import com.example.compete6.bean.LajiXwListBean;
import com.example.compete6.bean.LajifenBean;
import com.example.compete6.bean.LibraryBean;
import com.example.compete6.bean.LvshiDetailBean;
import com.example.compete6.bean.LvshiTopListBean;
import com.example.compete6.bean.LvshiZcListBean;
import com.example.compete6.bean.MetroDeBean;
import com.example.compete6.bean.MetroHomeListBean;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.bean.NeirongDeBean;
import com.example.compete6.bean.NeirongListBean;
import com.example.compete6.bean.NewListBean;
import com.example.compete6.bean.NewsDetailBean;
import com.example.compete6.bean.PingjiaBean;
import com.example.compete6.bean.PinglunBean;
import com.example.compete6.bean.RencaiBean;
import com.example.compete6.bean.RencaiDeBean;
import com.example.compete6.bean.ResultBean;
import com.example.compete6.bean.SearchHotBean;
import com.example.compete6.bean.ShuziListBean;
import com.example.compete6.bean.StationBean;
import com.example.compete6.bean.SuqiuDeBean;
import com.example.compete6.bean.SuqiuListBean;
import com.example.compete6.bean.ToudiListBean;
import com.example.compete6.bean.UserInforBean;
import com.example.compete6.bean.WorkZhiweiBean;
import com.example.compete6.bean.WuliuDetailBean;
import com.example.compete6.bean.WuliucomBean;
import com.example.compete6.bean.YizhanBean;
import com.example.compete6.bean.YizhanDeBean;
import com.example.compete6.bean.ZfBannerBean;
import com.example.compete6.bean.ZfFenleiBena;
import com.example.compete6.bean.ZhaopinBean;
import com.example.compete6.bean.ZhengfuFenBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.bean.ZhiyuanNewListBean;
import com.example.compete6.bean.ZixunDeBean;
import com.example.compete6.bean.ZixunListBean;
import com.example.compete6.bean.ZyPartyListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParse {
    public static JsonParse mInstance=null;
    private Context context;


    public JsonParse() {

    }

    public static JsonParse getmInstance() {
        if(mInstance==null){
            mInstance=new JsonParse();
        }
        return mInstance;
    }

    public NewsDetailBean.DataBean getNewsDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<NewsDetailBean.DataBean>(){}.getType();
        NewsDetailBean.DataBean dataBean=new NewsDetailBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            dataBean=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }
    public List<BannerBean.RowsBean> getBannerList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<BannerBean.RowsBean>>(){}.getType();
        List<BannerBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<NewListBean.RowsBean> getNewsList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<NewListBean.RowsBean>>(){}.getType();
        List<NewListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<BusBean.RowsBean> getBusList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<BusBean.RowsBean>>(){}.getType();
        List<BusBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<MovieListBean.RowsBean> getMovieList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<MovieListBean.RowsBean>>(){}.getType();
        List<MovieListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public UserInforBean.UserBean getUserInfor(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<UserInforBean.UserBean>(){}.getType();
        UserInforBean.UserBean rowsBeanList=new UserInforBean.UserBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("user");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZhiyuanBannerBean.DataBean> getZyBannerList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZhiyuanBannerBean.DataBean>>(){}.getType();
        List<ZhiyuanBannerBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZhiyuanNewListBean.RowsBean> getZyNewList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZhiyuanNewListBean.RowsBean>>(){}.getType();
        List<ZhiyuanNewListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZyPartyListBean.RowsBean> getZypartyList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZyPartyListBean.RowsBean>>(){}.getType();
        List<ZyPartyListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<Anlibean.RowsBean> getAlwentiList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<Anlibean.RowsBean>>(){}.getType();
        Type type1=new TypeToken<Anlibean.RowsBean.DoctorBean>(){}.getType();
        List<Anlibean.RowsBean> rowsBeanList=new ArrayList<>();
        Anlibean.RowsBean.DoctorBean doctorBean=new Anlibean.RowsBean.DoctorBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");

//            JSONObject jsonObject1=jsonArray.getJSONObject(0);
//            JSONObject jsonObject2=jsonObject1.getJSONObject("doctor");
//            doctorBean=gson.fromJson(String.valueOf(jsonObject2),type1);
//
//            Log.d("doctor111", String.valueOf(jsonArray));
//            Log.d("doctor222", String.valueOf(doctorBean.getName()));

            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public Anlibean.RowsBean.DoctorBean getAlys(String json,int position){
        Gson gson=new Gson();
        Type type=new TypeToken<Anlibean.RowsBean.DoctorBean>(){}.getType();
        Anlibean.RowsBean.DoctorBean doctorBean=new Anlibean.RowsBean.DoctorBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            JSONObject jsonObject1=jsonArray.getJSONObject(position);
            JSONObject jsonObject2=jsonObject1.getJSONObject("doctor");
            doctorBean=gson.fromJson(String.valueOf(jsonObject2),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctorBean;
    }

    public List<FindDtBean.RowsBean> getDoctorList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<FindDtBean.RowsBean>>(){}.getType();
        List<FindDtBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public List<WuliucomBean.DataBean> getWuliuComList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<WuliucomBean.DataBean>>(){}.getType();
        List<WuliucomBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public WuliuDetailBean.DataBean getWuliu(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<WuliuDetailBean.DataBean>(){}.getType();
        WuliuDetailBean.DataBean dataBean=new WuliuDetailBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            dataBean=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }

    public NewsDetailBean.DataBean getNewDetail(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<NewsDetailBean.DataBean>(){}.getType();
        NewsDetailBean.DataBean rowsBeanList=new NewsDetailBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public List<AixintuijianBean.RowsBean> getAIxintuijianList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<AixintuijianBean.RowsBean>>(){}.getType();
        List<AixintuijianBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<GongyiListBean.RowsBean> getGongyiList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<GongyiListBean.RowsBean>>(){}.getType();
        List<GongyiListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public GongyiListBean.RowsBean.TypeBean getGongyiLeibie(String json,int position){
        Gson gson=new Gson();
        Type type=new TypeToken<GongyiListBean.RowsBean.TypeBean>(){}.getType();
        GongyiListBean.RowsBean.TypeBean rowsBeanList=new GongyiListBean.RowsBean.TypeBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            JSONObject jsonObject1=jsonArray.getJSONObject(position);
            JSONObject jsonObject2=jsonObject1.getJSONObject("type");
            rowsBeanList=gson.fromJson(String.valueOf(jsonObject2),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public List<FlzcBean.RowsBean> getZcList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<FlzcBean.RowsBean>>(){}.getType();
        List<FlzcBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<LvshiTopListBean.DataBean> getTopList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<LvshiTopListBean.DataBean>>(){}.getType();
        List<LvshiTopListBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<LvshiZcListBean.RowsBean> getLvshiZcList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<LvshiZcListBean.RowsBean>>(){}.getType();
        List<LvshiZcListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public LvshiDetailBean.DataBean getLvshiDetail(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<LvshiDetailBean.DataBean>(){}.getType();
        LvshiDetailBean.DataBean rowsBeanList=new LvshiDetailBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<PingjiaBean.RowsBean> getPingjiaList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<PingjiaBean.RowsBean>>(){}.getType();
        List<PingjiaBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZixunListBean.RowsBean> getZixunList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZixunListBean.RowsBean>>(){}.getType();
        List<ZixunListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public ZixunDeBean.DataBean getZIxunde(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<ZixunDeBean.DataBean>(){}.getType();
        ZixunDeBean.DataBean rowsBeanList=new ZixunDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<MetroHomeListBean.DataBean> getMetroList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<MetroHomeListBean.DataBean>>(){}.getType();
        List<MetroHomeListBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public MetroDeBean.DataBean getMetroDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<MetroDeBean.DataBean>(){}.getType();
        MetroDeBean.DataBean rowsBeanList=new MetroDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public StationBean.DataBean getStation(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<StationBean.DataBean>(){}.getType();
        StationBean.DataBean rowsBeanList=new StationBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public List<ChongzhiBean.RowsBean> getHistoryPhone(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ChongzhiBean.RowsBean>>(){}.getType();
        List<ChongzhiBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<WorkZhiweiBean.RowsBean> getZhiweiname(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<WorkZhiweiBean.RowsBean>>(){}.getType();
        List<WorkZhiweiBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZhaopinBean.RowsBean> getZhaopinList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZhaopinBean.RowsBean>>(){}.getType();
        List<ZhaopinBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public JobDetaBean.DataBean getJob(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<JobDetaBean.DataBean>(){}.getType();
        JobDetaBean.DataBean rowsBeanList=new JobDetaBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ToudiListBean.RowsBean> getToudiList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ToudiListBean.RowsBean>>(){}.getType();
        List<ToudiListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public CheckBean.DataBean getjianli(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<CheckBean.DataBean>(){}.getType();
        CheckBean.DataBean rowsBeanList=new CheckBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ShuziListBean.RowsBean> getShuziList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ShuziListBean.RowsBean>>(){}.getType();
        List<ShuziListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public LibraryBean.DataBean getLibraryDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<LibraryBean.DataBean>(){}.getType();
        LibraryBean.DataBean rowsBeanList=new LibraryBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public List<PinglunBean.DataBean> getPinglun(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<PinglunBean.DataBean>>(){}.getType();
        List<PinglunBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<RencaiBean.DataBean> getRencai(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<RencaiBean.DataBean>>(){}.getType();
        List<RencaiBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<YizhanBean.RowsBean> getYizhan(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<YizhanBean.RowsBean>>(){}.getType();
        List<YizhanBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public YizhanDeBean.DataBean getYizhanDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<YizhanDeBean.DataBean>(){}.getType();
        YizhanDeBean.DataBean rowsBeanList=new YizhanDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public RencaiDeBean.DataBean getRencaiDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<RencaiDeBean.DataBean>(){}.getType();
        RencaiDeBean.DataBean rowsBeanList=new RencaiDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<NeirongListBean.DataBean> getNeirong(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<NeirongListBean.DataBean>>(){}.getType();
        List<NeirongListBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public NeirongDeBean.DataBean getNeirongde(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<NeirongDeBean.DataBean>(){}.getType();
        NeirongDeBean.DataBean rowsBeanList=new NeirongDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZfBannerBean.RowsBean> getZfBanner(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZfBannerBean.RowsBean>>(){}.getType();
        List<ZfBannerBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ZfFenleiBena.RowsBean> getZfFenList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ZfFenleiBena.RowsBean>>(){}.getType();
        List<ZfFenleiBena.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<SuqiuListBean.RowsBean> getSuqiuList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<SuqiuListBean.RowsBean>>(){}.getType();
        List<SuqiuListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }

    public SuqiuDeBean.DataBean getSuqiude(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<SuqiuDeBean.DataBean>(){}.getType();
        SuqiuDeBean.DataBean rowsBeanList=new SuqiuDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<LajiXwListBean.RowsBean> getLajiXwList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<LajiXwListBean.RowsBean>>(){}.getType();
        List<LajiXwListBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<LajifenBean.RowsBean> getLajifenList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<LajifenBean.RowsBean>>(){}.getType();
        List<LajifenBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public LajiNewsDeBean.DataBean getLajiDe(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<LajiNewsDeBean.DataBean>(){}.getType();
        LajiNewsDeBean.DataBean rowsBeanList=new LajiNewsDeBean.DataBean();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject jsonArray=jsonObject.getJSONObject("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<SearchHotBean.DataBean> getSearchHot(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<SearchHotBean.DataBean>>(){}.getType();
        List<SearchHotBean.DataBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<ResultBean.RowsBean> getResult(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<ResultBean.RowsBean>>(){}.getType();
        List<ResultBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
    public List<FenleiBean.RowsBean> getFenleiList(String json){
        Gson gson=new Gson();
        Type type=new TypeToken<List<FenleiBean.RowsBean>>(){}.getType();
        List<FenleiBean.RowsBean> rowsBeanList=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray=jsonObject.getJSONArray("rows");
            rowsBeanList=gson.fromJson(String.valueOf(jsonArray),type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rowsBeanList;
    }
}
