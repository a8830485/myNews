package com.example.lalala.mynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvTypeData;
import com.example.lalala.mynews.http.TvServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/1/24.
 */

public class TvHome extends AppCompatActivity {
        /*

         */
    private static final int TYPE_LEVEL = 1;
        /*

         */
    private static final int CHANNEL_LEVEL = 2;

    @BindView(R.id.listview)
    ListView mListView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

        /*
        处于什么等级，1为频道分类界面，2为频道界面
         */
    private int level = 0;

    private List<TvTypeData.TvTypeItem> tvTypeList;

    private List<TvChannelData.TvChannelItem> tvChannelList;

    private ArrayList<String> array = new ArrayList<>();

    private ArrayAdapter<String> adapter;

    private android.os.Handler handler = new android.os.Handler(){

        public void handleMessage(Message msg){
                switch (msg.what){
                case TYPE_LEVEL:
                mToolbar.setTitle(getString(R.string.tv_type));
                if(tvTypeList.size() > 0) {
                    array.clear();
                    for(TvTypeData.TvTypeItem typeItem : tvTypeList)
                        array.add(new String(typeItem.name));
                    adapter = new ArrayAdapter<>(TvHome.this, android.R.layout.simple_list_item_1, array);
                    mListView.setAdapter(adapter);
                    level = TYPE_LEVEL;
                }
                break;
                case CHANNEL_LEVEL:
                mToolbar.setTitle(getString(R.string.tv_channel));
                if(tvTypeList.size() > 0) {
                    array.clear();
                    for (TvChannelData.TvChannelItem channelItem : tvChannelList)
                        array.add(new String(channelItem.channelName));
                    adapter = new ArrayAdapter<>(TvHome.this, android.R.layout.simple_list_item_1, array);
                    mListView.setAdapter(adapter);
                    level = CHANNEL_LEVEL;
                }
                    break;
                    default:
                        break;
                }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_home);

        ButterKnife.bind(this);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (level){
                    case CHANNEL_LEVEL:
                        TvServer.queryTvType(tvTypeCallback);
                        break;
                }
            }
        });
        TvServer.queryTvType(tvTypeCallback);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (level){
                    case TYPE_LEVEL:
                        TvServer.quertTvChannel(tvTypeList.get(position).id, tvChannelCallback);
                        break;
                    case CHANNEL_LEVEL:
                        Intent intent = new Intent(TvHome.this,TvShowActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tvChannel", tvChannelList.get(position));
                        intent.putExtra("tvChannel", tvChannelList.get(position));
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private retrofit2.Callback<TvTypeData> tvTypeCallback = new retrofit2.Callback<TvTypeData>() {
        @Override
        public void onResponse(retrofit2.Call<TvTypeData> call, retrofit2.Response<TvTypeData> response) {
            TvTypeData tvTypeData = response.body();
            tvTypeList = tvTypeData.result;
            Message message = Message.obtain();
            message.what = TYPE_LEVEL;
            handler.sendMessage(message);
        }
    @Override
     public void onFailure(retrofit2.Call<TvTypeData> call, Throwable t) {

        }
    };


    private retrofit2.Callback<TvChannelData> tvChannelCallback = new retrofit2.Callback<TvChannelData>() {
    @Override
    public void onResponse(retrofit2.Call<TvChannelData> call, retrofit2.Response<TvChannelData> response) {
            TvChannelData tvChannelData = response.body();
            tvChannelList = tvChannelData.result;
            Message message = Message.obtain();
            message.what = CHANNEL_LEVEL;
            handler.sendMessage(message);
            }

    @Override
    public void onFailure(retrofit2.Call<TvChannelData> call, Throwable t) {

        }
    };
}