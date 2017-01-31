package com.example.lalala.mynews.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.data.NbaMatchData;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/1/26.
 */

public class NbaMatchAdapter extends ArrayAdapter<NbaMatchData.Match> {

    int resouceId;

    ViewHolder holder;


    static Map<String, Drawable> drawableMap = new HashMap<>();

    public NbaMatchAdapter(Context context, int viewResourceId, List<NbaMatchData.Match> list) {
        super(context, viewResourceId, list);
        this.resouceId = viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NbaMatchData.MatchInfo item = getItem(position).matchInfo;
        String  status = "", broadcaster = "";
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resouceId, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if((item.quarter.contains("第4节") || item.quarter.contains("加时"))
                && item.quarterTime.contains("00:00") && !item.leftGoal.equals(item.rightGoal)){
            status = "已结束";
        }else if(item.quarter.equals("")){
            status = item.startTime;
        }else{
            status = item.quarter + " " + item.quarterTime;
        }
        holder.nbaStartTime.setText(status);


        for(int i = 0; i < item.broadcasters.size() - 1; i++){
            broadcaster+=item.broadcasters.get(i) + "|";
        }
        broadcaster += item.broadcasters.get(item.broadcasters.size() - 1);
        holder.nbaBroadcasters.setText(broadcaster);

        holder.nbaLeftName.setText(item.leftName);
        holder.nbaLeftGoal.setText(item.leftGoal);
        holder.nbaRightGoal.setText(item.rightGoal);
        holder.nbaRightName.setText(item.rightName);
        holder.nbaMatchDesc.setText(item.matchDesc);

        if (!getItem(position).date.equals("")) {
            holder.nbaMatchDate.setText(getItem(position).date);
            holder.nbaMatchDate.setVisibility(View.VISIBLE);
        } else
            holder.nbaMatchDate.setVisibility(View.GONE);

        holder.nbaRightBadge.setController(BaseUtil.getController(item.rightBadge, holder.nbaRightBadge));
        holder.nbaLeftBadge.setController(BaseUtil.getController(item.leftBadge, holder.nbaLeftBadge));
        return convertView;
    }


    static class ViewHolder {

        @BindView(R.id.nba_matchDate)
        TextView nbaMatchDate;
        @BindView(R.id.nba_startTime)
        TextView nbaStartTime;
        @BindView(R.id.nba_leftBadge)
        SimpleDraweeView nbaLeftBadge;
        @BindView(R.id.nba_leftName)
        TextView nbaLeftName;
        @BindView(R.id.nba_broadcasters)
        TextView nbaBroadcasters;
        @BindView(R.id.nba_leftGoal)
        TextView nbaLeftGoal;
        @BindView(R.id.nba_rightGoal)
        TextView nbaRightGoal;
        @BindView(R.id.nba_matchDesc)
        TextView nbaMatchDesc;
        @BindView(R.id.nba_rightBadge)
        SimpleDraweeView nbaRightBadge;
        @BindView(R.id.nba_rightName)
        TextView nbaRightName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            //R.layout.nba_game_item
        }
    }
}
