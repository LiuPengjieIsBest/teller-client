package com.panda.teller.activities;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by zhouke on 2016/11/5.
 *
 */

public class ViewPagerAdapter extends PagerAdapter
{
    private List<View> views;//引导页面的集合
    private Context context;

    public ViewPagerAdapter(List<View> views,Context context)
    {
        this.context=context;
        this.views=views;
    }

    @Override
    public void destroyItem(View container, int position, Object object)//当views不需要的时候进行销毁
    {
        ((ViewPager)container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position)//加载view的方法
    {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount()//返回当前views的数量
    {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) //判断当前的view是不是需要的对象
    {
        return (view==object);
    }
}
