package com.example.jinyu.SentenceAct;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jinyu.Database.Sentence;
import com.example.jinyu.R;
import com.example.jinyu.Sound.Sound;

import java.util.ArrayList;

/**
 * Created by yyx on 2017/8/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Sentence> mList;

    public MyAdapter(Context context, ArrayList<Sentence> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 给ViewHolder设置布局文件
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        // 给ViewHolder设置元素
        final Sentence sentence = mList.get(i);
        viewHolder.content.setText(sentence.getContent());
        //viewHolder.content.setText("This is the sentence???");
        viewHolder.analysis.setText(sentence.getAnalysis());
        viewHolder.cardview.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)

            {
                Sound.playSound(sentence.getSound_url());
            }
        });
    }
    @Override
    public int getItemCount() {
        // 返回数据总数
        return mList == null ? 0 : mList.size();
    }

    // 重写的自定义ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView content,analysis;
        public CardView cardview;

        public ViewHolder(View v) {
            super(v);
            cardview = (CardView) v.findViewById(R.id.cardview);
            content = (TextView)v.findViewById(R.id.stc_cont);
            analysis = (TextView)v.findViewById(R.id.stc_anal);
            //android:text="this is card text"
        }

    }
}