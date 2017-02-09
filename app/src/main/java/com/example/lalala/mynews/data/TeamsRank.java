package com.example.lalala.mynews.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lalala on 2017/2/7.
 */

public class TeamsRank extends NbaBaseData {

    public List<TeamBean> all;

    public List<TeamBean> east;
    public List<TeamBean> west;

    public static class TeamBean implements Serializable {
        public String teamId;
        public String name;
        public String badge;
        public String serial;
        public String color;
        public String detailUrl;

        public String win;
        public String lose;
        public String rate;
        public String difference; //胜场差

        public int type = 0; // 0：具体排名数据   1：东部    2：西部
    }
}
