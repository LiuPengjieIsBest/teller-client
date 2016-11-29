package com.panda.teller.views.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;

/**
 * Created by root on 16-11-22.
 */

public class SubmitActionbar extends Fragment {

    View submitActionbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        submitActionbar = inflater.inflate(R.layout.actionbar_submit, container, false);
        return submitActionbar;
    }

}
