package com.example.jinyu.SentenceAct;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jinyu.Database.GreenDaoManagerStc;
import com.example.jinyu.Database.Sentence;
import com.example.jinyu.Init;
import com.example.jinyu.R;

import java.util.ArrayList;

public class SentenceAnal extends Fragment {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Sentence> mList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sentence_anal, container,false);

        initData();
        // 获取RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        // 设置LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        // 初始化自定义的适配器
        myAdapter = new MyAdapter(getContext(), mList);
        // 为mRecyclerView设置适配器
        mRecyclerView.setAdapter(myAdapter);

        return view;

    }

    private void initData(){
        GreenDaoManagerStc database = new GreenDaoManagerStc();
        database.setupDatabase(getContext(),Init.dbStcName);
        mList = new ArrayList<Sentence>();
        for(long id = 0; ; id ++){
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
