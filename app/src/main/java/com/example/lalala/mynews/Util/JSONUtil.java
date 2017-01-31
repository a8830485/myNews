package com.example.lalala.mynews.Util;

import android.text.TextUtils;


import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvShowData;
import com.example.lalala.mynews.data.TvTypeData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lalala on 2017/1/22.
 */

public class JSONUtil {

    /*
    解析频道类型Json
     */
    public static ArrayList<TvTypeData.TvTypeItem> handleTvType(String response){
        ArrayList<TvTypeData.TvTypeItem> list = new ArrayList<>();
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject obj = new JSONObject(response);
                response = obj.getString("result");
                JSONArray array =  new JSONArray(response);
                for(int i = 0; i  < array.length(); i++){
                    TvTypeData.TvTypeItem item = new TvTypeData.TvTypeItem();
                    JSONObject object = array.getJSONObject(i);
                    item.id = object.getString("id");
                    item.name = object.getString("name");
                    list.add(item);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }
    /*
    解析频道信息Json
     */
    public static ArrayList<TvChannelData.TvChannelItem> handleTvChannel(String response){
        ArrayList<TvChannelData.TvChannelItem> list = new ArrayList<>();
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject obj = new JSONObject(response);
                response = obj.getString("result");
                JSONArray array =  new JSONArray(response);
                for(int i = 0; i  < array.length(); i++){
                    TvChannelData.TvChannelItem item = new TvChannelData.TvChannelItem();
                    JSONObject object = array.getJSONObject(i);
                    item.channelName = object.getString("channelName");
                    item.pId = object.getString("pId");
                    item.rel = object.getString("rel");
                    item.url = object.getString("url");
                    list.add(item);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }
    /*
    解析节目信息Json
     */
    public static  ArrayList<TvShowData.TvShowItem> handleTvShow(String response){
        ArrayList<TvShowData.TvShowItem> list = new ArrayList<>();
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject obj = new JSONObject(response);
                response = obj.getString("result");
                JSONArray array =  new JSONArray(response);
                for(int i = 0; i  < array.length(); i++){
                    TvShowData.TvShowItem item = new TvShowData.TvShowItem();
                    JSONObject object = array.getJSONObject(i);
                    item.cName = object.getString("cName");
                    item.pName = object.getString("pName");
                    item.pUrl = object.getString("pUrl");
                    item.time = object.getString("time");
                    list.add(item);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
