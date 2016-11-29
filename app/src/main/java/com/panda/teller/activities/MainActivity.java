package com.panda.teller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.panda.teller.R;
import com.panda.teller.fragments.HomeFragment;


/**
 * Created by huang on 16-11-1.
 * 主页
 */

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;

    FragmentManager fragmentManager;

    /**
     * 主界面启动器
     * @param context 想要跳过来的那个
     * @param userId 主界面需要接受的数据，还没定好
     */
    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        homeFragment = new HomeFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_activity_main_content, homeFragment).commit();
    }
/*
    public void switchFragment(Fragment from, Fragment to) {
        if(currentFragment != to) {
            currentFragment = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(!to.isAdded()) {
                transaction.hide(from).add(R.id.fl_activity_main_content, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }
*/
    public void showFragment(Fragment frag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(!frag.isAdded()) {
            transaction.add(R.id.fl_activity_main_content, frag).commit();
        } else {
            transaction.show(frag).commit();
        }
    }

    public void hideFragment(Fragment frag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(frag).commit();
    }


}
