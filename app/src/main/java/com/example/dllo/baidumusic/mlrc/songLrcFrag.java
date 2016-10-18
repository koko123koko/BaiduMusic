package com.example.dllo.baidumusic.mlrc;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dllo.baidumusic.basefrag.BaseFragment;
import com.example.dllo.baidumusic.mfragment.mlibsfrag.song.songlist.SongInfoBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.div.view.ILrcBuilder;
import com.example.dllo.baidumusic.div.view.ILrcView;
import com.example.dllo.baidumusic.div.view.ILrcViewListener;
import com.example.dllo.baidumusic.div.view.impl.DefaultLrcBuilder;
import com.example.dllo.baidumusic.div.view.impl.LrcRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/10/15.
 */
public class songLrcFrag extends BaseFragment {

    //自定义LrcView，用来展示歌词
    ILrcView mLrcView;
    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;

    private SongInfoBean currentSong;

    public void setCurrentSong(SongInfoBean currentSong) {
        this.currentSong = currentSong;
    }

    @Override
    protected void initData() {

        //从assets目录下读取歌词文件内容
        //        String lrc = getFromAssets(currentSong.getSonginfo().getLrclink());
        String lrcUrl = currentSong.getSonginfo().getLrclink();
        new AsynUrl().execute(currentSong.getSonginfo().getLrclink());

        //开始播放歌曲并同步展示歌词
        //        beginLrcPlay();

        //设置自定义的LrcView上下拖动歌词时监听
        mLrcView.setListener(new ILrcViewListener() {
            //当歌词被用户上下拖动的时候回调该方法,从高亮的那一句歌词开始播放
            public void onLrcSeeked(int newPosition, LrcRow row) {
                if (mPlayer != null) {
                    //                    Log.d(TAG, "onLrcSeeked:" + row.time);
                    mPlayer.seekTo((int) row.time);
                }
            }
        });

    }

    @Override
    protected void initVIew() {
        mLrcView = bindView(R.id.lrcview_lrc_item);
    }

    @Override
    protected int setLayout() {
        return R.layout.lrc_item;
    }

    class AsynUrl extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = connection.getInputStream();
                    InputStreamReader inputReader = new InputStreamReader(is);
                    BufferedReader bufReader = new BufferedReader(inputReader);
                    String line = "";

                    while ((line = bufReader.readLine()) != null) {
                        if (line.trim().equals(""))
                            continue;
                        result += line + "\r\n";
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("songLrcFrag", s);
            //解析歌词构造器
            ILrcBuilder builder = new DefaultLrcBuilder();
            //解析歌词返回LrcRow集合
            List<LrcRow> rows = builder.getLrcRows(s);
            //将得到的歌词集合传给mLrcView用来展示
            mLrcView.setLrc(rows);

        }
    }

    /**
     * 从assets目录下读取歌词文件内容
     *
     * @param urlPath
     * @return
     */

    MediaPlayer mPlayer;
    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(currentSong.getBitrate().getFile_link());
            //准备播放歌曲监听
            //            mPlayer.setOnPreparedListener(new OnPreparedListener() {
            //                //准备完毕
            //                public void onPrepared(MediaPlayer mp) {
            //                    mp.start();
            //                    if(mTimer == null){
            //                        mTimer = new Timer();
            //                        mTask = new LrcTask();
            //                        mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
            //                    }
            //                }
            //            });
            //            //歌曲播放完毕监听
            //            mPlayer.setOnCompletionListener(new OnCompletionListener() {
            //                public void onCompletion(MediaPlayer mp) {
            //                    stopLrcPlay();
            //                }
            //            });
            //准备播放歌曲
            mPlayer.prepare();
            //开始播放歌曲
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
