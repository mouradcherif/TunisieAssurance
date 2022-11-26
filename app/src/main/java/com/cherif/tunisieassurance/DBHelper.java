package com.cherif.tunisieassurance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.widget.SimpleCursorAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;


public class DBHelper extends SQLiteOpenHelper {
        public static final String DBNAME = "TunisieAsssurance.db";
        public DBHelper(Context context) {
            super(context, "TunisieAsssurance.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase MyDB) {
            MyDB.execSQL("create Table users(email TEXT primary key, password TEXT)");
            MyDB.execSQL("create Table clients(idC integer primary key AUTOINCREMENT,nameC TEXT,phoneC TEXT ,emailC TEXT ,regionC TEXT);");
            MyDB.execSQL("create Table contracts(idCr integer primary key AUTOINCREMENT,refCr TEXT,datedebut TEXT ,datefin TEXT ,redevence TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

            MyDB.execSQL("drop Table if exists users");
            MyDB.execSQL("drop Table if exists clients");
            MyDB.execSQL("drop Table if exists contracts");
        }

    public Boolean insertDataContract(String refCr, String datedebut, String datefin, String redevence){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1= new ContentValues();
        contentValues1.put("refCr", refCr);
        contentValues1.put("datedebut", datedebut);
        contentValues1.put("datefin", datefin);
        contentValues1.put("redevence", redevence);
        long result = MyDB.insert("contracts", null, contentValues1);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertDataClient(String nameC, String phoneC, String emailC, String regionC){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues1= new ContentValues();
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

    public ArrayList<ModelClient> getAllData(){

            ArrayList<ModelClient> arrayList = new ArrayList<>();
            String selectQuery = "select * from clients";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            if (cursor.moveToFirst()){
                do {
                    ModelClient modelClient = new ModelClient(
                            ""+cursor.getInt(cursor.getColumnIndexOrThrow("idC")),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow("nameC")),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow("phoneC")),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow("regionC")),
                            ""+cursor.getString(cursor.getColumnIndexOrThrow("emailC"))
                    );

                    arrayList.add(modelClient);


                } while (cursor.moveToNext());
            }
            db.close();
            return arrayList;
    }

    public ArrayList<ModelContract> getAllDataContract(){

        ArrayList<ModelContract> arrayList = new ArrayList<>();
        String selectQuery = "select * from contracts";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                ModelContract modelContract = new ModelContract(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow("idCr")),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow("refCr")),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow("datedebut")),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow("datefin")),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow("redevence"))
                );

                arrayList.add(modelContract);


            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public void updateClient(String idC,String nameC, String phoneC, String emailC, String regionC){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        contentValues.put("nameC", nameC);
        contentValues.put("emailC", emailC);
        contentValues.put("regionC", regionC);
        contentValues.put("phoneC", phoneC);

        //update data in row, It will return id of record
        db.update("clients",contentValues,"idC"+"=? ",new String[]{idC} );

        // close db
        db.close();

    }

    public void updateContract(String idCr,String refCr, String datedebut, String datefin, String redevence){

        //get writable database to write data on db
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValue class object to save data
        ContentValues contentValues = new ContentValues();

        contentValues.put("refCr", refCr);
        contentValues.put("datedebut", datedebut);
        contentValues.put("datefin", datefin);
        contentValues.put("redevence", redevence);

        //update data in row, It will return id of record
        db.update("clients",contentValues,"idC"+"=? ",new String[]{idCr} );

        // close db
        db.close();

    }

    public void deleteClient(String id){
        //get writable database
        SQLiteDatabase db =  getWritableDatabase();

        //delete query
        db.delete("clients","WHERE"+" =? ",new String[]{id});

        db.close();
    }

    public void deleteContract(String id){
        //get writable database
        SQLiteDatabase db =  getWritableDatabase();

        //delete query
        db.delete("contracts","WHERE"+" =? ",new String[]{id});

        db.close();
    }


}
