package com.example.jinyu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.jinyu.DicAdpList.DicList;
import com.example.jinyu.SentenceAct.SentenceAnal;

public class Main2 extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar mBottomNavigationBar;
    FrameLayout mFrameLayout;

    private SentenceAnal stcFrag;
    private DicList dicFrag;
    //private DialyPush dpFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mFrameLayout = (FrameLayout)findViewById(R.id.fragment_container);
        InitNavigationBar() ;
        dicFrag = new DicList();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,dicFrag).commit();
    }

    private void InitNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);


        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "日推").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "词典").setActiveColorResource(R.color.green))
                .addItem(new BottomNavigationItem(R.drawable.ic_find_replace_white_24dp, "句子").setActiveColorResource(R.color.red))
                .setFirstSelectedPosition(1)
                .initialise();
    }

    public void onTabSelected(int position) {
        Log.d("onTabSelected", "onTabSelected: " + position);
        switch (position) {
            /*case 0:
                if (dicFrag == null) {
                    dicFrag = new DicList();
                }
                tgetSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dicFrag).commit();
                break;*/
            case 1:
                if (dicFrag == null) {
                    dicFrag = new DicList();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dicFrag).commit();
                break;
            case 2:
                if (stcFrag == null) {
                    stcFrag = new SentenceAnal();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, stcFrag).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d("onTabUnselected", "onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.d("onTabReselected", "onTabReselected: " + position);
    }

}
