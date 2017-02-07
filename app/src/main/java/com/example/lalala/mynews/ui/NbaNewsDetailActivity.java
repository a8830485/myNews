package com.example.lalala.mynews.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.Util.BaseUtil;
import com.example.lalala.mynews.Util.DimenisionUtil;
import com.example.lalala.mynews.data.NbaNewsDetailData;
import com.example.lalala.mynews.http.TencentServer;
import com.facebook.drawee.view.SimpleDraweeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lalala on 2017/2/6.
 */

public class NbaNewsDetailActivity extends AppCompatActivity {


    private String newsId;

    private String column;

    private LinearLayout layout;

    private ScrollView scrollView;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Intent intent = getIntent();
        newsId = intent.getStringExtra("id");
        column = intent.getStringExtra("column");
        scrollView = new ScrollView(this);
        scrollView.setBackgroundResource(android.R.color.white);
        layout = new LinearLayout(this);
        scrollView.addView(layout);
        TencentServer.getDetail(callback, column, newsId);
        setContentView(scrollView);
    }

    private void initLayout(NbaNewsDetailData data){

        layout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(this);
        title.setText(data.data.title);
        title.setTextColor(Color.BLACK);
        title.setTextSize(22);
        title.setPadding(20, 20, 20, 20);
        layout.addView(title);

        TextView time = new TextView(this);
        time.setText(data.data.pub_time_new);
        time.setTextColor(Color.BLACK);
        time.setTextSize(12);
        time.setGravity(Gravity.RIGHT);
        time.setPadding(10, 10, 10, 10);
        layout.addView(time);

        for(NbaNewsDetailData.Content item : data.data.content){
            if(item.type.equals("text")){
                TextView a = new TextView(this);
                a.setText(item.info);
                a.setPadding(20,20,20,20);
                a.setTextSize(16);
                a.setTextColor(Color.BLACK);
                layout.addView(a);
            }else{
                SimpleDraweeView draweeView = new SimpleDraweeView(this);
                DisplayMetrics metrics = DimenisionUtil.getScreenSize(this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(metrics.widthPixels, (metrics.widthPixels * 457 / 640));
                draweeView.setLayoutParams(params);
                draweeView.setPadding(20, 20, 20, 20);
                draweeView.setController(BaseUtil.getController(item.img.imgurl640.imgurl, draweeView));
                layout.addView(draweeView);
            }
        }

    }

    private Callback<NbaNewsDetailData> callback = new Callback<NbaNewsDetailData>() {
        @Override
        public void onResponse(Call<NbaNewsDetailData> call, Response<NbaNewsDetailData> response) {
            NbaNewsDetailData data = response.body();
            initLayout(data);
        }

        @Override
        public void onFailure(Call<NbaNewsDetailData> call, Throwable t) {
            Log.i("dasdasdsadsad", t.toString());
        }
    };
}
