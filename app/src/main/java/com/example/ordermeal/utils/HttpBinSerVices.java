package com.example.ordermeal.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface HttpBinSerVices {
    @GET
    Call<ResponseBody> getSHopList(@Url String uri);
}
