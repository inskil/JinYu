package com.example.jinyu;

/**
 * Created by hasee on 2017/8/27.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.jinyu.DicAdpList.DicList;

public class StartActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);

            /* Called when the activity is first created. */
            int SPLASH_DISPLAY_LENGHT = 3000;
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
                    StartActivity.this.startActivity(mainIntent);
                    StartActivity.this.finish();
                }

            }, SPLASH_DISPLAY_LENGHT);
        }

    }


