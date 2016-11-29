package com.panda.teller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.activities.MainActivity;

/**
 * Created by root on 16-11-20.
 */

public abstract class BaseFragment extends Fragment {

    public MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        return null;
    }



}
