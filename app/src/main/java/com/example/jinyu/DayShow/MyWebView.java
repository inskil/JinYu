package com.example.jinyu.DayShow;

/**
 * Created by inskil on 2017/8/31.
 * a webview
 */

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class MyWebView extends WebView {

    // private OnScrollChangedCallback mOnScrollChangedCallback;


    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*      基本没啥用的部分了其实这个MYWEBVIRW就没啥用
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        Log.d("MYwebview", "onScrollChanged: 滑动执行");
        if (mOnScrollChangedCallback != null) {
            Log.d("if onscroll is ture", "onScrollChanged:x "+x+"y"+y+"x"+oldx+"y"+oldy);
            mOnScrollChangedCallback.onScroll(x - oldx, y - oldy);
        }
    }


    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public interface OnScrollChangedCallback {
        //这里的dx和dy代表的是x轴和y轴上的偏移量，你也可以自己把l, t, oldl, oldt四个参数暴露出来
        void onScroll(int dx, int dy);
    }
*/
}