package com.example.king.recordbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.king.recordbook.db.group.GroupTable;
import com.example.king.recordbook.db.student.StudentTable;

/**
 * Created by KING on 04.02.2018.
 */

public class DbHelper  extends SQLiteOpenHelper{

    private static final String DB_NAME = "record_book_db";
    private static final int DB_VER = 1;

    private GroupTable groupTable;
    private StudentTable studentTable;

    public DbHelper(Context context) {
        super(context, DB_NAME , null, DB_VER);
        groupTable = new GroupTable(this);
        studentTable = new StudentTable(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        groupTable = new GroupTable(db, this);
        studentTable = new StudentTable(db, this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //String query = String.format("DELETE TABLE IF EXIST %s", GroupTable.DB_TABLE);

        db.execSQL(deleteTable(GroupTable.DB_TABLE));
        db.execSQL(deleteTable(StudentTable.DB_TABLE));
        onCreate(db);
    }

    private String deleteTable(String table){
        return String.format("DELETE TABLE IF EXIST %s", table);
    }

    public GroupTable getGroupTable(){
        return groupTable;
    }

    public StudentTable getStudentTable() {return studentTable;}
}
