package com.example.jinyu.Sound;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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

    public static void playSound(Context context,String url){
        Intent it = new Intent();
        it.setAction(Intent.ACTION_VIEW);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri uri = Uri.parse(url);//此url就是流媒体文件的下载地址
        it.setDataAndType(uri, "audio/*");//type的值是 "video/*"或者 "audio/*"

        context.startActivity(it);
    }
}
