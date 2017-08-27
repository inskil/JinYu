package com.example.jinyu.SentenceAct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jinyu.Database.GreenDaoManagerStc;
import com.example.jinyu.Database.Sentence;
import com.example.jinyu.MainActivity;
import com.example.jinyu.R;

import java.util.ArrayList;

public class SentenceAnal extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Sentence> mList;
    private static final int sentNum = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_anal);

        initData();
        // 获取RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        myAdapter = new MyAdapter(this, mList);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

    }

    private void initData(){
        GreenDaoManagerStc database = MainActivity.initialer.getDatabaseStc();
        mList = new ArrayList<Sentence>();
        for(long id = 0;id < sentNum;id ++){
            try{
                mList.add(database.getEntity(id));
            }
            catch (Exception e){
                Log.e("initdata error",e.toString());
                return;
            }
        }
    }
}
