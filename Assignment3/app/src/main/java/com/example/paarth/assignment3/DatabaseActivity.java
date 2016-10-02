package com.example.paarth.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseActivity extends SQLiteOpenHelper {
    private static final String DATBASE_NAME = "log.db";
    private static final String TABLE_NAME = "log_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "PASSWORD";




    public DatabaseActivity(Context context) {
        super(context, DATBASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, USERNAME TEXT, PASSWORD TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);


    }


    public boolean insertData(String username, String password){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return result != -1;

    }



    public Integer deleteData(String id){
        Integer x;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        x = sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {id});
        return x;
    }



    public Cursor retrieveData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME,null);

        return res;

    }

    @SuppressWarnings("SameReturnValue")
    public boolean updateData(String id, String username, String password){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;

    }
}
