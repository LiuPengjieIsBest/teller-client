package com.panda.teller.views.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.fragments.HomeFragment;
import com.panda.teller.fragments.SearchFragment;

/**
 * Created by root on 16-11-10.
 */

public class MainActionbar extends Fragment implements View.OnClickListener {

    private ImageView userMenuImg;

    private EditText searchEdt;

    private TextView cancelTxt;

    /* 搜索页 */
    private SearchFragment searchFragment;

    private FragmentManager fragmentManager;

    View mainActionBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainActionBar = inflater.inflate(R.layout.actionbar_main, container, false);
        initView();
        return mainActionBar;
    }

    private void initView() {
        userMenuImg = (ImageView) mainActionBar.findViewById(R.id.iv_actionbar_main_user);
        searchEdt = (EditText) mainActionBar.findViewById(R.id.edtTxt_actionbar_main_search);
        userMenuImg.setOnClickListener(this);
        searchEdt.setOnClickListener(this);
        fragmentManager = getFragmentManager();
    }

    HomeFragment fragment;

    public void setParentFragment(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction;
        switch(v.getId()) {
        case R.id.iv_actionbar_main_user:
            fragment.updateDrawer(true);
            break;
            /* 搜索按钮 */
        case R.id.edtTxt_actionbar_main_search:
            /* 进入搜索界面 */
            transaction = fragmentManager.beginTransaction();
            /* 叫出搜索页 */
            if (null == searchFragment) {
                searchFragment = new SearchFragment();
                transaction.add(R.id.fl_activity_main_content, searchFragment);
            } else {
                transaction.show(searchFragment);
            }
            transaction.commit();
            break;
        default:
            break;
        }
    }

}
