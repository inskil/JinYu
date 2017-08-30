package com.example.jinyu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity  {
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
                Intent intent = new Intent(MainActivity.this,Main2.class);
                startActivity(intent);
            }
        });


}