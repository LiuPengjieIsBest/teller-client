package com.panda.teller.views.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.panda.teller.R;

import java.io.IOException;

/**
 * Created by root on 16-11-27.
 */

public class ImgViewLayout extends RelativeLayout {

    private ImageView img;

    private View layout;

    public ImgViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        layout = LayoutInflater.from(context).inflate(R.layout.img_item, this);
        this.img = (ImageView) findViewById(R.id.iv_img_item);
    }

    public ImgViewLayout(Context context) {
        this(context, null);
    }

    /* 使用资源Uri设置图片 */
    public void setContentUri(Uri uri) {
        try {
            /* 将照片解析成Bitmap对象，用于显示到ImageView中 */
            Bitmap bitmap = BitmapFactory.decodeStream(
                getContext().getContentResolver().openInputStream(uri));
            /* 显示照片 */
            img.setImageBitmap(bitmap);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /* 使用drawable中的资源设置图片 */
    public void setContentRes(int resId) {
        img.setImageResource(resId);
    }

    /* 设置图片的尺寸 */
    public void setImgSize(int width, int height) {
        ViewGroup.LayoutParams para = img.getLayoutParams();
        para.width = width;
        para.height = height;
        img.setLayoutParams(para);
    }

    /* 设置整个布局的尺寸 */
    public void setSize(int width, int height) {
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(width, height);
        para.width = width;
        para.height = height;
        setLayoutParams(para);
    }
}
