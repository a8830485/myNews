package com.example.lalala.mynews.http;

import com.example.lalala.mynews.Util.HttpUtil;
import com.example.lalala.mynews.data.NbaMatchData;
import com.example.lalala.mynews.data.NbaNewItemsData;
import com.example.lalala.mynews.data.NbaNewsDetailData;
import com.example.lalala.mynews.data.NbaNewsIndexesData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lalala on 2017/1/25.
 */

public class TencentServer {
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HttpUtil.TENCENT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true) // 失败重发
                     .build())
            .build();

    public static TencentApi tencentApi = retrofit.create(TencentApi.class);

    public static void getMatches(Callback<NbaMatchData> callback){
        Call<NbaMatchData> call = tencentApi.getMatches();
        call.enqueue(callback);
    }

    public static void getIndexes(Callback<NbaNewsIndexesData> callback, String column){
        tencentApi.getNewIndexes(column).enqueue(callback);
    }

    public static void getItems(Callback<NbaNewItemsData> callback, String column, String articleIds){
        tencentApi.getNewItems(column, articleIds).enqueue(callback);
    }

    public static void getDetail(Callback<NbaNewsDetailData> callback, String column, String articleId){
        tencentApi.getNewsDetail(column, articleId).enqueue(callback);
    }
}
