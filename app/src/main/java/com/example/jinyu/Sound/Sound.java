package com.example.jinyu.Sound;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.jinyu.Database.GreenDaoManager;

/**
 * Created by yyx on 2017/8/12.
 */

public class Sound {
    public static void playSound(String url){
        MediaPlayer mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        }
        catch (Exception e){
            Log.e("play","play error");
        }

    }

}
