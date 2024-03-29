package com.tcapps.hearthtools.table.column;

public enum ColumnType {

    TEXT("TEXT"),
    INTEGER("INTEGER");

    private String type;

    private ColumnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
