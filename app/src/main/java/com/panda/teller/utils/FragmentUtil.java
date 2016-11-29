package com.panda.teller.utils;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-16.
 */

public class FragmentUtil {

    private List<Fragment> frags;

    public FragmentUtil() {
        frags = new ArrayList<Fragment>();
    }

    void addFragment(Fragment fragment) {
        frags.add(fragment);
    }

}
