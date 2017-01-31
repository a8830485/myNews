package com.example.lalala.mynews.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lalala on 2017/1/22.
 */

public class TvShowData implements Serializable {

    public List<TvShowItem> result;

    public String code;

    public String date;

    public static class TvShowItem implements Serializable {
        /*
        播出时间
        */
        public String time;
        /*
        播放链接
        */
        public String pUrl;
        /*
        节目名称
        */
        public String pName;
        /*
        频道名称
        */
        public String cName;

    }

}
