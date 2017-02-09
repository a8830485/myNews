package com.example.lalala.mynews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lalala.mynews.Adapter.NbaNewsAdapter;
import com.example.lalala.mynews.R;
import com.example.lalala.mynews.data.NbaNewItemsData;
import com.example.lalala.mynews.data.NbaNewsIndexesData;
import com.example.lalala.mynews.http.TencentServer;
import com.example.lalala.mynews.ui.NbaNewsDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lalala on 2017/1/31.
 */

public class NbaNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.nba_news_listview)
    ListView nbaNewsListview;
    @BindView(R.id.nba_news_refreshlayout)
    SwipeRefreshLayout nbaNewsRefreshlayout;

    private NbaNewsAdapter adapter;

    private List<NbaNewItemsData.NbaNewsItemData> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.nba_news, container, false);
        ButterKnife.bind(this, view);
        nbaNewsRefreshlayout.setOnRefreshListener(this);
        onRefresh();
        nbaNewsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NbaNewItemsData.NbaNewsItemData item = list.get(position);
                Intent intent = new Intent(getActivity(), NbaNewsDetailActivity.class);
                intent.putExtra("column", item.column);
                intent.putExtra("id", item.newsId);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onRefresh() {
        nbaNewsRefreshlayout.setRefreshing(true);
        TencentServer.getIndexes(indexesCallback, "news");
    }

    private Callback<NbaNewsIndexesData> indexesCallback = new Callback<NbaNewsIndexesData>() {
        @Override
        public void onResponse(Call<NbaNewsIndexesData> call, Response<NbaNewsIndexesData> response) {
            NbaNewsIndexesData item = response.body();
            String idArr = "";
            for (NbaNewsIndexesData.NbaNewsIndexData data : item.data) {
                idArr += data.id + ",";
            }
            idArr = idArr.substring(0, idArr.length() - 1);
            TencentServer.getItems(itemsCallback, item.data.get(0).column, idArr);
        }

        @Override
        public void onFailure(Call<NbaNewsIndexesData> call, Throwable t) {

        }
    };

    private Callback<NbaNewItemsData> itemsCallback = new Callback<NbaNewItemsData>() {
        @Override
        public void onResponse(Call<NbaNewItemsData> call, Response<NbaNewItemsData> response) {
            NbaNewItemsData item = response.body();
            list.clear();
            for (Map.Entry<String, NbaNewItemsData.NbaNewsItemData> entry : item.data.entrySet()) {
                entry.getValue().index = entry.getKey();
                list.add(entry.getValue());
            }
            Collections.sort(list, new Comparator<NbaNewItemsData.NbaNewsItemData>() {
                @Override
                public int compare(NbaNewItemsData.NbaNewsItemData o1, NbaNewItemsData.NbaNewsItemData o2) {
                    return o2.index.compareTo(o1.index);
                }
            });
            (getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter = new NbaNewsAdapter(getActivity(), R.layout.nba_news_item, list.subList(0, 30));
                    nbaNewsListview.setAdapter(adapter);
                    nbaNewsRefreshlayout.setRefreshing(false);
                }
            });
        }

        @Override
        public void onFailure(Call<NbaNewItemsData> call, Throwable t) {
            Log.i("dasdasd", t.toString());
        }
    };
}
