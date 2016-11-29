package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.panda.teller.R;
import com.panda.teller.models.Video;
import com.panda.teller.utils.OnItemClickListener;
import com.panda.teller.views.adapters.MainViewPagerAdapter;
import com.panda.teller.views.widgets.VideoItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-8.
 */

public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

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
        super.onCreateView(inflater, container, savedInstanceState);
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
        VideoListAdapter adapter = new VideoListAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, Object o) {
            }
        });
        videoListView.setAdapter(adapter);
        /* 解决打开后ScrollView（最外层）的页面布局起始位置不在最顶部 */
        videoListView.setFocusable(false);
        /* 初始化视频列表 */
        videos = new ArrayList<Video>();
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
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

        private OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onBindViewHolder(VideoViewHolder holder, final int position) {
            holder.vl.setVideoItem(videos.get(position));
            if(onItemClickListener != null) {
                holder.vl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Video video = videos.get(position);
                        onItemClickListener.onItemClick(v, video);
                        Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
