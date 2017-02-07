package com.example.lalala.mynews.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lalala on 2017/2/5.
 */

public class NbaNewsIndexesData extends NbaBaseData {

    public List<NbaNewsIndexData> data;

     public static class NbaNewsIndexData {
        public String type;

        public String id;

        public String column;

        public String needUpdate;
    }
}
