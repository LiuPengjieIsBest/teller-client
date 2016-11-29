package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;

/**
 * Created by root on 16-11-21.
 */

public class SubmitVideoFragment extends Fragment {

    View submitVideoLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        submitVideoLayout = inflater.inflate(R.layout.fragment_submit_video, container, false);
        initView();
        return submitVideoLayout;
    }

    private void initView() {

    }
}
