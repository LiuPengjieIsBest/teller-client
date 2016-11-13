package com.panda.teller.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.panda.teller.R;
import com.panda.teller.models.Video;

/**
 * 视频单元的布局
 */

public class VideoItemLayout extends RelativeLayout {

    Video video;

    public VideoItemLayout(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.video_item, this);
    }

    public VideoItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.video_item, this);
    }

    public void setVideoItem(Video video) {
        this.video = video;
    }

}
