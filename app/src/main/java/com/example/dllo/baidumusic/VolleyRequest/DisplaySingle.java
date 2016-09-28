package com.example.dllo.baidumusic.VolleyRequest;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.dllo.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by dllo on 16/9/28.
 */
public class DisplaySingle {

    private static DisplaySingle displaySingle;

    private DisplaySingle() {
    }

    public static DisplaySingle getInstance() {
        if (displaySingle == null) {
            synchronized (DisplaySingle.class) {
                if (displaySingle == null) {
                    displaySingle = new DisplaySingle();
                }
            }
        }
        return displaySingle;
    }

    public void show(String uri, ImageView imageView) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.default_album_bg)
                .showImageOnLoading(R.mipmap.default_album_bg)
                .showImageOnFail(R.mipmap.default_album_bg)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        ImageLoader.getInstance().displayImage(uri, imageView);
    }

}