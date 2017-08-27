package com.example.jinyu.DicAdpList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.jinyu.Database.GreenDaoManager;
import com.example.jinyu.Database.Word;
import com.example.jinyu.MainActivity;
import com.example.jinyu.R;

import java.util.ArrayList;
import java.util.Iterator;

public class DicList extends AppCompatActivity {

    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
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
            for(Iterator<Word> it = wordlist.iterator();it.hasNext();){
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mContext = DicList.this;
        exlist_dic = (ExpandableListView) findViewById(R.id.exlist_dic);


        //数据准备
        preData();
        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_dic.setAdapter(myAdapter);


    }

}
