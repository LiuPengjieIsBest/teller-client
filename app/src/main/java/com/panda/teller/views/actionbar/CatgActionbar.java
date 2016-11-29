package com.panda.teller.views.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;

/**
 * Created by root on 16-11-20.
 */

public class CatgActionbar extends Fragment {

    View catgActionbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        catgActionbar = inflater.inflate(R.layout.actionbar_catg, container, false);
        return catgActionbar;
    }

}
