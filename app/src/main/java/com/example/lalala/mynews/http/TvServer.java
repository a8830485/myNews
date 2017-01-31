package com.example.lalala.mynews.http;

import android.os.Message;
import android.util.Log;

import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.Util.HttpUtil;
import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvShowData;
import com.example.lalala.mynews.data.TvTypeData;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lalala on 2017/1/24.
 */

public class TvServer {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(HttpUtil.TV_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();

    public static TvApi tvApi = retrofit.create(TvApi.class);

    /*
    查询电视节目
     */
    public static void queryTvShow(String rel, String date, Callback<TvShowData> callback){
        Call<TvShowData> call = tvApi.getProgram(rel, date, BaseUtil.key);
        call.enqueue(callback);
    }

    /*
    查询频道类型
     */
    public static void queryTvType(Callback<TvTypeData> callback){
        Call<TvTypeData> call = tvApi.getCategory(BaseUtil.key);
        call.enqueue(callback);
    }

    /*
    查询频道列表
     */
    public static void quertTvChannel(String typeId, Callback<TvChannelData> callback){
        Call<TvChannelData> call = tvApi.getChannel(typeId, BaseUtil.key);
        call.enqueue(callback);
    }
}
