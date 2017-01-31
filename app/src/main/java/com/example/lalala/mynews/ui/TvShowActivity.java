package com.example.lalala.mynews.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lalala.mynews.Adapter.TvShowAdapter;
import com.example.lalala.mynews.R;
import com.example.lalala.mynews.data.TvChannelData;
import com.example.lalala.mynews.data.TvShowData;
import com.example.lalala.mynews.http.TvServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lalala on 2017/1/23.
 */

public class TvShowActivity extends AppCompatActivity {


    private static final int FINISH_QUERY = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_show_listview)
    ListView tvShowListview;
    @BindView(R.id.no_tvshow)
    TextView noTvshow;

    private TvChannelData.TvChannelItem tvChannelItem;

    private ArrayList<TvShowData.TvShowItem> tvShowList = new ArrayList<>();

    private TvShowAdapter adapter;

    private int mYear, mMonth, mDay;

    private String date;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FINISH_QUERY:
                    if (adapter == null) {
                        adapter = new TvShowAdapter(TvShowActivity.this, R.layout.tv_show_item, tvShowList);
                        tvShowListview.setEmptyView(noTvshow);
                        tvShowListview.setAdapter(adapter);
                    } else
                        adapter.notifyDataSetChanged();
            }
        }
    };

    private Callback<TvShowData> callback = new Callback<TvShowData>() {
        @Override
        public void onResponse(Call<TvShowData> call, Response<TvShowData> response) {
            TvShowData item = response.body();
            List<TvShowData.TvShowItem> list = item.result;
            tvShowList.clear();
            if (list != null)
                for (TvShowData.TvShowItem s : list) {
                    tvShowList.add(s);
                }
            Message message = Message.obtain();
            message.what = FINISH_QUERY;
            handler.sendMessage(message);
        }

        @Override
        public void onFailure(Call<TvShowData> call, Throwable t) {

        }
    };


    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(calendar.DAY_OF_MONTH);
        date = String.format("%4d-%02d-%2d", mYear, mMonth, mDay);
        Intent intent = getIntent();
        tvChannelItem = (TvChannelData.TvChannelItem) intent.getSerializableExtra("tvChannel");

        TvServer.queryTvShow(tvChannelItem.rel, date, callback);
        setContentView(R.layout.tv_show);
        ButterKnife.bind(this);

        toolbar.setTitle(getString(R.string.tv_show));
        toolbar.setSubtitle(getString(R.string.date) + date);

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_date:
                        DatePickerDialog datePickerDialog = new DatePickerDialog(TvShowActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                mYear = year;
                                mMonth = month + 1;
                                mDay = day;
                                date = String.format("%4d-%02d-%2d", mYear, mMonth, mDay);
                                toolbar.setSubtitle(getString(R.string.date) + date);
                                TvServer.queryTvShow(tvChannelItem.rel, date, callback);
                            }
                        }, mYear, mMonth - 1, mDay);
                        datePickerDialog.show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
