package com.example.jinyu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jinyu.SentenceAct.SentenceAnal;

public class MainActivity extends AppCompatActivity {
    public static Init initialer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialer = new Init(this);

        TextView tv = (TextView)findViewById(R.id.click_tx);
        tv.setOnClickListener(new View.OnClickListener()

        {

            public void onClick(View v)

            {
                Intent intent = new Intent(MainActivity.this,SentenceAnal.class);
                startActivity(intent);
            }
        });

    }






}