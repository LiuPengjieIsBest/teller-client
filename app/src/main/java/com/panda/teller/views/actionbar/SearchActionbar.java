package com.panda.teller.views.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.fragments.SearchFragment;

/**
 * Created by root on 16-11-11.
 */

public class SearchActionbar extends Fragment implements View.OnClickListener {

    private View searchActionBar;

    private TextView cancelTxt;

    private FragmentManager fragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchActionBar = inflater.inflate(R.layout.actionbar_search, container, false);
        initView();
        return searchActionBar;
    }

    SearchFragment searchFragment;

    public void setParentFragment(SearchFragment fragment) {
        searchFragment = fragment;
    }

    private void initView() {
        cancelTxt = (TextView) searchActionBar.findViewById(R.id.tv_actionbar_search_cancel);
        cancelTxt.setOnClickListener(this);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction;
        switch(v.getId()) {
        case R.id.tv_actionbar_search_cancel:
            transaction = fragmentManager.beginTransaction();
            transaction.hide(searchFragment);
            transaction.commit();
            break;
        default:
            break;
        }
    }
}
