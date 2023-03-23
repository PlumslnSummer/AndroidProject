package com.example.compete6.util;

import com.example.compete6.bean.AddJianBean;
import com.example.compete6.bean.AddTousuBean;
import com.example.compete6.bean.JiaofeiBean;
import com.example.compete6.bean.LoginBean;
import com.example.compete6.bean.PhoneJiaofeiBean;
import com.example.compete6.bean.PutUserInforBean;
import com.example.compete6.bean.PwdBean;
import com.example.compete6.bean.RegistSucBean;
import com.example.compete6.bean.RegisterBean;
import com.example.compete6.bean.SuccessBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpbinServices {

    @GET("/prod-api/api/rotation/list")
    Call<ResponseBody> getHomeBanner();

    @GET("/prod-api/press/press/list")
    Call<ResponseBody> getNewList(@Query("type")Integer id);

    @GET("/prod-api/api/bus/line/list")
    Call<ResponseBody> getBusList();

    @POST("/prod-api/api/login")
    Call<SuccessBean> postLogin(@Body LoginBean loginBean);

    @GET("/prod-api/api/movie/rotation/list")
    Call<ResponseBody> getMovieBanner();

    @GET("/prod-api/api/movie/film/list")
    Call<ResponseBody> getMovieList();

    @GET("/prod-api/api/common/user/getInfo")
    Call<ResponseBody> getUserInfor(@Header("Authorization") String token);

    @GET("/prod-api/api/volunteer-service/ad-banner/list")
    Call<ResponseBody> getZyBanner(@Header("Authorization") String token);

    @GET("/prod-api/api/volunteer-service/news/list")
    Call<ResponseBody> getZyNewList(@Header("Authorization") String token);

    @GET("/prod-api/api/volunteer-service/activity/list")
    Call<ResponseBody> getZyPartyList(@Header("Authorization") String token);

    @PUT("/prod-api/api/common/user")
    Call<SuccessBean> putUserInfior(@Header("Authorization") String token, @Body PutUserInforBean putUserInforBean);

    @PUT("/prod-api/api/common/user/resetPwd")
    Call<SuccessBean> putUserPwd(@Header("Authorization") String token, @Body PwdBean pwdBean);

    @GET("/prod-api/api/pet-hospital/pet-doctor/list")
    Call<ResponseBody> getCwyishengList(@Header("Authorization") String token);

    @GET("/prod-api/api/pet-hospital/inquiry/list")
    Call<ResponseBody> getAnliList(@Header("Authorization") String token);


    @GET("/prod-api/api/logistics-inquiry/logistics_company/list")
    Call<ResponseBody> getWuliuComList(@Header("Authorization") String token);


    @GET("/prod-api/api/logistics-inquiry/ad-banner/list")
    Call<ResponseBody> getWUliuBannerList(@Header("Authorization") String token);


    @GET("/prod-api/api/logistics-inquiry/logistics_company/{id}")
    Call<ResponseBody> getWuliuDetail(@Header("Authorization") String token, @Path("id")Integer id);

    @POST("/prod-api/api/logistics-inquiry/logistics_complaint")
    Call<SuccessBean> postAddtousu(@Header("Authorization") String token, @Body  AddTousuBean addTousuBean);

    @GET("/prod-api/api/public-welfare/ad-banner/list")
    Call<ResponseBody> getAixinBanner(@Header("Authorization")String token);

    @GET("/prod-api/press/press/{id}")
    Call<ResponseBody> getHomeBannerNewsDetail(@Path("id")Integer id);
    @GET("/prod-api/api/public-welfare/public-welfare-type/list")
    Call<ResponseBody> getAixinItemList(@Header("Authorization")String token);

    @GET("/prod-api/api/public-welfare/public-welfare-activity/recommend-list")
    Call<ResponseBody> getAixintuijianList(@Header("Authorization")String token);

    @GET("/prod-api/api/public-welfare/public-welfare-activity/list")
    Call<ResponseBody> getGongyiList(@Header("Authorization")String token,@Query("typeId")Integer id);

    @GET("/prod-api/api/lawyer-consultation/ad-banner/list")
    Call<ResponseBody> getLvshiBanner(@Header("Authorization")String token);

    @GET("/prod-api/api/lawyer-consultation/legal-expertise/list")
    Call<ResponseBody> getLvshiZcList(@Header("Authorization")String token);

    @GET("/prod-api/api/lawyer-consultation/lawyer/list-top10")
    Call<ResponseBody> getHplTopList(@Header("Authorization")String token);

    @GET("/prod-api/api/lawyer-consultation/lawyer/list")
    Call<ResponseBody> getLvshizcList(@Header("Authorization")String token,@Query("legalExpertiseId")Integer id);

    @GET("/prod-api/api/lawyer-consultation/lawyer/list")
    Call<ResponseBody> getAllLvshiList(@Header("Authorization")String token);

    @GET("/prod-api/api/lawyer-consultation/lawyer/{id}")
    Call<ResponseBody> getLvshidetail(@Header("Authorization")String token,@Path("id")Integer id);

    @GET("/prod-api/api/lawyer-consultation/lawyer/list-evaluate")
    Call<ResponseBody> getPingjia(@Header("Authorization")String token,@Query("lawyerId")Integer id);

    @GET("/prod-api/api/lawyer-consultation/legal-advice/list")
    Call<ResponseBody> getZixunList(@Header("Authorization")String token);

    @GET("/prod-api/api/lawyer-consultation/legal-advice/{id}")
    Call<ResponseBody> getZIxunDe(@Header("Authorization")String token,@Path("id")Integer id);

    @GET("/prod-api/api/metro/list")
    Call<ResponseBody> getMetroHomeList(@Header("Authorization")String token,@Query("currentName")String name);

    @GET("/prod-api/api/metro/line/{id}")
    Call<ResponseBody> getMetroDe(@Header("Authorization")String token,@Path("id")Integer id);

    @GET("/prod-api/api/metro/city")
    Call<ResponseBody> getAllStstion(@Header("Authorization")String token);

    @POST("/prod-api/api/living/phone/recharge")
    Call<PhoneJiaofeiBean> postjiaofei(@Header("Authorization")String token,@Body JiaofeiBean jiaofeiBean);

    @GET("/prod-api/api/living/phone/record/list")
    Call<ResponseBody> getHistoryPhone(@Header("Authorization")String token);

    @GET("/prod-api/api/job/profession/list")
    Call<ResponseBody> getZhiweiList(@Header("Authorization")String token);

    @GET("/prod-api/api/job/post/list")
    Call<ResponseBody> getJobList(@Header("Authorization")String token);

    @GET("/prod-api/api/job/post/{id}")
    Call<ResponseBody> getJobDetail(@Header("Authorization")String token, @Path("id")Integer id);

    @GET("/prod-api/api/job/deliver/list")
    Call<ResponseBody> gettouList(@Header("Authorization")String token);

    @POST("/prod-api/api/job/resume")
    Call<SuccessBean> postjob(@Header("Authorization")String token, @Body AddJianBean addJianBean);

    @GET("/prod-api/api/job/resume/queryResumeByUserId/{userId}")
    Call<ResponseBody> getCheckJian(@Header("Authorization")String token,@Path("userId")Integer id);

    @GET(" /prod-api/api/digital-library/library/list")
    Call<ResponseBody> getShuziList(@Header("Authorization")String token);

    @GET("/prod-api/api/digital-library/library/{id}")
    Call<ResponseBody> getLibraryDe(@Header("Authorization")String token,@Path("id")Integer id);

    @GET("/prod-api/api/digital-library/library-comment/list")
    Call<ResponseBody> getPinglunList(@Header("Authorization")String token,@Query("libraryId") Integer libraryId);

    @GET(" /prod-api/api/youth-inn/talent-policy-area/list")
    Call<ResponseBody> getRencaiList(@Header("Authorization")String token);

    @GET(" /prod-api/api/youth-inn/youth-inn/list")
    Call<ResponseBody> getYIzhanList(@Header("Authorization")String token);

    @GET(" /prod-api/api/youth-inn/youth-inn/{id}")
    Call<ResponseBody> getYizhanDe(@Header("Authorization")String token,@Path("id")Integer id);

    @GET(" /prod-api/api/youth-inn/talent-policy-area/{id}")
    Call<ResponseBody> getRencaiDe(@Header("Authorization")String token,@Path("id")Integer id);

    @GET("/prod-api/api/youth-inn/talent-policy/list")
    Call<ResponseBody> getNeirList(@Header("Authorization")String token,@Query("areaId")Integer id);

    @GET(" /prod-api/api/youth-inn/talent-policy/{id}")
    Call<ResponseBody> getNeirongDe(@Header("Authorization")String token,@Path("id") Integer id);

    @GET("/prod-api/api/gov-service-hotline/appeal-category/list")
    Call<ResponseBody> getZfFenlei(@Header("Authorization")String token);

    @GET(" /prod-api/api/gov-service-hotline/ad-banner/list")
    Call<ResponseBody> getZfBanner(@Header("Authorization")String token);

    @GET(" /prod-api/api/gov-service-hotline/appeal/list")
    Call<ResponseBody> getSuqiuList(@Header("Authorization")String token,@Query("appealCategoryId")Integer id);

    @GET(" /prod-api/api/gov-service-hotline/appeal/{id}")
    Call<ResponseBody> getSuqiuDe(@Header("Authorization")String token,@Path("id") Integer id);

    @GET(" /prod-api/api/garbage-classification/ad-banner/list")
    Call<ResponseBody> getLajiBanner(@Header("Authorization")String token);

    @GET(" /prod-api/api/garbage-classification/news-type/list")
    Call<ResponseBody> getLajiFenlei(@Header("Authorization")String token);
    @GET(" /prod-api/api/garbage-classification/news/list")
    Call<ResponseBody> getLajiXinwenList(@Header("Authorization")String token,@Query("type")Integer id);

    @GET(" /prod-api/api/garbage-classification/news/{id}")
    Call<ResponseBody> getLsjiNewsDe(@Header("Authorization")String token,@Path("id") Integer id);

    @GET(" /prod-api/api/garbage-classification/poster/list")
    Call<ResponseBody> getSearchBanner(@Header("Authorization")String token);

    @GET("/prod-api/api/garbage-classification/garbage-classification/hot/list")
    Call<ResponseBody> getSearchHot(@Header("Authorization")String token);

    @GET(" /prod-api/api/garbage-classification/garbage-example/list")
    Call<ResponseBody> getResultList(@Header("Authorization")String token,@Query("type")Integer id);

    @GET(" /prod-api/api/garbage-classification/garbage-classification/list")
    Call<ResponseBody> getFenleiList(@Header("Authorization")String token);

    @GET(" /prod-api/api/garbage-classification/garbage-example/list")
    Call<ResponseBody> getChangjianList(@Header("Authorization")String token);

    @POST("/prod-api/api/register")
    Call<RegistSucBean>  PostRegister(@Body RegisterBean registerBean);

    @GET("/prod-api/press/press/{id}")
    Call<ResponseBody> GetNewListDetail(@Header("Authorization")String token, @Path("id")Integer id);
}
