package com.example.lalala.mynews.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lalala on 2017/1/25.
 */

public class NbaMatchData implements Serializable {

    public String code;

    public NbaMatchesData data;

    public String version;

    public static class NbaMatchesData implements Serializable{
        public List<String> dates;

        //public String latestMatchDate;

        public Map<String,Matches> matches;

       // public List<Matches> matches;

       // public List<String> updateDates;

        //public String updateFrequency;
    }

    public static class Matches implements Serializable{
        public List<Match> list;

        public String version;

    }

    public static class Match implements Serializable{
        //public String ad;

        //public String isDisabled;

        //public String isPay;

        //public String liveId;

        public MatchInfo matchInfo;

        public String updateFrequency;

        public String date = "";

        //public String userNum;
    }

    public static class MatchInfo implements Serializable{

        public List<String> broadcasters;

        public String isBook;

        public String leftBadge;

        public String leftDetailUrl;

        public String leftGoal;

        public String leftHasUrl;

        public String leftId;

        public String leftName;

        public String liveType;

        public String logo;

        public String matchDesc;

        public String matchPeriod;

        public String matchType;

        public String mid;

        public String quarter;

        public String quarterTime;

        public String rightBadge;

        public String rightDetailUrl;

        public String rightGoal;

        public String rightHasUrl;

        public String rightId;

        public String rightName;

        public String startTime;

        public List<Tabs> tabs;

        public String title;
    }

    public static class Tabs{
        public String desc;

        public String type;
    }
}
