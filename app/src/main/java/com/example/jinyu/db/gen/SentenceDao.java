package com.example.jinyu.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.jinyu.Database.Sentence;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SENTENCE".
*/
public class SentenceDao extends AbstractDao<Sentence, Long> {

    public static final String TABLENAME = "SENTENCE";

    /**
     * Properties of entity Sentence.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Sound_url = new Property(2, String.class, "sound_url", false, "SOUND_URL");
        public final static Property Analysis = new Property(3, String.class, "analysis", false, "ANALYSIS");
    }


    public SentenceDao(DaoConfig config) {
        super(config);
    }
    
    public SentenceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SENTENCE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONTENT\" TEXT," + // 1: content
                "\"SOUND_URL\" TEXT," + // 2: sound_url
                "\"ANALYSIS\" TEXT);"); // 3: analysis
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SENTENCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Sentence entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
 
        String sound_url = entity.getSound_url();
        if (sound_url != null) {
            stmt.bindString(3, sound_url);
        }
 
        String analysis = entity.getAnalysis();
        if (analysis != null) {
            stmt.bindString(4, analysis);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Sentence entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
 
        String sound_url = entity.getSound_url();
        if (sound_url != null) {
            stmt.bindString(3, sound_url);
        }
 
        String analysis = entity.getAnalysis();
        if (analysis != null) {
            stmt.bindString(4, analysis);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Sentence readEntity(Cursor cursor, int offset) {
        Sentence entity = new Sentence( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sound_url
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // analysis
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Sentence entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSound_url(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAnalysis(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Sentence entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Sentence entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Sentence entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}