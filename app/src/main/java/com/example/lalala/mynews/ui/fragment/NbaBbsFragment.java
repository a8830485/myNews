package com.example.lalala.mynews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.http.TencentServer;

import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/2/3.
 */

public class NbaBbsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.nba_bbs, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public void onRefresh(){

    }
}
