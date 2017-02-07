package com.example.lalala.mynews.data;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by lalala on 2017/2/6.
 */

public class NbaNewsDetailData extends NbaBaseData{

   public DetailData data;

    public static class DetailData{
        public String title;
        @SerializedName("abstract")
        public String abstractX;

        public List<Content> content;

        public String url;
        public String imgurl;
        public String imgurl1;
        public String imgurl2;
        public String pub_time_new;
        public String atype;
        public String commentId;
        public String newsAppId;
    }

    public static class Content{
        public String type;

        public ImgsData img;

        public String islong;
        public String isqrcode;
        public String itype;
        public String count;
        public String face;
        public String has180;

        public String info;
    }

    public static class ImgsData{
        public ImgData imgurl1000;
        public ImgData imgurl641;
        public ImgData imgurl640;
        public ImgData imgurl0;

    }

    public static class ImgData{
        public String height;
        public String imgurl;
        public String length;
        public String width;
    }
}
