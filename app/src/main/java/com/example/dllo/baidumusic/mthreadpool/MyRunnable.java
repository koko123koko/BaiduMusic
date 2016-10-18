package com.example.dllo.baidumusic.mthreadpool;

import android.os.Handler;
import android.os.Message;

import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by dllo on 16/10/12.
 */
public class MyRunnable implements Runnable{

    private String url;
    private Handler handler;

    private SongInfoBean songInfoBean;

    private int i;

    public MyRunnable(String url, Handler handler,int i) {
        this.url = url;
        this.handler = handler;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String line = "";
                String str = new String();
                while ((str = br.readLine()) != null) {
                    line += str;
                }

                br.close();
                isr.close();
                is.close();

                line = line.substring(1, line.length() - 2);
                Gson gson = new Gson();
                songInfoBean = gson.fromJson(line, SongInfoBean.class);

                Message message = new Message();
                message.what = 101;
                message.arg1 = i;
//                message.arg2 = i;
                message.obj = songInfoBean;
                handler.sendMessage(message);

                //                    MediaPlayer mediaPlayer = new MediaPlayer();
                //                    Log.d("AsynUrl", songInfoBean.getBitrate().getFile_link());
                //                    mediaPlayer.setDataSource(mContext, Uri.parse("http://yinyueshiting.baidu.com/data2/music/64334230/64334230.mp3?xcode=cfcd9b5c0d02774359dc68219d22c373"));
                //                    mediaPlayer.prepare();

            }
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
