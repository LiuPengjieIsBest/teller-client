package com.panda.teller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.teller.R;

/**
 * Created by huang on 16-11-9.
 * 用户菜单，就是左边拉出来的那个
 */

public class UserMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View userMenuLayout = inflater.inflate(R.layout.fragment_user_menu, container, false);
        return userMenuLayout;
    }

}
