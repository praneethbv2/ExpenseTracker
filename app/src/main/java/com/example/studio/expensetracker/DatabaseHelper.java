package com.example.studio.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String Database_name = "register.db";
    private static final String table_name = "Register";
    private static final String col_1 = "id";
    private static final String col_2 = "FirstName";
    private static final String col_3 = "LastName";
    private static final String col_4 = "username";
    private static final String col_5 = "password";



    public DatabaseHelper(Context context) {
        super(context, Database_name, null
                , 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + table_name +"(ID INTEGER PRIMARY KEY, FirstName TEXT,LastName TEXT , Username TEXT , Password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String fname,String lname, String uname, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_2,fname);
        contentValues.put(col_3,lname);
        contentValues.put(col_4,uname);
        contentValues.put(col_5,pwd);
        long result = db.insert(table_name,null,contentValues);
        if(result != -1)
        {
            return  false;
        }
        else return true;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery(" select * from " + table_name,null);
        return  data;
    }

    public boolean updateData(String id, String item1)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,item1);
        db.update(table_name,contentValues,"ID=?",new String[] { id } );
        return true;

    }
    public  Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(table_name,"ID=?",new String[] {id});

    }

    //To check whether username already exists
    public boolean checkuname(String usname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + table_name + " where  username = ?", new String[] {usname} );
        if (cursor.getCount()>0)    return false;
        else return true;
    }

    //verifying credentials
    public boolean credentials(String uname,String pwd)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+table_name+" where username = ? and password = ?",new String[] {uname,pwd});
        if(cursor.getCount()>0) return true;
        else return false ;

    }
}