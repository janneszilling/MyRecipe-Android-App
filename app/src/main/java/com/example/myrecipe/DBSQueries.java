package com.example.myrecipe;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class DBSQueries {
    private static final String TAG = DBSQueries.class.getSimpleName();

    private static final String TABLE1 = "RECIPE";

    public static final String ID = "_id";
    public static final String NAME = "NAME";
    public static final String DIFFICULTY = "DIFFICULTY";
    public static final String WORKTIME = "WORKTIME";
    public static final String COOKINGTIME = "COOKINGTIME";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String RECIPE = "RECIPETEXT";

    private static final String DATABASE_NAME = "database1.db";
    private static final int DATABASE_VERSION = 2;

    public static String getDatabaseName(){
        return DATABASE_NAME;
    }

    public static int getDatabaseVersion(){
        return DATABASE_VERSION;
    }


    public static String getCreateTables() {
        String s = "CREATE TABLE  if not exists "+TABLE1+" ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME+" VARCHAR(30) DEFAULT '', "+
                DIFFICULTY+" VARCHAR(30) DEFAULT '', "+
                WORKTIME+" VARCHAR(6) DEFAULT '', "+
                COOKINGTIME+" VARCHAR(6) DEFAULT '', "+
                INGREDIENTS+" VARCHAR(400) DEFAULT '', "+
                RECIPE+" VARCHAR(1500) DEFAULT '' "+
                " );";
        Log.i(TAG, "**********\n"+s);
        return s;
    }

    public static long insertRecipe(Recipe recipe) {
        DBSOpenHandler dbsOpenHandler = DBSOpenHandler.getInstance();
        ContentValues values = new ContentValues();
        values.put(NAME, recipe.name);
        values.put(DIFFICULTY, recipe.difficulty);
        values.put(WORKTIME, recipe.worktime);
        values.put(COOKINGTIME, recipe.cookingtime);
        values.put(INGREDIENTS, recipe.ingredients);
        values.put(RECIPE, recipe.recipe);
        return dbsOpenHandler.insertSQL(values, TABLE1);
    }

    public static Cursor getRecipeCursor()    {
        String[] tableColumns = {ID, NAME,DIFFICULTY,WORKTIME,COOKINGTIME,INGREDIENTS,RECIPE};
        DBSOpenHandler dbsOpenHandler = DBSOpenHandler.getInstance();
        Cursor cursor = dbsOpenHandler.getQuery(TABLE1, tableColumns, null, null);
        if (cursor==null) {
            Log.e(TAG,"Der cursor (getRecipeCursor) ist null");
            return null;
        }
        else {
            int count = cursor.getCount();
            Log.e(TAG,"(getRecipeCursor)  Anzahl der Zeilen: "+count);
            return cursor;
        }
    }

    private static String quoted(String s) {
        return "'"+s+"'";
    }

    public static void updateSQL(Recipe recipe) {

    }

    public static void deleteSQL(int pindex) {

    }
}
