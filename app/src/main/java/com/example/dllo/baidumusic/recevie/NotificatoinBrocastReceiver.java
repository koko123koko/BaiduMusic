package com.example.dllo.baidumusic.recevie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.dllo.baidumusic.Service.SoundService;

/**
 * Created by dllo on 16/10/13.
 */
public class NotificatoinBrocastReceiver extends BroadcastReceiver {


    public static final String PLAY_PREVIOUS = "PLAY_PREVIOUS";
    public static final String PLAY_NEXT = "PLAY_NEXT";
    public static final String PLAY_PAUSE = "PLAY_PAUSE";
    public static final String PLAYER_CANCEL = "PLAYER_CANCEL";
    public static final String JUMP_TO_MUSIC_PLAYER = "JUMP TO MUSIC PLAYER";

    private SoundService soundService;

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getAction();


        if (type == null){
            Log.d("NotificatoinBrocastRece", "action is null!");
            return;
        }
        if (soundService == null){
            Log.d("NotificatoinBrocastRece", "sundService is null");
            return;
        }
        if (type.equals(PLAY_NEXT)){
            soundService.playNext();
        } else if(type.equals(PLAY_PAUSE)){
            soundService.changeState();

        } else if(type.equals(PLAYER_CANCEL)){

            if(soundService.getState() == SoundService.PLAYING){
                soundService.changeState();

            }
            soundService.stopForeground(true);
        }


    }

    public void registerMusicPlayerService(SoundService soundService){
        if (soundService != null){
            this.soundService = soundService;
        }
    }
}
