package com.example.lalala.mynews.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lalala on 2017/2/5.
 */

public class NbaNewItemsData extends NbaBaseData {
    public Map<String, NbaNewsItemData> data;

    public static class NbaNewsItemData{
        public String  index;

        public String attype;

        public String title;
        @SerializedName("abstract")
        public String abstractA;

        public String imgurl;

        public String imgurl2;

        public String newsId;

        public String url;

        public String commentId;

        public String pub_time;

        public String column;

        public String vid;

        public String duration;

        public String img_count;

        public List<String> images_3;

        public String footer;

        public String commentNum;

        public String upNum;

        public String pub_time_new;

        public String isCollect;
    }
}
