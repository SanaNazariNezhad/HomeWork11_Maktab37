package org.maktab.homework11_maktab37.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.maktab.homework11_maktab37.controller.database.TaskDBSchema;

import androidx.annotation.Nullable;

public class TaskDBHelper extends SQLiteOpenHelper {

    public TaskDBHelper(@Nullable Context context) {
        super(context, TaskDBSchema.NAME, null, TaskDBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE " + TaskDBSchema.TaskTable.NAME + " (");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.UUID + " TEXT NOT NULL,");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.TITLE + " TEXT,");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.DESCRIPTION + " TEXT,");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.DATE + " TEXT,");
        sbQuery.append(TaskDBSchema.TaskTable.Cols.STATE + " TEXT");
        sbQuery.append(");");

        sqLiteDatabase.execSQL(sbQuery.toString());

        StringBuilder sbQuery_user = new StringBuilder();
        sbQuery_user.append("CREATE TABLE " + TaskDBSchema.UserTable.NAME + " (");
        sbQuery_user.append(TaskDBSchema.UserTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sbQuery_user.append(TaskDBSchema.UserTable.Cols.USERNAME + " TEXT NOT NULL,");
        sbQuery_user.append(TaskDBSchema.UserTable.Cols.PASSWORD + " TEXT");
        sbQuery_user.append(");");

        sqLiteDatabase.execSQL(sbQuery_user.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
