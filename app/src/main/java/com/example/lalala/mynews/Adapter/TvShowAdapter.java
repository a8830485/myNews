package com.example.lalala.mynews.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.DimenisionUtil;
import com.example.lalala.mynews.data.TvShowData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/1/22.
 */

public class TvShowAdapter extends ArrayAdapter<TvShowData.TvShowItem> {

    private int resouceId;

    public TvShowAdapter(Context context, int textViewResourceId, List<TvShowData.TvShowItem> list) {
        super(context, textViewResourceId, list);
        this.resouceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TvShowData.TvShowItem tvShowItem = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resouceId, parent, false);
            holder = new ViewHolder(convertView);
            holder.tvShowName.setTextSize(DimenisionUtil.pxToSp(getContext(), DimenisionUtil.getScreenSize(getContext()).heightPixels / 30));
            holder.tvShowTime.setTextSize(DimenisionUtil.pxToSp(getContext(), DimenisionUtil.getScreenSize(getContext()).heightPixels / 30));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvShowName.setText(tvShowItem.pName);
        holder.tvShowTime.setText(tvShowItem.time);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_show_name)
        TextView tvShowName;
        @BindView(R.id.tv_show_time)
        TextView tvShowTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


