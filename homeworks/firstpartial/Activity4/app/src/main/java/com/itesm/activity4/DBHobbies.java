package com.itesm.activity4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHobbies extends SQLiteOpenHelper {
    private static final String DB_FILE = "HobbiesDB.db";
    private static final String TABLE = "MY_HOBBIES";
    private static final String FIELD_ID = "id";
    private static final String FIELD_FRIEND = "friend";
    private static final String FIELD_HOBBIES = "hobbies";

    public DBHobbies(Context context){
        super(context,DB_FILE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY, " +
                FIELD_FRIEND+ " TEXT, " +
                FIELD_HOBBIES + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE; //"DROP TABLE IF EXISTS ?";
        String[] params = {TABLE};
        db.execSQL(query, params);
    }

    public void save(String friend, String hobby){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(FIELD_FRIEND,friend);
        vals.put(FIELD_HOBBIES,hobby);
        db.insert(TABLE,null,vals);
    }

    public int delete(String friend){
        SQLiteDatabase db = getWritableDatabase();
        String clause = FIELD_FRIEND + " = ?";
        String[] args = {friend};
        return db.delete(TABLE,clause,args);
    }

    public String find(String friend){
        SQLiteDatabase db = getReadableDatabase();
        String filter = FIELD_FRIEND + " = ?";
        String[] args = {friend};

        Cursor c = db.query(TABLE, null,filter,args,null,null,null);
        String result = "N/A";

        if(c.moveToFirst()){
            result = c.getString(2);
        }

//        if(c.moveToFirst()){
//            do{
//                if(c.getString(1) == friend){
//                    result = c.getString(2);
//                }
//            }while(c.moveToNext());
//        }

        return result;
    }


}
