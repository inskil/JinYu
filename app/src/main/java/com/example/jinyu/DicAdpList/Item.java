package com.example.jinyu.DicAdpList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by yyx on 2017/8/20.
 */

public class Item {
    private int iId;
    private String iName;
    private ArrayList<String> urlList;

    public Item() {
    }

    public Item(int iId, String iName,String url) {
        this.iId = iId;
        this.iName = iName;
        this.urlList = new ArrayList<String>();
        this.urlList.add(url);
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public void addUrl(String url){
        urlList.add(url);
    }

    public ArrayList<String>  getUrlList(){
        ArrayList<String> list = new ArrayList<String>();
        for(Iterator<String> it = urlList.iterator();it.hasNext();){
            list.add(it.next());
        }
        return list;
    }
}
