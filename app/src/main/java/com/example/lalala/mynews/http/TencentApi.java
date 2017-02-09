package com.example.lalala.mynews.http;

import com.example.lalala.mynews.data.NbaMatchData;
import com.example.lalala.mynews.data.NbaNewItemsData;
import com.example.lalala.mynews.data.NbaNewsDetailData;
import com.example.lalala.mynews.data.NbaNewsIndexesData;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lalala on 2017/1/25.
 */

public interface TencentApi {

    @GET("match/list")
    Call<NbaMatchData> getMatches();

    @GET("news/index")
    Call<NbaNewsIndexesData> getNewIndexes(@Query("column") String column);

    @GET("news/item")
    Call<NbaNewItemsData> getNewItems(@Query("column") String column, @Query("articleIds") String articleIds);

    @GET("news/detail")
    Call<NbaNewsDetailData> getNewsDetail(@Query("column") String column, @Query("articleId") String articleId);

    @GET("team/rank")
    Call<String> getTeamRank();
}
