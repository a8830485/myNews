package com.example.lalala.mynews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.data.NbaNewItemsData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/2/5.
 */

public class NbaNewsAdapter extends ArrayAdapter<NbaNewItemsData.NbaNewsItemData> {


    int resourceId;

    public NbaNewsAdapter(Context context, int resourceId, List<NbaNewItemsData.NbaNewsItemData> list) {
        super(context, resourceId, list);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NbaNewItemsData.NbaNewsItemData item = getItem(position);
        Holder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.nbaNewsThumbnail.setController(BaseUtil.getController(item.imgurl2, holder.nbaNewsThumbnail));
        holder.nbaNewsTitle.setText(item.title);
        holder.nbaNewsPubtime.setText(item.pub_time);
        return convertView;
    }

    class Holder {

        @BindView(R.id.nba_news_thumbnail)
        SimpleDraweeView nbaNewsThumbnail;
        @BindView(R.id.nba_news_title)
        TextView nbaNewsTitle;
        @BindView(R.id.nba_news_pubtime)
        TextView nbaNewsPubtime;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
