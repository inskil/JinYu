package com.example.jinyu;

/**
 * Created by hasee on 2017/8/27.
 * this is a welcome view
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

public class StartActivity extends Activity {
    public static Init initialer;
    /** Called when the activity is first created. */
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
    private long stTime ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        stTime = System.currentTimeMillis();

        initialer = new Init(this.getApplicationContext(),this);

    }

    private void startMain(){
        Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
        startActivity(mainIntent);
        StartActivity.this.finish();
    }

    public void newAct(){
        long newActTime = System.currentTimeMillis();
        long inter = newActTime-stTime;
        //start view stay no less than SPLASH_DISPLAY_LENGHT
        if(inter<SPLASH_DISPLAY_LENGHT){
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    startMain();
                }
            }, SPLASH_DISPLAY_LENGHT/*-inter*/);
        }
        else{
            startMain();
        }
    }
}


