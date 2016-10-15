package com.example.dllo.baidumusic.VolleyRequest;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/9/20.
 */
public class MyApp extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush


        File cacheDir = StorageUtils.getOwnCacheDirectory(this,"imageLoader/Cache",true);
        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration
                        .Builder(this)
                        .threadPoolSize(3).diskCacheFileCount(100)
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .diskCache(new UnlimitedDiskCache(cacheDir))
                        .memoryCache(new WeakMemoryCache())
                        .build();
        ImageLoader.getInstance().init(configuration);

    }
    public static Context getContext(){
        return context;
    }
}
