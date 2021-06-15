package com.example.mathappt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String name="MathApp.db";
    public DatabaseHelper(Context context) {
        super(context, name, null, 1);
    }

        @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table members(member_id integer primary key autoincrement,name string,email string,password string,gender string)");
            db.execSQL("create table scores(email string,type string,score string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists members");
        db.execSQL("drop table if exists scores");
        onCreate(db);
    }
    public boolean insertData(String name,String email,String pass,String gender)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //onUpgrade(db,1,1);
        ContentValues cv = new ContentValues();
        if(name!=null&&email!=null&&pass!=null&&gender!=null) {

            cv.put("name", name);
            cv.put("email", email);
            cv.put("password", pass);
            cv.put("gender", gender);
        }
        if(db.insert("members",null,cv)==-1)
            return false;
        else
            return true;
    }

    public boolean insertScores(String email, String type, String score)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("email",email);
        cv.put("type",type);
        cv.put("score",score);

        if(db.insert("scores",null,cv)==-1)
            return false;
        else
            return true;
    }

    public Cursor getData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from members",null);
        return c;
    }

    public Cursor getScores(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from scores", null);
        return c;
    }
}
