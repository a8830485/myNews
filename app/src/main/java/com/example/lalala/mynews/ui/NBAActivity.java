package com.example.lalala.mynews.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lalala.mynews.R;
import com.example.lalala.mynews.ui.fragment.NbaBbsFragment;
import com.example.lalala.mynews.ui.fragment.NbaDataFragment;
import com.example.lalala.mynews.ui.fragment.NbaFragmentInterface;
import com.example.lalala.mynews.ui.fragment.NbaMatchesFragment;
import com.example.lalala.mynews.ui.fragment.NbaMoreFragment;
import com.example.lalala.mynews.ui.fragment.NbaNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/1/31.
 */

public class NBAActivity extends FragmentActivity implements View.OnClickListener {


    @BindView(R.id.nba_viewpager)
    ViewPager viewpager;
    @BindView(R.id.nba_button_matches)
    Button nbaButtonMatches;
    @BindView(R.id.nba_button_news)
    Button nbaButtonNews;
    @BindView(R.id.nba_button_bbs)
    Button nbaButtonBbs;
    @BindView(R.id.nba_button_data)
    Button nbaButtonData;
    @BindView(R.id.nba_button_more)
    Button nbaButtonMore;
    private List<Fragment> fragmentList = new ArrayList<>();

    private Button[] buttons;

    private int[] buttonImg;

    private int[] clickedImg;
    private int currentView = 0;

    @Override
    protected void onCreate(Bundle saveInstaceState) {
        super.onCreate(saveInstaceState);
        setContentView(R.layout.nba_home);
        ButterKnife.bind(this);
        nbaButtonBbs.getId();

        fragmentList.add(new NbaMatchesFragment());
        fragmentList.add(new NbaNewsFragment());
        fragmentList.add(new NbaBbsFragment());
        fragmentList.add(new NbaDataFragment());
        fragmentList.add(new NbaMoreFragment());
        viewpager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));

        buttons = new Button[]{nbaButtonMatches, nbaButtonNews, nbaButtonBbs, nbaButtonData, nbaButtonMore};
        buttonImg = new int[]{R.drawable.match, R.drawable.news, R.drawable.bbs,
                R.drawable.data, R.drawable.more};
        clickedImg = new int[]{R.drawable.clicked_match, R.drawable.clicked_news,
                R.drawable.clicked_bbs, R.drawable.clicked_data, R.drawable.clicked_more};
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nba_button_bbs:
                Log.i("dsadsd", "bbs");
                changeView(2);
                break;
            case R.id.nba_button_matches:
                Log.i("dsadsd", "matches");
                changeView(0);
                break;
            case R.id.nba_button_data:
                Log.i("dsadsd", "data");
                changeView(3);
                break;
            case R.id.nba_button_news:
                Log.i("dsadsd", "news");
                changeView(1);
                break;
            case R.id.nba_button_more:
                Log.i("dsadsd", "more");
                changeView(4);
                break;
            default:
                break;
        }
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {
        public MyFragmentAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int count) {
            return fragmentList.get(count);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);// 这句话要放在最前面，否则会报错
            // 获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = viewpager.getCurrentItem();
            if (currentItem == currentView) {
                return;
            }
            buttons[currentView].setBackgroundResource(buttonImg[currentView]);
            currentView = viewpager.getCurrentItem();
            buttons[currentView].setBackgroundResource(clickedImg[currentView]);
        }
    }


    // 手动设置ViewPager要显示的视图
    private void changeView(int desTab) {
        if(currentView == desTab) {
            Log.i("finishUpdate", "finishUpdate");
            ((SwipeRefreshLayout.OnRefreshListener) fragmentList.get(currentView)).onRefresh();
        }
        viewpager.setCurrentItem(desTab, true);
    }
}
