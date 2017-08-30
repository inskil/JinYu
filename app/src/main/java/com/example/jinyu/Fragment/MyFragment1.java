package com.example.jinyu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.jinyu.Database.GreenDaoManager;
import com.example.jinyu.Database.Word;
import com.example.jinyu.DicAdpList.DicList;
import com.example.jinyu.DicAdpList.Group;
import com.example.jinyu.DicAdpList.Item;
import com.example.jinyu.DicAdpList.MyBaseExpandableListAdapter;
import com.example.jinyu.MainActivity;
import com.example.jinyu.R;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.jinyu.R.id.exlist_dic;

/**
 * Created by Jay on 2015/8/28 0028.
 */

public class MyFragment1 extends Fragment {

    public MyFragment1() {
    }
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private MyFragment1 mContext;
    private ExpandableListView exlist_dic;
    private MyBaseExpandableListAdapter myAdapter = null;

    private String[] cateList = {"1","2"};
    //to be modified

    private static final int MAXDATA = 2; //to be modified

    private void preData(){
        //ArrayList<String>
        GreenDaoManager db = MainActivity.initialer.getDb();

        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        ArrayList<String> nameList;
        for(int i = 0;i < MAXDATA;i ++){
            gData.add(new Group(cateList[i]));
            lData = new ArrayList<Item>();
            nameList = new ArrayList<String>();
            ArrayList<Word> wordlist = db.getList(cateList[i]);
            for(Iterator<Word> it = wordlist.iterator(); it.hasNext();){
                Word word = it.next();
                //check the same item with different pronunciations
                String wordName = word.getName();
                int flag = -1;
                for(int j = 0;j < nameList.size();j ++){
                    String name = nameList.get(j);
                    if(name.equals(wordName)){
                        flag = j;
                        break;
                    }
                }

                if(flag!=-1){
                    Item itemSameName = lData.get(flag);
                    itemSameName.addUrl(word.getUrl());
                }
                else{
                    lData.add(new Item(Integer.valueOf(Long.toString(word.getId())),word.getName(),word.getUrl()));
                    nameList.add(word.getName());
                }
            }
            iData.add(lData);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mContext = MyFragment1.this;
        exlist_dic = (ExpandableListView) findViewById(exlist_dic);


        //数据准备
        preData();
        myAdapter = new MyBaseExpandableListAdapter(gData, iData, mContext);
        exlist_dic.setAdapter(myAdapter);

        View view = inflater.inflate(R.layout.activity_list, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第一个Fragment");
        Log.e("HEHE", "1日狗");
        return view;
    }
}


