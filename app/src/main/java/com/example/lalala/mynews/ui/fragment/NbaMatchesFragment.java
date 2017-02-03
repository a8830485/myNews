package com.example.lalala.mynews.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lalala.mynews.Adapter.NbaMatchAdapter;
import com.example.lalala.mynews.R;
import com.example.lalala.mynews.data.NbaMatchData;
import com.example.lalala.mynews.http.TencentServer;
import com.facebook.drawee.backends.pipeline.Fresco;

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

public class NbaMatchesFragment extends Fragment {

    @BindView(R.id.nba_gamelist_listview)
    ListView nbaGamelistListview;

    private NbaMatchData nbaMatchData;

    private NbaMatchAdapter adapter;

    List<NbaMatchData.Match> list = new ArrayList<>();

    // private Handler handler = new Handler




    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view =inflater.inflate(R.layout.nba_home, container, false);
        ButterKnife.bind(this, view);
        TencentServer.getMatches(callback);
        return view;
    }

    private Callback<NbaMatchData> callback = new Callback<NbaMatchData>() {
        @Override
        public void onResponse(Call<NbaMatchData> call, Response<NbaMatchData> response) {
            nbaMatchData = response.body();

            Iterator iter = nbaMatchData.data.matches.entrySet().iterator();
            List<String> dates = nbaMatchData.data.dates;
            int cnt = 0;
            while(iter.hasNext()){
                Map.Entry entry = (Map.Entry)iter.next();
                int size = list.size();
                list.addAll(((NbaMatchData.Matches)entry.getValue()).list);
                list.get(size).date = dates.get(cnt++);
            }
            (getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new NbaMatchAdapter(getActivity(), R.layout.nba_game_item, list);
                    nbaGamelistListview.setAdapter(adapter);
                }
            });
        }

        @Override
        public void onFailure(Call<NbaMatchData> call, Throwable t) {
            Log.i("NbaHome", t.toString());
        }
    };
}
