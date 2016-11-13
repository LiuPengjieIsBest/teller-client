package com.panda.teller.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.panda.teller.fragments.MainSlidePageFragment;

/**
 * Created by root on 16-11-11.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    /* 总共页面数 */
    public final static int NUM_PAGES = 3;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    /* 将Fragment作为新页面补充进来 */
    @Override
    public Fragment getItem(int position) {
        return new MainSlidePageFragment();
    }
    /* adapter将要创建的页面的总数 */
    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}
