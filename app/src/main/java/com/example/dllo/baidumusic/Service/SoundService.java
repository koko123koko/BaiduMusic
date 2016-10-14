package com.example.dllo.baidumusic.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.dllo.baidumusic.Bus.InfoBeanEvent;
import com.example.dllo.baidumusic.Fragment.LibsFragment.Song.SongList.SongInfoBean;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.recevie.NotificatoinBrocastReceiver;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/10/12.
 */
public class SoundService extends Service implements SoundServiceInterface {

    private MediaPlayer mediaPlayer;
    private List<SongInfoBean> songInfoBeanList;
    private NotificatoinBrocastReceiver mNotificatoinBrocastReceiver;

    private NotificationManager mNotificationManager;
    private Notification mNotification;

    public final static int PAUSED = 0;
    public final static int PLAYING = 1;
    private int state = PLAYING;
    private RemoteViews mRemoteViews;
    private SoundServiceBinder iBinder;
    private InfoBeanEvent event;
    private int mPosition;

    public int getState() {
        return state;
    }

    public List<SongInfoBean> getSongInfoBeanList() {
        return songInfoBeanList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        registerBrocastReceiver();

    }

    public synchronized void playNext() {
        if ((mPosition + 1) == songInfoBeanList.size()) {
            mPosition = 0;
        } else {
            mPosition++;
        }

        playFetched(songInfoBeanList.get(mPosition).getBitrate().getFile_link());


    }

    public int changeState() {

        switch (state) {
            case PAUSED:

                play();
                state = PLAYING;
                break;
            case PLAYING:
                pause();
                state = PAUSED;
                break;
        }
        return state;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean playing = intent.getBooleanExtra("playing", false);
        mPosition = intent.getIntExtra("position", 0);
        songInfoBeanList = intent.getParcelableArrayListExtra("songInfo");
        //        if(iBinder != null){
        //            iBinder.setArrayListBean(songInfoBeanList);
        //        }

        event = new InfoBeanEvent();
        event.setSongInfoBeanList(songInfoBeanList);
        event.setPosition(mPosition);
        EventBus.getDefault().post(event);

        String url = songInfoBeanList.get(mPosition).getBitrate().getFile_link();
        //        String url = intent.getStringExtra("url");

        Log.d("SoundService", url);
        if (playing) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(this, Uri.parse(url));
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //
        } else {
            mediaPlayer.pause();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mp.isPlaying() == false) {
                    Log.d("SoundService", "音乐播放完毕");
                    playNext();
                }

            }


        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                iBinder.SetBean(songInfoBeanList.get(mPosition));
                iBinder.setMusicArtist(songInfoBeanList.get(mPosition).getSonginfo().getAuthor());
                iBinder.setArrayListBean(songInfoBeanList);
                iBinder.setMusicTitle(songInfoBeanList.get(mPosition).getSonginfo().getTitle());
                iBinder.setMusicPic(songInfoBeanList.get(mPosition).getSonginfo().getPic_small());
                iBinder.setCurrentPosition(mPosition);

                play();

                showBrocast();

            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        stopSelf();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //        return new SoundServiseBinder();
        iBinder = new SoundServiceBinder(this, this);
        return iBinder;
    }

    @Override
    public void addMusicToQueue(SongInfoBean bean) {
        if (songInfoBeanList == null) {
            songInfoBeanList = new ArrayList<>();
        }
        songInfoBeanList.add(bean);
    }

    @Override
    public void addMusicToQueue(List<SongInfoBean> songs) {
        if (songInfoBeanList == null) {
            songInfoBeanList = new ArrayList<>();
        }
        songInfoBeanList.addAll(songs);
    }

    @Override
    public void removeMusicFromQueue(SongInfoBean bean) {
        songInfoBeanList.remove(bean);
    }

    @Override
    public void removeMusicFromQueue(List<SongInfoBean> songs) {
        songInfoBeanList.removeAll(songs);
    }

    @Override
    public void skipToPoint(int point) {
        mediaPlayer.seekTo(point);
    }



    @Override
    public void play(int position) {
        this.mPosition = position;
        playFetched(songInfoBeanList.get(mPosition).getBitrate().getFile_link());

    }

    private void playFetched(String file_link) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, Uri.parse(file_link));
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void play() {
        mediaPlayer.start();


        if (iBinder != null) {
            iBinder.play();


        }
    }

    @Override
    public void pause() {
        mediaPlayer.pause();

        if (iBinder != null) {
            iBinder.paused();
        }
    }

    public boolean registerBrocastReceiver() {

        mNotificatoinBrocastReceiver = new NotificatoinBrocastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAY_NEXT);
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAY_PAUSE);
        intentFilter.addAction(NotificatoinBrocastReceiver.PLAYER_CANCEL);

        registerReceiver(mNotificatoinBrocastReceiver, intentFilter);
        mNotificatoinBrocastReceiver.registerMusicPlayerService(SoundService.this);

        return true;
    }

    public void showBrocast() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("Playing")
                .setAutoCancel(true);
        //        mNotification.flags |= Notification.FLAG_NO_CLEAR;
        //
        RemoteViews remoteViews = getExpandView();
        builder.setContent(remoteViews);

        mNotification = builder.build();
        //        mNotificationManager.notify(1,mNotification);

        startForeground(1, mNotification);

    }

    public RemoteViews getExpandView() {

        mRemoteViews = new RemoteViews(getPackageName(), R.layout.item_notification_view);

        String pic = songInfoBeanList.get(mPosition).getSonginfo().getPic_small();

        AsyncTask asyncTask = new AsyncTask<String, Bitmap, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {

                return ImageLoader.getInstance().loadImageSync(params[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                mRemoteViews.setImageViewBitmap(R.id.iv_notification, bitmap);
                mNotificationManager.notify(1, mNotification);

            }


        }.execute(pic);


        mRemoteViews.setTextViewText(R.id.tv_song_notification, songInfoBeanList.get(mPosition).getSonginfo().getTitle());

        mRemoteViews.setTextViewText(R.id.tv_author_notification, songInfoBeanList.get(mPosition).getSonginfo().getAuthor());

        if (state == PLAYING) {
            mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_pause);

        } else {
            mRemoteViews.setImageViewResource(R.id.ib_pause, R.mipmap.bt_notificationbar_play);

        }
        Intent playIntent = new Intent(NotificatoinBrocastReceiver.PLAY_PAUSE);
        PendingIntent pIntentPlay = PendingIntent.getBroadcast(getApplicationContext(), 0, playIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_pause, pIntentPlay);


        Intent nextIntent = new Intent(NotificatoinBrocastReceiver.PLAY_NEXT);
        PendingIntent pIntentNext = PendingIntent.getBroadcast(getApplicationContext(), 0, nextIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_next, pIntentNext);

        Intent cancelIntent = new Intent(NotificatoinBrocastReceiver.PLAYER_CANCEL);
        PendingIntent pIntentCancel = PendingIntent.getBroadcast(getApplicationContext(), 0, cancelIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_close, pIntentCancel);


        return mRemoteViews;


    }

}
