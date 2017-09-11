package com.example.jinyu;
import android.content.Context;
import android.util.Log;

import com.example.jinyu.Database.GreenDaoManagerStc;
import com.example.jinyu.Database.Sentence;
import com.example.jinyu.Database.Word;
import com.example.jinyu.Download.Download;
import com.example.jinyu.Sound.Sound;

import com.example.jinyu.Database.GreenDaoManager;
import com.example.jinyu.Sound.Sound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by yyx on 2017/8/19.
 */

public class Init {
    public static GreenDaoManager database;
    public static GreenDaoManagerStc databaseStc;
    private static final String dbname = "jsjm.db";
    private static final String dbStcName = "jsjm_sentences.db";
    private static int dbStcSize ;
    private Context sContext;

    Init(Context context){
        sContext = context;
        database = new GreenDaoManager();
        databaseStc = new GreenDaoManagerStc();
        database.setupDatabase(context,dbname);
        databaseStc.setupDatabase(context,dbStcName);
        /*try{
            database.getName((long)0);
            databaseStc.getContent((long)0);
        }
        catch (Exception e){*/
        //for debug
        database.dropAll();
        databaseStc.dropAll();
            install_init();
        //}
    }

    private void install_init(){
        /*try {
            Download.download(sContext, "http://sinacloud.net/jsjmsounds/Test/%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.txt", "新建文本文档.txt");
            File file = new File("/download/新建文本文档.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data = reader.readLine();
            Log.d("init debug",data);
        }
        catch (Exception e){
            Log.d("error download",e.toString());
        }*/
        //load db
        long id = 0;
        database.insert(new Word(id++,"伸手 兀手","http://sinacloud.net/jsjmsounds/Action%26Behavior/Bodymovement/%E4%BC%B8%E6%89%8B%20%E5%85%80%E6%89%8B.mp3","1"));
        database.insert(new Word(id++,"剥开 扒","http://sinacloud.net/jsjmsounds/Action%26Behavior/Bodymovement/%E5%89%A5%E5%BC%80%20%E6%89%92.mp3","2"));
        database.insert(new Word(id++,"谁","http://sinacloud.net/jsjmsounds/Test/%E8%B0%81.mp3","2"));


        //load sentence db
        id = 0;

        dbStcSize = 1;
        databaseStc.insert(new Sentence(id++,"这是例句","http://sinacloud.net/jsjmsounds/Test/%E8%B0%81.mp3","并不存在的句子分析"));


    }



    public GreenDaoManager getDb(){
        return database;
    }

    public GreenDaoManagerStc getDatabaseStc(){return databaseStc;}

    public int getSentenceDbSize(){return dbStcSize;}



    //for rt activity
    private static String[] rts = {""};
    private static int reqCont = 0;
    public static String getRTUrl(){
        return rts[(reqCont++)%rts.length];
    }

}
