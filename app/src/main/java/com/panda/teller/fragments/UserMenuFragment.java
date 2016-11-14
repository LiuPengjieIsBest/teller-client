package com.panda.teller.fragments;

import android.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        /*
            这里监听的是“个人信息”按钮
         */
        userMenuLayout.findViewById(R.id.button_infomation).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

         /*
            这里监听的是“我的提问”按钮
         */
        userMenuLayout.findViewById(R.id.button_myQuestion).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
         /*
            这里监听的是“我的回答”按钮
         */
        userMenuLayout.findViewById(R.id.button_myAnswer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
         /*
            这里监听的是“我的投稿”按钮
         */
        userMenuLayout.findViewById(R.id.button_myDraft).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
         /*
            这里监听的是“我的征集令”按钮
         */
        userMenuLayout.findViewById(R.id.button_myCollect).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
         /*
            这里监听的是“浏览历史”按钮
         */
        userMenuLayout.findViewById(R.id.button_myHistory).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        /*
            这里监听的是“设置”按钮
         */
        userMenuLayout.findViewById(R.id.button_settings).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });



        return userMenuLayout;
    }
}
