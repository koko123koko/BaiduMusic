package com.example.dllo.baidumusic.mutil;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/16.
 */
public class CommonUtils  {
    private static final String LOG_TAG = CommonUtils.class.getSimpleName();

    /**
     * ms 转换为 mm:ss 格式的字符串.
     * @param ms
     * @return
     */
    public static String timeFormatMs2Str(int ms){
        String str;
        int minutes = (ms / 1000) / 60, seconds = (ms / 1000) % 60;
        if (minutes >= 10 && seconds >= 10) {
            str = "" + minutes + ":" + seconds;
        } else if (minutes >= 10 && seconds < 10) {
            str = "" + minutes + ":0" + seconds;
        } else if (minutes < 10 && seconds >= 10) {
            str = "0" + minutes + ":" + seconds;
        } else {
            str = "0" + minutes + ":0" + seconds;
        }
        return str;
    }

    /**
     * 判断播放音乐服务是否在工作
     * @param className
     * @param context
     * @return
     */
    public static boolean isServiceWorked(String className, Context context){
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningServiceInfo = (ArrayList<ActivityManager.RunningServiceInfo>) manager.getRunningServices(30);
        for (int i = 0; i < runningServiceInfo.size(); i++){
            if (runningServiceInfo.get(i).service.getClassName().equals(className)) {
                Log.d("CommonUtils", runningServiceInfo.get(i).service.getClassName());
                return true;
            }

        }
        return false;
    }
}
