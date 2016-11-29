package com.panda.teller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panda.teller.R;
import com.panda.teller.views.actionbar.CatgActionbar;
import com.panda.teller.views.actionbar.FollowActionbar;
import com.panda.teller.views.actionbar.MainActionbar;
import com.panda.teller.views.actionbar.SubmitActionbar;

import static com.panda.teller.R.drawable.catg;
import static com.panda.teller.R.drawable.main;

/**
 * Created by root on 16-11-16.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    View firstLayout;

    private final static String TAG = "MainActivity";

    /* actionbars */
    private MainActionbar mainActionbar;
    private CatgActionbar catgActionbar;
    private FollowActionbar followActionbar;
    private SubmitActionbar submitActionbar;

    /* 用户菜单，左侧隐藏的抽屉 */
    private DrawerLayout userMenuLayout;

    /* 四个要在首页显示的界面，包括主、分类、星号（？）、设置 */
    private MainFragment mainFragment;
    private CatgFragment catgFragment;
    private FollowFragment followFragment;
    private SubmitFragment submitFragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View firstLayout = inflater.inflate(R.layout.fragment_home, container, false);
        this.firstLayout = firstLayout;
        /* 初始化Fragment管理器 */
        fragmentManager = getFragmentManager();
        /* 初始化布局 */
        initViews();
        /* 初始化活动栏 */
        setActionBar(0);
        Log.d("HomeFragment", "hi");
        /* 第一次启动选中第0个选项 */
        setTabSelection(0);
        return firstLayout;
    }

    private void hideActionBars(FragmentTransaction transaction) {
        if(mainActionbar != null) {
            transaction.hide(mainActionbar);
        }
        if(catgActionbar != null) {
            transaction.hide(catgActionbar);
        }
        if(followActionbar != null) {
            transaction.hide(followActionbar);
        }
        if(submitActionbar != null) {
            transaction.hide(submitActionbar);
        }
    }

    private void setActionBar(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /* 隐藏所有Actionbar */
        hideActionBars(transaction);
        switch(position) {
        case 0:
            if(null == mainActionbar) {
                mainActionbar = new MainActionbar();
                /* 为了在该actionbar中打开本fragment的抽屉 */
                mainActionbar.setParentFragment(this);
                transaction.add(R.id.fl_fragment_home_actionbar, mainActionbar);
            } else {
                transaction.show(mainActionbar);
            }
            break;
        case 1:
            if(null == catgActionbar) {
                catgActionbar = new CatgActionbar();
                transaction.add(R.id.fl_fragment_home_actionbar, catgActionbar);
            } else {
                transaction.show(catgActionbar);
            }
            break;
        case 2:
            if(null == followActionbar) {
                followActionbar = new FollowActionbar();
                transaction.add(R.id.fl_fragment_home_actionbar, followActionbar);
            } else {
                transaction.show(followActionbar);
            }
            break;
        case 3:
            if(null == submitActionbar) {
                submitActionbar = new SubmitActionbar();
                transaction.add(R.id.fl_fragment_home_actionbar, submitActionbar);
            } else {
                transaction.show(submitActionbar);
            }
            break;
        default:
            break;
        }
        transaction.commit();
    }

    /**
     * 初始化View对象
     */
    private void initViews() {
        /* 主页用户抽屉菜单 */
        userMenuLayout = (DrawerLayout) firstLayout.findViewById(R.id.dl_fragment_first_drawer_layout);
        /* 初始化tab图标对象 */
        mainImg = (ImageView) firstLayout.findViewById(R.id.iv_fragment_tabs_main_img);
        catgImg = (ImageView) firstLayout.findViewById(R.id.iv_fragment_tabs_catg_img);
        starImg = (ImageView) firstLayout.findViewById(R.id.iv_fragment_tabs_star_img);
        settingImg = (ImageView) firstLayout.findViewById(R.id.iv_fragment_tabs_setting_img);
        /* 初始化tab布局对象 */
        mainLayout = firstLayout.findViewById(R.id.rl_fragment_tabs_main_layout);
        catgLayout = firstLayout.findViewById(R.id.rl_fragment_tabs_catg_layout);
        starLayout = firstLayout.findViewById(R.id.rl_fragment_tabs_star_layout);
        settingLayout = firstLayout.findViewById(R.id.rl_fragment_tabs_setting_layout);
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
                /* 改变主要页面 */
                setFragment(transaction, "mainFragment");
                /* 改变actionbar */
                setActionBar(0);
                break;
            case 1:
                catgImg.setImageResource(R.drawable.catg_selected);
                setFragment(transaction, "catgFragment");
                setActionBar(1);
                break;
            case 2:
                starImg.setImageResource(R.drawable.follow_selected);
                setFragment(transaction, "followFragment");
                setActionBar(2);
                break;
            case 3:
                settingImg.setImageResource(R.drawable.submit_selected);
                setFragment(transaction, "settingFragment");
                setActionBar(3);
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
        mainImg.setImageResource(main);
        catgImg.setImageResource(catg);
        starImg.setImageResource(R.drawable.follow);
        settingImg.setImageResource(R.drawable.submit);
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
        if(followFragment != null) {
            transaction.hide(followFragment);
        }
        if(submitFragment != null) {
            transaction.hide(submitFragment);
        }
    }

    /**
     * 改变当前显示的页面
     * @param transaction
     * @param fragmentName
     */
    private void setFragment(FragmentTransaction transaction, String fragmentName) {
        switch(fragmentName) {
            case "mainFragment":
                if(mainFragment == null) {
                    mainFragment = new MainFragment();
                /* activity_main可以找到一个用于显示界面的FrameLayout */
                    transaction.add(R.id.fl_fragment_home_content, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case "catgFragment":
                if(catgFragment == null) {
                    catgFragment = new CatgFragment();
                    transaction.add(R.id.fl_fragment_home_content, catgFragment);
                } else {
                    transaction.show(catgFragment);
                }
                break;
            case "followFragment":
                if(followFragment == null) {
                    followFragment = new FollowFragment();
                    transaction.add(R.id.fl_fragment_home_content, followFragment);
                } else {
                    transaction.show(followFragment);
                }
                break;
            case "settingFragment":
                if(submitFragment == null) {
                    submitFragment = new SubmitFragment();
                    transaction.add(R.id.fl_fragment_home_content, submitFragment);
                } else {
                    transaction.show(submitFragment);
                }
                break;
            default:
                Log.d(TAG, "Fragment unexists");
                break;
        }
    }

    @Override
    public void onClick(View v) {
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
            default:
                break;
        }
    }

    public void updateDrawer(boolean open) {
        if(open) {
            if(!userMenuLayout.isDrawerVisible(GravityCompat.START)) {
                userMenuLayout.openDrawer(GravityCompat.START);
            }
        } else {
            if(userMenuLayout.isDrawerVisible(GravityCompat.START)) {
                userMenuLayout.closeDrawer(GravityCompat.START);
            }
        }
    }

}
