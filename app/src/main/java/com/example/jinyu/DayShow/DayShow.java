package com.example.jinyu.DayShow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.jinyu.Init;
import com.example.jinyu.MainActivity;
import com.example.jinyu.R;

/**
 * Created by hasee on 2017/8/31.
 * a dayshowview
 */

public class DayShow extends Fragment implements GestureDetector.OnGestureListener {

    private MyWebView wView;
    private Button btn_icon;
    private long exitTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_web, container, false);
        btn_icon = (Button) view.findViewById(R.id.btn_icon);
        wView = (MyWebView) view.findViewById(R.id.wView);

        String url = Init.getDSUrl();
        wView.loadUrl(url);
        //wView.loadUrl("http://mp.weixin.qq.com/s/VdHvCFprTUZYStSgImdGKw");


        //滑动相应布置
        final GestureDetector mGestureDetector = new GestureDetector(
                getActivity(), this);
        MainActivity.MyOnTouchListener myOnTouchListener = new MainActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                boolean result = mGestureDetector.onTouchEvent(ev);
                return result;
            }
        };

        ((MainActivity) getActivity())
                .registerMyOnTouchListener(myOnTouchListener);
        //布局监布置完毕


        wView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //获取屏幕高度，另外因为网页可能进行缩放了，所以需要乘以缩放比例得出的才是实际的尺寸
                Log.e("HEHE", wView.getContentHeight() * wView.getScale() + "");
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                Log.e("HEHE", "Cookies = " + CookieStr);
                super.onPageFinished(view, url);

            }
        });

        btn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wView.setScrollY(0);
                btn_icon.setVisibility(View.GONE);
            }
        });

        WebSettings settings = wView.getSettings();
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);//设定支持缩放
        settings.setJavaScriptEnabled(true);//支持js工具（微信公众号必备）
        settings.setBlockNetworkImage(false);//图片必加载


/*      缓存处理，暂时没写，留后处理
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        */

        CookieManager cm = CookieManager.getInstance();


        return view;
    }


    //滑动响应设置
    public void flingLeft() {
        //自定义方法：处理向左滑动事件
        Log.d("flingLeft()", "相左滑动");
        String url = Init.getRTUrl();
        wView.loadUrl(url);

    }

    public void flingRight() {
        //自定义方法：处理向右滑动事件
        Log.d("flingright", "向右滑动");
        String url = Init.getRTUrl();
        wView.loadUrl(url);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Log.d("Fling", "onFling: 左右划成功");
        float wid = wView.getWidth() / 3.0f;
        float hig = wView.getHeight() / 5.0f;
        if (e1.getY() - e2.getY() < hig) {
            try {
                if (e1.getX() - e2.getX() < -wid) {
                    flingLeft();
                    return true;
                } else if (e1.getX() - e2.getX() > wid) {
                    flingRight();
                    return true;
                }
            } catch (Exception e) {

            }

        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float dX,
                            float dY) {
        if (dY > 0) {
            btn_icon.setVisibility(View.VISIBLE);
            Log.d("Dayshow", "onScroll:滑动 dx:" + dX + "dy=" + dY);
        } else {
            btn_icon.setVisibility(View.GONE);
        }

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

}