package com.example.lalala.mynews.data;


import java.io.Serializable;
import java.util.List;

/**
 * Created by lalala on 2017/1/24.
 */

public class TvTypeData implements Serializable {
    public List<TvTypeItem> result;

    public String code;

    public String date;

    public static class TvTypeItem implements Serializable{
        /*
        频道分类
         */
        public String name;
        /*
        分类Id
         */
        public String id;

    }

}
