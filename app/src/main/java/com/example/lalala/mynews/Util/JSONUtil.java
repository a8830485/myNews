package com.example.lalala.mynews.Util;

import android.text.TextUtils;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.example.lalala.mynews.data.NbaBaseData;
import com.example.lalala.mynews.data.TeamsRank;
import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvShowData;
import com.example.lalala.mynews.data.TvTypeData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lalala on 2017/1/22.
 */

public class JSONUtil {


    public static TeamsRank parseTeamsRank(String jsonStr) {
        TeamsRank rank = new TeamsRank();
        String dataStr = parseBase(rank, jsonStr);
        rank.east = new ArrayList<>();
        rank.west = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(dataStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                org.json.JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
                String title = item.getString("title");
                JSONArray teamsArray = item.optJSONArray("rows");
                for (int j = 0; j < teamsArray.length(); j++) {
                    JSONArray teamsInfo = teamsArray.getJSONArray(j);
                    Gson gson = new Gson();
                    TeamsRank.TeamBean bean = gson.fromJson(teamsInfo.getString(0), TeamsRank.TeamBean.class);
                    bean.win = teamsInfo.optString(1);
                    bean.lose = teamsInfo.optString(2);
                    bean.rate = teamsInfo.optString(3);
                    bean.difference = teamsInfo.optString(4);
                    if (title.equals("东部联盟")) {
                        rank.east.add(bean);
                    } else {
                        rank.west.add(bean);
                    }
                }
            }
            return rank;
        } catch (Exception e) {
            Log.e("dasdasd", e.toString());
        }
        return null;
    }

    public static String parseBase(NbaBaseData base, String jsonStr) {
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        String data = "{}";
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            if (entry.getKey().equals("code")) {
                base.code = entry.getValue().toString();
            } else if (entry.getKey().equals("version")) {
                base.version = entry.getValue().toString();
            } else {
                data = entry.getValue().toString();
            }
        }
        return data;
    }
}
