package com.example.king.recordbook.db.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.king.recordbook.api.model.Student.StudentModel;
import com.example.king.recordbook.db.DbHelper;
import com.example.king.recordbook.db.DBInterface;

import java.util.ArrayList;

/**
 * Created by KING on 10.02.2018.
 */

public class StudentTable implements DBInterface {
    public static final String DB_TABLE = "Student_Table";
    public static final String DB_COLUMN_ID = "id_student";
    public static final String DB_COLUMN_FK_ID = "id_group";
    public static final String DB_COLUMN_NAME = "student_name";
    public static final String DB_COLUMN_SEX = "student_sex";
    DbHelper dbHelper;


    public StudentTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public StudentTable(SQLiteDatabase db, DbHelper dbHelper){
        creatеTable(db);
        this.dbHelper = dbHelper;
    }

    @Override
    public void creatеTable(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL);",
                DB_TABLE, DB_COLUMN_ID, DB_COLUMN_FK_ID, DB_COLUMN_NAME, DB_COLUMN_SEX);
        db.execSQL(query);
    }

    public void insert(int id_group, String name, String sex){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_NAME}, DB_COLUMN_NAME+" = ?", new String[]{name}, null, null,null);
        if  (cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(DB_COLUMN_FK_ID, id_group);
            values.put(DB_COLUMN_NAME, name);
            values.put(DB_COLUMN_SEX, sex);
            db.insertWithOnConflict(DB_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.close();
    }

    public ArrayList<StudentModel> getList(int id_group) {
        ArrayList<StudentModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_FK_ID, DB_COLUMN_NAME, DB_COLUMN_SEX}, DB_COLUMN_FK_ID+" = ?", new String[]{String.valueOf(id_group)}, null, null, null);
        //Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_FK_ID, DB_COLUMN_NAME, DB_COLUMN_SEX}, null, null, null, null, null);
        Log.d("LOL SIZE", String.valueOf(id_group) + " : " + String.valueOf(cursor.getCount()));
        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndex(DB_COLUMN_ID);
            Log.d("FK_ID: ", String.valueOf(cursor.getInt(cursor.getColumnIndex(DB_COLUMN_FK_ID))));
            int text = cursor.getColumnIndex(DB_COLUMN_NAME);
            int sex = cursor.getColumnIndex(DB_COLUMN_SEX);
            list.add(new StudentModel(cursor.getInt(id), cursor.getString(text), cursor.getString(sex)));
        }
        cursor.close();
        db.close();
        return list;
    }

}
