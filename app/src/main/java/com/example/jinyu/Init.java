package com.example.jinyu;

import android.content.Context;

import com.example.jinyu.Database.GreenDaoManager;
import com.example.jinyu.Database.GreenDaoManagerStc;
import com.example.jinyu.Database.Sentence;
import com.example.jinyu.Database.Word;

/**
 * Created by yyx on 2017/8/19.
 */

public class Init {
    private static final String dbname = "jsjm.db";
    private static final String dbStcName = "jsjm_sentences.db";
    public static GreenDaoManager database;
    public static GreenDaoManagerStc databaseStc;
    //for rt activity
    private static String[] rts = {"http://mp.weixin.qq.com/s/EDcUv9JTPU152CeY025AlA", "http://mp.weixin.qq.com/s/zeyrRgRVRpQ68pGtr1nAGQ", "http://mp.weixin.qq.com/s/phesqgS6Xyb3b4eYKMUtSQ", "http://mp.weixin.qq.com/s/PU9uyk5LGXMJBx1BDusK3w", "https://mp.weixin.qq.com/s/0w5Ouqmgsm4Z9SHxGSYMTw"};
    private static int reqCont = 0;


    Init(Context context){
        database = new GreenDaoManager();
        databaseStc = new GreenDaoManagerStc();
        database.setupDatabase(context,dbname);
        databaseStc.setupDatabase(context,dbStcName);
        try{
            database.getName((long)0);
            databaseStc.getContent((long)0);
        }
        catch (Exception e){
            install_init();
        }
    }

    public static String getRTUrl() {
        return rts[(reqCont++) % rts.length];
    }

    private void install_init(){
        //load db
        long id = 0;
        database.insert(new Word(id++,"鸡","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","1"));
        database.insert(new Word(id++,"麻雀","http://sinacloud.net/jsjmsounds/%E9%BA%BB%E9%9B%802.mp3","2"));
        database.insert(new Word(id++,"麻雀","http://sinacloud.net/jsjmsounds/%E9%BA%BB%E9%9B%80.mp3","2"));


        //load sentence db
        id = 0;
        databaseStc.insert(new Sentence(id++,"这是例句1","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","并不存在的句子分析"));
        databaseStc.insert(new Sentence(id++,"这是例句2","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","依旧是并不存在的句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析句子分析（猜猜会不会换行）"));
        databaseStc.insert(new Sentence(id++,"这是还例句3","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","并不存在的句子分析d"));
        databaseStc.insert(new Sentence(id++,"这是还例句4","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","并不存在的句子分析fd"));
        databaseStc.insert(new Sentence(id++,"这是还例句5","http://sinacloud.net/jsjmsounds/%E9%B8%A1.mp3","并不存在的句子分析dsd"));


    }

    public GreenDaoManager getDb(){
        return database;
    }

    public GreenDaoManagerStc getDatabaseStc(){return databaseStc;}

}
