package com.example.lalala.mynews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.data.TeamsRank;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/2/8.
 */

public class NbaDataAdapter extends ArrayAdapter<TeamsRank.TeamBean> {

    private int resourceId;

    public NbaDataAdapter(Context context, int resourceId, List<TeamsRank.TeamBean> list) {
        super(context, resourceId, list);
        this.resourceId = resourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        TeamsRank.TeamBean item = getItem(position);
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.nbaDataTitleLayout.setVisibility(View.GONE);
        if(position == 0 ) {
            holder.nbaDataTitleLayout.setVisibility(View.VISIBLE);
            holder.nbaDataTitleUnion.setText("东部");
        }
        if(position == 15){
            holder.nbaDataTitleLayout.setVisibility(View.VISIBLE);
            holder.nbaDataTitleUnion.setText("西部");
        }
        holder.nbaDataItemBadge.setController(BaseUtil.getController(item.badge, holder.nbaDataItemBadge));
        holder.nbaDataItemDiff.setText(item.difference);
        holder.nbaDataItemLose.setText(item.lose);
        holder.nbaDataItemName.setText(item.name);
        holder.nbaDataItemPercent.setText(item.rate);
        holder.nbaDataItemWin.setText(item.win);
        return convertView;
    }

    class Holder {
        @BindView(R.id.nba_data_item_badge)
        SimpleDraweeView nbaDataItemBadge;
        @BindView(R.id.nba_data_item_name)
        TextView nbaDataItemName;
        @BindView(R.id.nba_data_item_win)
        TextView nbaDataItemWin;
        @BindView(R.id.nba_data_item_lose)
        TextView nbaDataItemLose;
        @BindView(R.id.nba_data_item_percent)
        TextView nbaDataItemPercent;
        @BindView(R.id.nba_data_item_diff)
        TextView nbaDataItemDiff;
        @BindView(R.id.nba_data_title_union)
        TextView nbaDataTitleUnion;
        @BindView(R.id.nba_data_title_layout)
        LinearLayout nbaDataTitleLayout;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
