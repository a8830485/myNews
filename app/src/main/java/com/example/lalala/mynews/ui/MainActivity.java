package com.example.lalala.mynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lalala.mynews.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this) ;
    }

    @OnClick(R.id.button_to_tv)
    public void toTvHome(View view){
        Intent intent = new Intent(MainActivity.this, TvHome.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_to_nba)
    public void toNbaHome(View view){
        Intent intent = new Intent(MainActivity.this, NbaHome.class);
        startActivity(intent);
    }
}
