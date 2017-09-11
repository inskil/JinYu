package com.example.jinyu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jinyu.db.gen.DaoMaster;
import com.example.jinyu.db.gen.DaoSession;
import com.example.jinyu.db.gen.WordDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yyx on 2017/8/12.
 */

public class GreenDaoManager {
    private DaoSession daoSession;

    /**
     * 配置数据库
     */
    public  void setupDatabase(Context context, String dbName) {
        //创建数据库.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }
    public void dropAll(){
        daoSession.getWordDao().deleteAll();
    }
    public void insert(Word Word){
        daoSession.getWordDao().insert(Word);
    }

    public String getCategory(String name){
        Word Word = daoSession.getWordDao().queryBuilder().where(WordDao.Properties.Name.eq(name)).unique();
        return Word.getCategory();
    }

    public String getUrl(Word word){
        return word.getUrl();
    }
    public ArrayList<Word> getList(String category){
        List list = daoSession.getWordDao().queryBuilder().where(WordDao.Properties.Category.eq(category)).list();
        ArrayList<Word> newlist = new ArrayList<Word>();
        for(Iterator<Word> it = list.iterator();it.hasNext();){
            newlist.add(it.next().clone());
        }
        return newlist;
    }

    public String getUrl(String name){
        //need to modify
        Word word = daoSession.getWordDao().queryBuilder()
                .where(WordDao.Properties.Name.eq(name))
                .unique();
        return word.getUrl();

    }

    public String getName(long id){
        return daoSession.getWordDao().queryBuilder().where(WordDao.Properties.Id.eq(id)).unique().getName();
    }



}