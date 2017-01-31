package com.example.lalala.mynews.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class NbaHome extends AppCompatActivity {

    @BindView(R.id.nba_gamelist_listview)
    ListView nbaGamelistListview;

    private NbaMatchData nbaMatchData;

    private NbaMatchAdapter adapter;

    List<NbaMatchData.Match> list = new ArrayList<>();

    // private Handler handler = new Handler



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.nba_game_item);
        Fresco.initialize(this);
        setContentView(R.layout.nba_home);
        ButterKnife.bind(this);
        TencentServer.getMatches(callback);

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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new NbaMatchAdapter(NbaHome.this, R.layout.nba_game_item, list);
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
