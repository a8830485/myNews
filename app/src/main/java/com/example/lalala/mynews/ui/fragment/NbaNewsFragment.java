package com.example.lalala.mynews.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.http.TencentServer;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/1/31.
 */

public class NbaNewsFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view =inflater.inflate(R.layout.nba_news, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
