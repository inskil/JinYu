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
    /** Called when the activity is first created. */
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
    public static Init initialer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialer = new Init(this);

        Log.d("欢迎界面加载","这是欢迎界面");

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(mainIntent);
                StartActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }

}


