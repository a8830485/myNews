package com.example.lalala.mynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lalala.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lalala on 2017/1/31.
 */

public class NBAActivity extends FragmentActivity implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nba_button_bbs:
                Log.i("dsadsd", "bbs");
                break;
            case R.id.nba_button_matches:
                Log.i("dsadsd", "matches");
                break;
            case R.id.nba_button_data:
                Log.i("dsadsd", "data");
                break;
            case R.id.nba_button_news:
                Log.i("dsadsd", "news");
                if(currentView != NEWS_VIEW) {

                }
                break;
            case R.id.nba_button_more:
                Log.i("dsadsd", "more");
                break;
            default:
                break;
        }
    }

}
