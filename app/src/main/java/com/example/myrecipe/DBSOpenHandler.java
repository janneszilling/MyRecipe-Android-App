package com.example.myrecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBSOpenHandler extends SQLiteOpenHelper {
    private static final String TAG = DBSOpenHandler.class.getSimpleName();

    private static DBSOpenHandler instance = null;

    // name and version of the database

    public static void createInstance(Context context, String databasename, int databaseVersion) {
        if (instance==null) {
            instance = new DBSOpenHandler(context, databasename, databaseVersion);
        }
    }

    public static DBSOpenHandler getInstance() {

        return instance;
    }

    public DBSOpenHandler(Context context, String databasename, int databaseVersion) {

        super(context, databasename, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= DBSQueries.getCreateTables();
        Log.i(TAG, "********** String  getCreateTables");
        try {
            db.execSQL(sql);
            Log.i(TAG, "********** database are created (1)");
        } catch (SQLiteException e) {
            Log.i(TAG, "**********                        no database are created", e);
        } finally {
            Log.i(TAG, "********** in finally onCreate");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrade of the database from version "
                + oldVersion + " to "
                + newVersion + "; all datas are deleted");
        String sql= DBSQueries.getCreateTables();
        db.execSQL(sql);
        onCreate(db);
    }

    public long insertSQL(ContentValues values, String dbtable) {
        long rowId = -1;
        try {
            // open database
            SQLiteDatabase db = getWritableDatabase();
            Log.i(TAG, "Path: " + db.getPath());
            rowId = db.insert(dbtable, null, values);
        } catch (SQLiteException e) {
            Log.e(TAG, "insert()", e);
        } finally {
            Log.d(TAG, "insert(): rowId=" + rowId);
        }
        return rowId;
    }

    // select * from  =>  tableColumns=null
    public Cursor getQuery(String table, String[] tableColumns,
                           String whereClause, String orderBy)  {
        try{
            SQLiteDatabase db = getWritableDatabase();
            // db.execSQL(sql);
            // db.beginTransaction();
            // db.endTransaction();
            //  String table, String[] columns, String whereClause, String[] selectionArgs, String groupBy, String having, String orderBy
            //  String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy
            return db.query(table, tableColumns, whereClause, null, null, null, orderBy);
        }
        catch(SQLiteException e)    {
            Log.e("Database", "getQueryAutomarke", e);
            return null;
        }
        //return db.query(TABLE_NAME, null, "Automarke", new String[]{Automarke}, null, null, null);
    }


}  // DBSOpenHandler
