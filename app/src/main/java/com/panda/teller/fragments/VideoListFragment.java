package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Video;
import com.panda.teller.views.adapters.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频列表
 * 在CatgFragment中点击一个大类跳转到SubareaFragment
 * 在SubareaFragment中可以选择查看视频列表或问题列表
 */

public class VideoListFragment extends Fragment {

    View videoListLayout;

    ListView videoListView;

    List<Video> videos;

    VideoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        videoListLayout = inflater.inflate(R.layout.fragment_videolist, container, false);
        initView();
        return videoListLayout;
    }

    private void initView() {
        videoListView = (ListView) videoListLayout.findViewById(R.id.lv_fragment_videolist_list);
        videos = new ArrayList<Video>();
        adapter = new VideoAdapter(getContext(), R.layout.video_item_long, videos);
        videoListView.setAdapter(adapter);
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /* 点击后跳转到对应页面显示视频详细信息 */
                Video video = videos.get(position);
                /* TODO */
            }
        });

        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());
        videos.add(new Video());

    }

}
