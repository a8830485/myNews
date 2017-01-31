package com.example.lalala.mynews.http;

import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvShowData;
import com.example.lalala.mynews.data.TvTypeData;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lalala on 2017/1/24.
 */

public interface TvApi {

    /*
        获取电视节目表
     */
    @POST("getProgram")
    Call<TvShowData> getProgram(@Query("code") String code, @Query("date") String date, @Query("key") String key);

    /*
       获取频道类型列表
     */
    @POST("getCategory")
    Call<TvTypeData> getCategory(@Query("key") String key);

    /*

     */
    @POST("getChannel")
    Call<TvChannelData> getChannel(@Query("pId") String pId, @Query("key") String key);
}
