package com.example.lalala.mynews.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lalala on 2017/1/24.
 */

public class TvChannelData implements Serializable{
    public List<TvChannelItem> result;

    public String code;

    public String date;

    public static class TvChannelItem implements Serializable{

        /*
        频道名称
         */
        public String channelName;
        /*
        频道代码
         */
        public String rel;
        /*
        播放链接
         */
        public String url;

        /*
        分类Id
         */
        public String pId;
    }
}
