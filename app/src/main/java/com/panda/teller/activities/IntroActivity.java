package com.panda.teller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.panda.teller.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-1.
 * 软件介绍页，主体为一个轮播器
 * 在播放完毕后将跳转进入EntrActivity
 */

public class IntroActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    List<View> views;
    private ImageView[] points;//这是轮播图片中的小圆点
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};//这是小圆点对应的id
    Button startBtn;//从引导页进入app的按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.introactivity);
        initViews();
        initPoints();
    }

    public void initViews()//一个初始化的方法
    {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));

        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
        startBtn = (Button) views.get(2).findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this, EntrActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void initPoints()//初始化小圆点
    {
        points = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++)
            points[i] = (ImageView) findViewById(ids[i]);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i)
                points[i].setImageResource(R.drawable.login_point_selected);
            else
                points[i].setImageResource(R.drawable.login_point);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}