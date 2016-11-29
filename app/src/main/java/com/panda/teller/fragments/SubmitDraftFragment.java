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

public class SubmitDraftFragment extends Fragment {

    View submitDraftLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        submitDraftLayout = inflater.inflate(R.layout.fragment_submit_draft, container, false);
        initView();
        return submitDraftLayout;
    }

    private void initView() {

    }
}
