package com.example.c3bluetoothproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FlightLibrary.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME ="my_library";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_NAME ="guest_name";
    private static final String COLUMN_NUMBER ="guest_number";
    private static final String COLUMN_TIME ="guest_time";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " TEXT, " +
                        COLUMN_TIME + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addGuest(String name,String number,String time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_NUMBER,number);
        cv.put(COLUMN_TIME,time);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
       long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
       if(result == -1){
           Toast.makeText(context, "Failed To Delete", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
       }
    }

    void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
