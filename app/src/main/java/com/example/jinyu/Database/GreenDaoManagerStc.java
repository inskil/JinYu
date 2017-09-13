package com.example.jinyu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jinyu.db.gen.DaoMaster;
import com.example.jinyu.db.gen.DaoSession;
import com.example.jinyu.db.gen.SentenceDao;

/**
 * Created by yyx on 2017/8/26.
 */

public class GreenDaoManagerStc {
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
        daoSession.getSentenceDao().deleteAll();
    }

    public void insert(Sentence sentence){ daoSession.getSentenceDao().insert(sentence);}

    public String getUrl(Sentence sentence){return sentence.getSound_url();}

    public String getContent(Sentence sentence){ return sentence.getContent();}

    public String getAnalysis(Sentence sentence){return sentence.getAnalysis();}

    public long getId(Sentence sentence){ return sentence.getId();}

    public Sentence getEntity(long id){ return daoSession.getSentenceDao().queryBuilder().where(SentenceDao.Properties.Id.eq(id)).unique().clone();}

    public String getContent(long id){return daoSession.getSentenceDao().queryBuilder().where(SentenceDao.Properties.Id.eq(id)).unique().getContent();}
}
