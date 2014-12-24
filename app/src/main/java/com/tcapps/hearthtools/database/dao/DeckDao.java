package com.tcapps.hearthtools.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.tcapps.hearthtools.database.HearthToolsSQLiteOpenHelper;
import com.tcapps.hearthtools.database.cursor.DeckCursor;
import com.tcapps.hearthtools.table.column.DeckTable;

import javax.inject.Inject;

public class DeckDao {

    private static final int INSERT_ERROR_CODE = -1;

    private SQLiteDatabase mWritableDatabase;
    private SQLiteDatabase mReadableDatabase;

    @Inject
    public DeckDao(HearthToolsSQLiteOpenHelper database) {
        mWritableDatabase = database.getWritableDatabase();
        mReadableDatabase = database.getReadableDatabase();
    }

    public long insertDeck(String name, String deckClass) {
        ContentValues values = new ContentValues();
        values.put(DeckTable.Columns.COLUMN_NAME.name, name);
        values.put(DeckTable.Columns.COLUMN_DECK_CLASS.name, deckClass);

        return mWritableDatabase.insert(DeckTable.TABLE_NAME, "null", values);
    }

    public DeckCursor readDecks() {
        return new DeckCursor(mReadableDatabase.query(DeckTable.TABLE_NAME,
                                                      new String[] {"*"},
                                                      null,
                                                      null,
                                                      null,
                                                      null,
                                                      "_id ASC"));
    }
}
