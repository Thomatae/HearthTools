package com.tcapps.hearthtools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tcapps.hearthtools.table.column.DeckTable;

public class HearthToolsSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hearthtools.db";

    public HearthToolsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, HearthToolsDatabaseVersion.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL(DeckTable.getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DeckTable.getDeleteQuery());
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
