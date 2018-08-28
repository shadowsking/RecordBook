package com.example.king.recordbook.db.group;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.db.DbHelper;
import com.example.king.recordbook.db.DBInterface;

import java.util.ArrayList;

/**
 * Created by KING on 04.02.2018.
 */

public class GroupTable implements DBInterface {
    public static final String DB_TABLE = "Group_Table";
    public static final String DB_COLUMN_ID = "id";
    public static final String DB_COLUMN_ID_GROUP = "id_group";
    public static final String DB_COLUMN_NAME = "name";
    public static final String DB_COLUMN_ADMISSION_YEAR = "admission_year";
    DbHelper dbHelper;

    public GroupTable(SQLiteDatabase db, DbHelper dbHelper){
        creatеTable(db);
        this.dbHelper = dbHelper;
    }

    public GroupTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void creatеTable(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL ,%s TEXT NOT NULL, %s INTEGER);",
                DB_TABLE, DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME, DB_COLUMN_ADMISSION_YEAR);
        db.execSQL(query);
    }

    public void insert(int id_group, String name, int admission_year) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME, DB_COLUMN_ADMISSION_YEAR}, "name = ?", new String[]{name}, null, null,null);
        if  (cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(DB_COLUMN_ID_GROUP, id_group);
            values.put(DB_COLUMN_NAME, name);
            values.put(DB_COLUMN_ADMISSION_YEAR, admission_year);
            db.insertWithOnConflict(DB_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.close();
    }


    public void update() {

    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DB_TABLE, DB_COLUMN_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<GroupModel> getList() {
        ArrayList<GroupModel> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME, DB_COLUMN_ADMISSION_YEAR}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id_group = cursor.getColumnIndex(DB_COLUMN_ID_GROUP);
            int name = cursor.getColumnIndex(DB_COLUMN_NAME);
            int admission_name = cursor.getColumnIndex(DB_COLUMN_ADMISSION_YEAR);
             list.add(new GroupModel(cursor.getInt(id_group), cursor.getString(name), cursor.getInt(admission_name)));
        }
        cursor.close();
        db.close();
        return list;
    }

}
