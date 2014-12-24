package com.tcapps.hearthtools.database.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.tcapps.hearthtools.table.column.DeckTable;

public class DeckCursor extends CursorWrapper {

    public DeckCursor(Cursor cursor) {
        super(cursor);
    }

    public long readRowId() {
        return getLong(getColumnIndexOrThrow(DeckTable.Columns._ID));
    }

    public String readName() {
        return getString(getColumnIndexOrThrow(DeckTable.Columns.COLUMN_NAME.name));
    }

    public String readDeckType() {
        return getString(getColumnIndexOrThrow(DeckTable.Columns.COLUMN_DECK_CLASS.name));
    }

    public long readWins() {
        return getLong(getColumnIndexOrThrow(DeckTable.Columns.COLUMN_WINS.name));
    }

    public long readLosses() {
        return getLong(getColumnIndexOrThrow(DeckTable.Columns.COLUMN_LOSSES.name));
    }
}
