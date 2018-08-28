package com.example.king.recordbook.db.point;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.king.recordbook.api.model.Group.GroupModel;
import com.example.king.recordbook.db.DbHelper;

import java.util.ArrayList;

/**
 * Created by KING on 22.02.2018.
 */

public class PointTable {
    /*
    public static final String DB_TABLE = "Point_Table";
    public static final String DB_COLUMN_ID = "id_point";
    public static final String DB_COLUMN_POINT_NAME = "point_name";

    //public static final String DB_COLUMN_POINT = "point";
    public static final String DB_COLUMN_RESULT = "result";
    public static final String DB_COLUMN_SEX = "sex";

    DbHelper dbHelper;

    public PointTable(SQLiteDatabase db, DbHelper dbHelper){
        createDB(db);
        this.dbHelper = dbHelper;
    }

    public GroupTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void createDB(SQLiteDatabase db) {

        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL ,%s TEXT NOT NULL);",
                DB_TABLE, DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME);
        db.execSQL(query);
    }

    public void insert(int id_group, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME}, "name = ?", new String[]{name}, null, null,null);
        if  (cursor.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(DB_COLUMN_ID_GROUP, id_group);
            values.put(DB_COLUMN_NAME, name);
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
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN_ID, DB_COLUMN_ID_GROUP, DB_COLUMN_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id_group = cursor.getColumnIndex(DB_COLUMN_ID_GROUP);
            int text = cursor.getColumnIndex(DB_COLUMN_NAME);
            list.add(new GroupModel(cursor.getInt(id_group), cursor.getString(text)));
        }
        cursor.close();
        db.close();
        return list;
    }
    */
}
