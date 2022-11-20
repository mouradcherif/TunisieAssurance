package com.cherif.tunisieassurance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;


public class DBHelper extends SQLiteOpenHelper {
        public static final String DBNAME = "TunisieAsssurance.db";
        public DBHelper(Context context) {
            super(context, "TunisieAsssurance.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase MyDB) {
            MyDB.execSQL("create Table users(email TEXT primary key, password TEXT)");
            MyDB.execSQL("create Table clients(idC integer primary key AUTOINCREMENT,imageC TEXT,nameC TEXT,phoneC TEXT ,emailC TEXT ,regionC TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

            MyDB.execSQL("drop Table if exists users");
            MyDB.execSQL("drop Table if exists clients");
        }


    public Boolean insertDataClient(String imageC, String nameC,String phoneC,String emailC, String regionC){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1= new ContentValues();
        contentValues1.put("imageC", imageC);
        contentValues1.put("nameC", nameC);
        contentValues1.put("emailC", emailC);
        contentValues1.put("regionC", regionC);
        contentValues1.put("phoneC", phoneC);
        long result = MyDB.insert("clients", null, contentValues1);
        if(result==-1) return false;
        else
            return true;
    }

        public Boolean insertData(String email, String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            contentValues.put("email", email);
            contentValues.put("password", password);
            long result = MyDB.insert("users", null, contentValues);
            if(result==-1) return false;
            else
                return true;
        }

        public Boolean checkemail(String email) {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
            if (cursor.getCount() > 0)
                return true;
            else
                return false;
        }

        public Boolean checkemailpassword(String email, String password){
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }


    }
