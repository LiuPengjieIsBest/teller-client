package com.panda.teller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;

/**
 * 首页下方的四个tab
 */

public class TabsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tabsLayout = inflater.inflate(R.layout.fragment_tabs, container, false);
        return tabsLayout;
    }

}
