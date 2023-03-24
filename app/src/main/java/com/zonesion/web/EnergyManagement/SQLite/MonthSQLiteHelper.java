package com.zonesion.web.EnergyManagement.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

/**
 * 代码为原创
 */
public class MonthSQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteOpenHelper mInstance=null;
    public static synchronized SQLiteOpenHelper getmInstance(Context mContext){
        if(mInstance==null){
            mInstance=new MonthSQLiteHelper(mContext,"monthquantity.db",null,3);
        }
        return mInstance;
    }
    public MonthSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table month(_id integer primary key autoincrement,quantity varchar(20),money varchar(20),remainamount varchar(20));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
