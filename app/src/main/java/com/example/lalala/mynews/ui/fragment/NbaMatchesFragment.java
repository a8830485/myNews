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

import com.example.lalala.mynews.Adapter.NbaMatchAdapter;
import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.data.NbaMatchData;
import com.example.lalala.mynews.http.TencentServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lalala on 2017/1/25.
 */

public class NbaMatchesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.nba_gamelist_listview)
    ListView nbaGamelistListview;
    @BindView(R.id.nba_matches_refreshlayout)
    SwipeRefreshLayout nbaMatchesRefreshlayout;

    private NbaMatchData nbaMatchData;

    private NbaMatchAdapter adapter;

    List<NbaMatchData.Match> list = new ArrayList<>();

    int index = 0;
    // private Handler handler = new Handler


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.nba_matches, container, false);
        ButterKnife.bind(this, view);
        nbaMatchesRefreshlayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }

    public void onRefresh() {
        nbaMatchesRefreshlayout.setRefreshing(true);
        TencentServer.getMatches(callback);
    }

    private Callback<NbaMatchData> callback = new Callback<NbaMatchData>() {
        @Override
        public void onResponse(Call<NbaMatchData> call, Response<NbaMatchData> response) {
            nbaMatchData = response.body();

            Iterator iter = nbaMatchData.data.matches.entrySet().iterator();
            List<String> dates = nbaMatchData.data.dates;
            int cnt = 0;
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                int size = list.size();
                list.addAll(((NbaMatchData.Matches) entry.getValue()).list);
                list.get(size).date = dates.get(cnt++);
                if (list.get(size).date.equals(BaseUtil.getDate()))
                    index = size;
            }
            (getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new NbaMatchAdapter(getActivity(), R.layout.nba_game_item, list);
                    nbaGamelistListview.setAdapter(adapter);
                    nbaGamelistListview.setSelection(index);
                    nbaMatchesRefreshlayout.setRefreshing(false);
                }
            });
        }

        @Override
        public void onFailure(Call<NbaMatchData> call, Throwable t) {
            Log.i("NbaHome", t.toString());
        }
    };
}
