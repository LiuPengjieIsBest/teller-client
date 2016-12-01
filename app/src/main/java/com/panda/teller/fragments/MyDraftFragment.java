package com.panda.teller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Quiz;
import com.panda.teller.models.Video;
import com.panda.teller.views.adapters.VideoAdapter;

import java.util.ArrayList;
import java.util.List;



public class MyDraftFragment extends BaseFragment implements View.OnClickListener {

    /* “我的投稿”页的布局 */
    private View myDraftLayout;

    /* 投稿列表视图 */
    private ListView listView;

    private VideoAdapter adapter;

    private List<Video> videos;

    private ImageView undoImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        videos = new ArrayList<Video>();
        myDraftLayout = inflater.inflate(R.layout.fragment_mydraft, null, false);
        initView();
        return myDraftLayout;
    }

    /* 主要是初始化actionbar和投稿列表 */
    public void initView() {
        adapter = new VideoAdapter(getContext(), R.layout.video_item_long, videos);
        listView = (ListView) myDraftLayout.findViewById(R.id.lv_fragment_mydraft_list);
        listView.setAdapter(adapter);
        addItem(new Video());
        addItem(new Video());

        undoImage = (ImageView) myDraftLayout.findViewById(R.id.iv_fragment_mydraft_undo);
        undoImage.setOnClickListener(this);
    }

    void addItem(Video video) {
        videos.add(video);
        adapter.notifyDataSetChanged();
        listView.setSelection(videos.size());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_fragment_mydraft_undo:
            /* 点击左上角返回按钮，隐藏当前Fragment */
                mActivity.hideFragment(this);
                break;
            default:
                break;
        }
    }
}
