package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panda.teller.R;
import com.panda.teller.models.Video;
import com.panda.teller.views.adapters.MainViewPagerAdapter;
import com.panda.teller.views.widgets.VideoItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-8.
 */

public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener {

    View mainLayout;

    /* 主页轮播器 */
    private ViewPager mainPager;

    /* 提供页面 */
    private MainViewPagerAdapter mainPagerAdapter;

    /* 小圆图片 */
    private ImageView[] pointImgs;

    /* 视频单元布局 */
    RecyclerView videoListView;

    /* 视频列表 */
    List<Video> videos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainLayout = inflater.inflate(R.layout.fragment_main, container, false);
        this.mainLayout = mainLayout;
        initView(mainLayout);
        return mainLayout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        for(int i = 0; i < pointImgs.length; i++) {
            /* 点击后切换小圆点图片 */
            if(position == i) {
                pointImgs[i].setImageResource(R.drawable.point_selected);
            } else {
                pointImgs[i].setImageResource(R.drawable.point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initView(View v) {
        /* 主页轮播器 */
        mainPager = (ViewPager) v.findViewById(R.id.vp_viewpager_main);
        mainPagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        mainPager.setAdapter(mainPagerAdapter);
        mainPager.addOnPageChangeListener(this);
        /* 小圆点图片 */
        pointImgs = new ImageView[3];
        pointImgs[0] = (ImageView) v.findViewById(R.id.iv_viewpager_main_first);
        pointImgs[1] = (ImageView) v.findViewById(R.id.iv_viewpager_main_second);
        pointImgs[2] = (ImageView) v.findViewById(R.id.iv_viewpager_main_third);
        /* 视频布局 */
        RecyclerView videoListView = (RecyclerView) v.findViewById(R.id.rv_fragment_main_rcmd);
        videoListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        videoListView.setAdapter(new VideoListAdapter());
        /* 初始化视频列表 */
        videos = new ArrayList<Video>();
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
    }

    /* 控制视频菜单中的每个单元，包括添加数据、添加监听等 */
    class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

        @Override
        public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            VideoViewHolder holder = new VideoViewHolder(new VideoItemLayout(parent.getContext()));
            return holder;
        }
        @Override
        public void onBindViewHolder(VideoViewHolder holder, int position) {
            holder.vl.setVideoItem(videos.get(position));
        }
        @Override
        public int getItemCount() {
            return videos.size();
        }

        class VideoViewHolder extends RecyclerView.ViewHolder {
            VideoItemLayout vl;
            public VideoViewHolder(View view) {
                super(view);
                vl = (VideoItemLayout) view;
            }
        }

    }
}
