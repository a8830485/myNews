package com.example.lalala.mynews.http;

import com.example.lalala.mynews.data.NbaMatchData;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lalala on 2017/1/25.
 */

public interface TencentApi {

    @GET("match/list")
    Call<NbaMatchData> getMatches();
            //osvid=22&appvid=4.5.0&appvcode=450&network=wifi&store=65&deviceId=862095022169061&guid=078D583C00E081E142ED046B5687E94E&omgid=d9df4e4d19423a416d08664f576de649fcec001021190d&omgbizid=cb3be0f43436b34490791fce7e46eafd68c40120212006
}
