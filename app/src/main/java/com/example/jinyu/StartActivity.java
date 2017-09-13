package com.example.jinyu;

/**
 * Created by hasee on 2017/8/27.
 * this is a welcome view
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class StartActivity extends Activity {
    public static Init initialer;
    /** Called when the activity is first created. */
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialer = new Init(this.getApplicationContext(),this);

        Log.d("欢迎界面", "欢迎界面部署完毕");
            /*
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(mainIntent);
                StartActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);      */

    }

    public void newAct(){


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(mainIntent);
                StartActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
        // Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
        //  startActivity(mainIntent);
    }
}


