package com.star.wanandroid.core.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.util.Property;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class HistoryDataDao extends AbstractDao<HistoryData, Void> {

    public static final String TABLENAME = "HISTORY_DATA";

    /**
     * Properties of entity HistoryData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Date = new Property(0, long.class, "date", false, "DATE");
        public final static Property Data = new Property(1, String.class, "data", false, "DATA");
    }


    public HistoryDataDao(DaoConfig config) {
        super(config);
    }

    public HistoryDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORY_DATA\" (" + //
                "\"DATE\" INTEGER NOT NULL ," + // 0: date
                "\"DATA\" TEXT);"); // 1: data
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORY_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistoryData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getDate());

        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistoryData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getDate());

        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }

    @Override
    public HistoryData readEntity(Cursor cursor, int offset) {
        HistoryData entity = new HistoryData( //
                cursor.getLong(offset + 0), // date
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // data
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, HistoryData entity, int offset) {
        entity.setDate(cursor.getLong(offset + 0));
        entity.setData(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
    }

    @Override
    protected final Void updateKeyAfterInsert(HistoryData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }

    @Override
    public Void getKey(HistoryData entity) {
        return null;
    }

    @Override
    public boolean hasKey(HistoryData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
