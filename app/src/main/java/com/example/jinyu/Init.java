package com.example.jinyu;
import android.content.Context;
import android.os.Environment;
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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by yyx on 2017/8/19.
 */

public class Init {
    public static GreenDaoManager database;
    public static GreenDaoManagerStc databaseStc;
    public static final String dbname = "jsjm.db";
    public static final String dbStcName = "jsjm_sentences.db";
    private Context sContext;
    private File sdDir;
    private static final String wordSource = "WordSource.txt";
    private static final String sentenceSource = "SentenceSource.txt";
    private static final String rootPath = "/download/";
    private static String root ;
    private static final String urlRoot = "http://sinacloud.net/jsjmsounds/dbdocs/";
    public static ArrayList<String> cateList  = new ArrayList<String> ();
            //= {"代副介连词","动物","动物身体部位","鸟类爬行类","动作行为","文娱消遣","房舍家具","家具器具","建筑","日用品","婚丧嫁娶","经营","农业","文化","物象","人品称谓","身体医疗","时间方位","食品医疗","天文地理","性质状态","植物"};

    private File fileWd,fileSt;

    Init(Context context,StartActivity act){

        sContext = context.getApplicationContext();
        sdDir = Environment.getExternalStorageDirectory();
        root = sdDir + rootPath;

        database = new GreenDaoManager();
        databaseStc = new GreenDaoManagerStc();
        database.setupDatabase(context,dbname);
        databaseStc.setupDatabase(context,dbStcName);
        /*try{

            database.getName((long)0);
            databaseStc.getContent((long)0);
            act.newAct();
        }
        catch (Exception e){
            Log.d("db not ok","");*/
            install_init();
        //}

        fileWd = new File(root+wordSource);
        fileSt = new File(root+sentenceSource);
        while (true){
            if(fileWd.exists()&&fileSt.exists())
                break;
        }
        act.newAct();

    }

    void readin(String filename,boolean ifWord){
        String encoding = ifWord?"GBK":"Unicode";
        String data = null;
        String[] spList;
        String[] buf={"","",""};
        BufferedReader reader ;
        long id = 0;

        try{
            reader =new BufferedReader(new InputStreamReader(new FileInputStream(filename),encoding)) ;
            while((data = reader.readLine())!=null){
                spList = data.split("\t");
                int k = 0;
                for(int i = 0;i <  spList.length;i ++){
                    if(spList[i].equals("")) continue;
                    buf[k++] = spList[i];
                }
                if(ifWord){
                    database.insert(new Word(id++,buf[0],buf[1],buf[2]));
                    if(!cateList.contains(buf[2])) cateList.add(buf[2]);

                }
                else{
                    databaseStc.insert(new Sentence(id++,buf[0],buf[1],buf[2]));
                }
            }
        }
        catch(Exception e){
            Log.d("init error",e.toString());
        }
    }

    public void load_wordDb(){
        try {
            //Download.download(sContext, "http://sinacloud.net/jsjmsounds/Test/%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.txt", "新建文本文档.txt");
            fileWd = new File(root+wordSource);
            Log.d("file wd",Boolean.toString(fileWd.exists()));
            readin(root+wordSource,true);
        }
        catch (Exception e){
            Log.d("word db init error",e.toString());
        }
    }

    public void load_sentenceDb(){
        try {
            //Download.download(sContext, "http://sinacloud.net/jsjmsounds/Test/%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.txt", "新建文本文档.txt");
            fileSt = new File(root+sentenceSource);
            readin(root+sentenceSource,false);
        }
        catch (Exception e){
            Log.d("sentece db init error",e.toString());
        }
    }

    private boolean fileExist(String filename){
        File f = new File(root+filename);
        boolean exist = f.exists();

        if(!exist){
            Download download = new Download();
            try {
                download.download(sContext,urlRoot+filename,filename,this);
            }
            catch (Exception e){
                Log.d("download error",e.toString());
            }
        }
        return exist;
    }
    private void install_init(){
        database.dropAll();
        databaseStc.dropAll();
        Log.d("init","enter");
        if(fileExist(wordSource)){
            load_wordDb();
        }
        if (fileExist(sentenceSource)){
            load_sentenceDb();
        }
    }


    public GreenDaoManager getDb(){
        return database;
    }

    public GreenDaoManagerStc getDatabaseStc(){return databaseStc;}

    public static String[] getCateList(){
        String[] va = new String[cateList.size()];
        for(int i = 0;i < cateList.size();i ++){
            va[i] = cateList.get(i);
        }
        return va;
    }


    public String getWordSource(){ return wordSource;}
    public String getSentenceSource(){return sentenceSource;}
    public String getRootPath(){return rootPath;}

    //for rt activity
    private static String[] rts = {""};
    private static int reqCont = 0;
    public static String getRTUrl(){
        return rts[(reqCont++)%rts.length];
    }



}
