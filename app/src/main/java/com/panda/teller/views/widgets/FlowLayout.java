package com.panda.teller.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-11-12.
 */

public class FlowLayout extends ViewGroup {

    private final static String TAG = "FlowLayout";

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /* 获得它的父容器为它设置的测量模式和大小 */
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG, sizeWidth + "," + sizeHeight);
        /* 如果是warp_content情况下，记录宽和高 */
        int width = 0;
        int height = 0;
        /* 每行的最大宽度/高度 */
        int lineWidth = 0;
        int lineHeight = 0;
        /* 遍历子视图 */
        int cCount = getChildCount();
        for(int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            /* 测量每个child的宽和高 */
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            /* 获得child的lp */
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            /* child实际占据的宽度/高度 */
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            /* 根据加入当前child是否超出最大宽度，更新宽度和高度 */
            if(lineWidth + childWidth > sizeWidth) {
                /* 更新最大宽度 */
                width = Math.max(width, lineWidth);
                height += lineHeight;
                /* 新行的初始宽/高 */
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            /* 如果是最后一个，将最大宽度和当前行宽比较，并添加最后一行的高度 */
            if(i == cCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width,
                (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    /**
     * 绘制所有子视图
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /* 可能要进行多次重绘，排除上一次的影响 */
        mAllViews.clear();
        mLineHeight.clear();
        /* 总宽度 */
        int maxWidth = getWidth();
        /* 每一行的宽/高 */
        int lineWidth = 0;
        int lineHeight = 0;
        /* 存储每一行所有childView */
        List<View> lineViews = new ArrayList<View>();
        /* 遍历绘制所有childView */
        int cCount = getChildCount();
        for(int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            /* 该child的参数 */
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            /* 如果已经需要换行 */
            if(childWidth + lp.leftMargin + lp.rightMargin + lineWidth > maxWidth) {
                /* 记录这一行的最大高度 */
                mLineHeight.add(lineHeight);
                /* 保存当前行的child */
                mAllViews.add(lineViews);
                lineViews = new ArrayList<View>();
                lineWidth = 0;
            }
            /* 将当前child的参数累加进来 */
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        /* 记录最后一行 */
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        /* 开始绘制 */
        int left = 0, top = 0;
        /* 绘制每一层 */
        int lineNums = mAllViews.size();
        for(int i = 0; i < lineNums; i++) {
            /* 当前行的所有child和最大高度 */
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);
            /* 遍历当前行的所有child */
            for(int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                /* 若该child不是不可见的 */
                if(child.getVisibility() != View.GONE) {
                    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                    /* 计算child的位置 */
                    int lc = left + lp.leftMargin;
                    int tc = top + lp.topMargin;
                    int rc = lc + child.getMeasuredWidth();
                    int bc = tc + child.getMeasuredHeight();
                    Log.e(TAG, child + " , l = " + lc + " , t = " + tc + " , r ="
                            + rc + " , b = " + bc);
                    /* 绘制 */
                    child.layout(lc, tc, rc, bc);
                    /* 下一个child的left位置 */
                    left += child.getMeasuredWidth() + lp.rightMargin
                            + lp.leftMargin;
                }
            }
            left = 0;
            top += lineHeight;
        }
    }

}
