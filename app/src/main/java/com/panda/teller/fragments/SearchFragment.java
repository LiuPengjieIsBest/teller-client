package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.panda.teller.R;
import com.panda.teller.views.adapters.TextFlowAdapter;
import com.panda.teller.views.widgets.FlowLayout;

/**
 * Created by root on 16-11-9.
 */

public class SearchFragment extends Fragment implements View.OnClickListener {

    View searchLayout;

    /* 历史搜索 */
    FlowLayout hstFlow;
    TextFlowAdapter hstAdapter;

    /* 推荐搜索 */
    FlowLayout rcmdFlow;
    TextFlowAdapter rcmdAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View searchLayout = inflater.inflate(R.layout.fragment_search, container, false);
        this.searchLayout = searchLayout;
        initView();
        return searchLayout;
    }

    private void initView() {
        hstFlow = (FlowLayout) searchLayout.findViewById(R.id.flow_fragment_search_hst);
        hstAdapter = new TextFlowAdapter(getContext(), hstFlow);
        hstAdapter.setOnItemClickListener(this);
        rcmdFlow = (FlowLayout) searchLayout.findViewById(R.id.flow_fragment_search_rcmd);
        rcmdAdapter = new TextFlowAdapter(getContext(), rcmdFlow);
        rcmdAdapter.setOnItemClickListener(this);

        initDatas();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_flowlayout_item_txt:
                Toast.makeText(getContext(), ((TextView)v).getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initDatas() {
        hstAdapter.addItem("name1");
        hstAdapter.addItem("name2222222");
        hstAdapter.addItem("name33");
        hstAdapter.addItem("name4");
        hstAdapter.addItem("name5");
        hstAdapter.addItem("name6");

        rcmdAdapter.addItem("name1");
        rcmdAdapter.addItem("name222222222");
        rcmdAdapter.addItem("name3333");
        rcmdAdapter.addItem("name4444444444");
        rcmdAdapter.addItem("name5");
        rcmdAdapter.addItem("name6");
    }
}
