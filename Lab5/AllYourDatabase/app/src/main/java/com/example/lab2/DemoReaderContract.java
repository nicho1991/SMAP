package com.example.lab2;

import android.provider.BaseColumns;

public final class DemoReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DemoReaderContract() {}

    /* Inner class that defines the table contents */
    public static class DemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final int COLUMN_ID = 1;
        public static final String COLUMN_NAME_TASK = "task_name";
        public static final String COLUMN_NAME_PLACE = "Place_name";
    }
}
