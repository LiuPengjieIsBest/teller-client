package com.panda.teller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.panda.teller.R;
import com.panda.teller.models.Collect;
import com.panda.teller.models.Video;
import com.panda.teller.views.adapters.CollectAdapter;
import com.panda.teller.views.adapters.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 51275 on 2016/12/1.
 */

public class MyCollectFragment extends BaseFragment implements View.OnClickListener  {
    /* “我的征集令”页的布局 */
    private View myCollectLayout;

    /* 征集令列表视图 */
    private ListView listView;

    private CollectAdapter adapter;

    private List<Collect> collects;

    private ImageView undoImage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        collects = new ArrayList<Collect>();
        myCollectLayout = inflater.inflate(R.layout.fragment_mycollect, null, false);
        initView();
        return myCollectLayout;
    }

    /* 主要是初始化actionbar和征集令列表 */
    public void initView() {
        adapter = new CollectAdapter(getContext(), R.layout.collect_item_long, collects);
        listView = (ListView) myCollectLayout.findViewById(R.id.lv_fragment_mycollect_list);
        listView.setAdapter(adapter);
        addItem(new Collect());
        addItem(new Collect());

        undoImage = (ImageView) myCollectLayout.findViewById(R.id.iv_fragment_mycollect_undo);
        undoImage.setOnClickListener(this);
    }

    void addItem(Collect collect) {
        collects.add(collect);
        adapter.notifyDataSetChanged();
        listView.setSelection(collects.size());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_fragment_mycollect_undo:
            /* 点击左上角返回按钮，隐藏当前Fragment */
                mActivity.hideFragment(this);
                break;
            default:
                break;
        }
    }
}
