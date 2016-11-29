package com.panda.teller.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.panda.teller.R;
import com.panda.teller.models.Video;

/**
 * 视频单元的布局
 */

public class VideoItemLayout extends RelativeLayout {

    /* R.layout.video_item */
    View container;

    /* video bean */
    Video video;

    ImageView videoCover;
    TextView numberOfLike;
    TextView numberOfComment;
    TextView videoName;

    public VideoItemLayout(Context context) {
        super(context);
        container = LayoutInflater.from(context).inflate(R.layout.video_item, this);
        initView();
    }

    public VideoItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        container = LayoutInflater.from(context).inflate(R.layout.video_item, this);
        initView();
    }

    private void initView() {
        videoCover = (ImageView)container.findViewById(R.id.img_video_item_image);
        numberOfLike = (TextView)container.findViewById(R.id.textview_video_item_like);
        numberOfComment = (TextView)container.findViewById(R.id.textview_video_item_comment);
        videoName = (TextView)container.findViewById(R.id.textview_video_item_name);

        videoCover.setImageResource(R.drawable.two);
        videoName.setText("12345");
        numberOfLike.setText("12345");
        numberOfComment.setText("12345");
    }

    public void setVideoItem(Video video) {
        this.video = video;
    }

}
