package com.example.lalala.mynews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lalala.mynews.Adapter.NbaDataAdapter;
import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.JSONUtil;
import com.example.lalala.mynews.data.TeamsRank;
import com.example.lalala.mynews.http.TencentServer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lalala on 2017/2/3.
 */

public class NbaDataFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.nba_data_east_listview)
    ListView nbaDataEastListview;
    NbaDataAdapter eastAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.nba_data, container, false);
        ButterKnife.bind(this, view);
        TencentServer.getTeamRank(callback);
        return view;
    }

    public void onRefresh() {

    }

    private Callback<String> callback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            String str = response.body();
            TeamsRank item = JSONUtil.parseTeamsRank(str);
            item.east.addAll(item.west);
            eastAdapter = new NbaDataAdapter(getActivity(), R.layout.nba_data_item, item.east);
            nbaDataEastListview.setAdapter(eastAdapter);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.i("Failure", t.toString());
        }
    };
}
