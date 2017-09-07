package com.example.jinyu;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.jinyu.DayShow.DayShow;
import com.example.jinyu.DicAdpList.DicList;
import com.example.jinyu.SentenceAct.SentenceAnal;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    BottomNavigationBar mBottomNavigationBar;
    FrameLayout mFrameLayout;

    private SentenceAnal stcFrag;
    private DicList dicFrag;
    private DayShow dsFrag;
    // menu setting
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(
            10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                .addItem(new BottomNavigationItem(R.drawable.tab_menu_message, "日推").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.tab_menu_channel, "词典").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.tab_menu_better, "句子").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(1)
                .initialise();
    }

    public void onTabSelected(int position) {
        Log.d("onTabSelected", "onTabSelected: " + position);
        switch (position) {
            case 0:
                if (dsFrag== null) {
                    dsFrag = new DayShow();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dsFrag).commit();
                break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //菜单图标显示（反射原理）
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
// menu setting end

//fragment 滑动监听接口

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                builder = new AlertDialog.Builder(MainActivity.this);
                final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view_custom = inflater.inflate(R.layout.menu_about, null);
                builder.setView(view_custom);
                builder.setCancelable(false);
                alert = builder.create();
                view_custom.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });

                view_custom.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "欢迎关注公众号", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                alert.show();
                break;
            case R.id.menu_setting:

                Toast.makeText(getApplicationContext(), "暂无设置，请持续关注晋善晋美", Toast.LENGTH_LONG).show();
                break;
        }
        Log.d("menu:", "onOptionsItemSelected: " + item);
        //Toast.makeText(MainActivity.this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            listener.onTouch(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }

    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }
//fragment 滑动监听接口  end

}
