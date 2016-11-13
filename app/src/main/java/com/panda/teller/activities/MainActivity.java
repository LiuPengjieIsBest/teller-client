package com.panda.teller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.fragments.CatgFragment;
import com.panda.teller.fragments.MainFragment;
import com.panda.teller.fragments.SearchFragment;
import com.panda.teller.fragments.SettingFragment;
import com.panda.teller.fragments.StarFragment;
import com.panda.teller.views.actionbar.MainActionbar;
import com.panda.teller.views.actionbar.SearchActionbar;



/**
 * Created by huang on 16-11-1.
 * 主页
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* 主页左上角用户菜单弹出按钮 */
    private ImageView userMenuImg;

    /* 主页actionbar中央的搜索框，实际上是假的 */
    private EditText searchEdt;

    /* 主页点击搜索栏后显示的界面 */
    private SearchFragment searchFragment;

    /* 用户菜单，左侧隐藏的抽屉 */
    private DrawerLayout userMenuLayout;

    /* 搜索页actionbar中央的搜索框 */
    private EditText searchEdt1;

    /* 搜索页取消按钮（TextView） */
    private TextView cancelTxt;

    /* 四个要在首页显示的界面，包括主、分类、星号（？）、设置 */
    private MainFragment mainFragment;
    private CatgFragment catgFragment;
    private StarFragment starFragment;
    private SettingFragment settingFragment;

    /* 显示tabs的每一栏的布局，在fragment_tabs中可以找到它们，通过监听这些对象的点击事件来做对应切换 */
    private View mainLayout;
    private View catgLayout;
    private View starLayout;
    private View settingLayout;

    /* 每个tab中的图片 */
    private ImageView mainImg;
    private ImageView catgImg;
    private ImageView starImg;
    private ImageView settingImg;

    /* Fragment管理器 */
    private FragmentManager fragmentManager;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 初始化标题栏 */
        setActionBar("main");
        /* 初始化布局 */
        initViews();
        /* 初始化Fragment管理器 */
        fragmentManager = getSupportFragmentManager();
        /* 第一次启动选中第0个选项 */
        setTabSelection(0);
    }

    private void setActionBar(String type) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        ActionBar.LayoutParams lp = null;
        switch(type) {
        case "main":
            lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            View mainActionBar = new MainActionbar(this);
            actionBar.setCustomView(mainActionBar, lp);
            /* 主页mainActionbar中的用户菜单按钮 */
            userMenuImg = (ImageView) findViewById(R.id.iv_actionbar_main_user);
            userMenuImg.setOnClickListener(this);
            /* 主页mainActionbar中的搜索框 */
            searchEdt = (EditText) findViewById(R.id.edtTxt_actionbar_main_search);
            searchEdt.setOnClickListener(this);
            break;
        case "search":
            lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            View searchActionBar = new SearchActionbar(this);
            actionBar.setCustomView(searchActionBar, lp);
            /* 搜索页取消按钮 */
            cancelTxt = (TextView) findViewById(R.id.tv_actionbar_search_cancel);
            cancelTxt.setOnClickListener(this);
            break;
        default:
            break;
        }
    }

    /**
     * 初始化View对象
     */
    private void initViews() {
        /* 主页用户抽屉菜单 */
        userMenuLayout = (DrawerLayout) findViewById(R.id.dl_activity_main_drawer_layout);
        /* 初始化tab图标对象 */
        mainImg = (ImageView) findViewById(R.id.iv_fragment_tabs_main_img);
        catgImg = (ImageView) findViewById(R.id.iv_fragment_tabs_catg_img);
        starImg = (ImageView) findViewById(R.id.iv_fragment_tabs_star_img);
        settingImg = (ImageView) findViewById(R.id.iv_fragment_tabs_setting_img);
        /* 初始化tab布局对象 */
        mainLayout = findViewById(R.id.rl_fragment_tabs_main_layout);
        catgLayout = findViewById(R.id.rl_fragment_tabs_catg_layout);
        starLayout = findViewById(R.id.rl_fragment_tabs_star_layout);
        settingLayout = findViewById(R.id.rl_fragment_tabs_setting_layout);
        /* 设置监听器，以图在点击下面的tab后转换布局 */
        mainLayout.setOnClickListener(this);
        catgLayout.setOnClickListener(this);
        starLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
    }

    /**
     * 设置选中的tab和其对应的界面
     * @param position 第几个，从0开始计数
     */
    private void setTabSelection(int position) {
        /* 每次选中前清除所有tab的选中状态 */
        clearSelection();
        /* 开启一个Fragment事务 */
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /* 先隐藏所有Fragment，以防止有多个Fragment同时显示在界面上 */
        hideFragments(transaction);
        switch(position) {
        case 0:
            /* 改变图标状态 */
            mainImg.setImageResource(R.drawable.main_selected);
            /* 改变页面 */
            if(mainFragment == null) {
                mainFragment = new MainFragment();
                /* activity_main可以找到一个用于显示界面的FrameLayout */
                transaction.add(R.id.fl_activity_main_content, mainFragment);
            } else {
                transaction.show(mainFragment);
            }
            break;
        case 1:
            catgImg.setImageResource(R.drawable.catg_selected);
            if(catgFragment == null) {
                catgFragment = new CatgFragment();
                transaction.add(R.id.fl_activity_main_content, catgFragment);
            } else {
                transaction.show(catgFragment);
            }
            break;
        case 2:
            starImg.setImageResource(R.drawable.star_selected);
            if(starFragment == null) {
                starFragment = new StarFragment();
                transaction.add(R.id.fl_activity_main_content, starFragment);
            } else {
                transaction.show(starFragment);
            }
            break;
        case 3:
            settingImg.setImageResource(R.drawable.setting_selected);
            if(settingFragment == null) {
                settingFragment = new SettingFragment();
                transaction.add(R.id.fl_activity_main_content, settingFragment);
            } else {
                transaction.show(settingFragment);
            }
            break;
        default:
            break;
        }
        /* 不要忘了提交事务 */
        transaction.commit();
    }

    /**
     * 清除所有tab的选中状态
     */
    private void clearSelection() {
        mainImg.setImageResource(R.drawable.main);
        catgImg.setImageResource(R.drawable.catg);
        starImg.setImageResource(R.drawable.star);
        settingImg.setImageResource(R.drawable.setting);
    }

    /**
     * 隐藏所有Fragment
     * @param transaction 操作Fragment的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if(mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if(catgFragment != null) {
            transaction.hide(catgFragment);
        }
        if(starFragment != null) {
            transaction.hide(starFragment);
        }
        if(settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    /* 进入搜索页前的页面 */
    private static int last = 0;

    @Override
    public void onClick(View v) {

        FragmentTransaction transaction;
        switch(v.getId()) {
        /* 四个Tab */
        case R.id.rl_fragment_tabs_main_layout:
            setTabSelection(0);
            break;
        case R.id.rl_fragment_tabs_catg_layout:
            setTabSelection(1);
            break;
        case R.id.rl_fragment_tabs_star_layout:
            setTabSelection(2);
            break;
        case R.id.rl_fragment_tabs_setting_layout:
            setTabSelection(3);
            break;
        case R.id.iv_actionbar_main_user:
            if(userMenuLayout.isDrawerVisible(GravityCompat.START)) {
                userMenuLayout.closeDrawer(GravityCompat.START);
            } else {
                userMenuLayout.openDrawer(GravityCompat.START);
            }
            break;
        /* 搜索按钮 */
        case R.id.edtTxt_actionbar_main_search:
            /* 进入搜索界面 */
            transaction = fragmentManager.beginTransaction();
            /* 先记录当前页面并隐藏所有fragment */
            if(mainFragment != null && mainFragment.isVisible()) {
                last = 0;
            } else if(catgFragment != null && catgFragment.isVisible()) {
                last = 1;
            } else if(starFragment != null && starFragment.isVisible()) {
                last = 2;
            } else {
                last = 3;
            }
            clearSelection();
            hideFragments(transaction);
            /* 叫出搜索页 */
            if(null == searchFragment) {
                searchFragment = new SearchFragment();
                transaction.add(R.id.fl_activity_main_content, searchFragment);
            }
            else {
                transaction.show(searchFragment);
            }
            transaction.commit();
            setActionBar("search");
            break;
        case R.id.tv_actionbar_search_cancel:
            transaction = fragmentManager.beginTransaction();
            /* 先隐藏搜索页面 */
            transaction.hide(searchFragment);
            transaction.commit();
            setTabSelection(last);
            setActionBar("main");
            break;
        default:
            break;
        }
    }

}
