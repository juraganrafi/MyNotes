package com.belajar.rafi.pajaku.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Untuk mempermudah akses nama field dan tabel di dalam database
 */

public class DatabaseContract {

    static String TABLE_NOTE = "note";

    static final class NoteColumns implements BaseColumns {

        static String VEHICLE   = "vehicle";
        static String BBNKB     = "bbnkb";
        static String PKB       = "pkb";
        static String SWDKLLAJ  = "swdkllaj";
        static String ADMSTNK   = "admstnk";
        static String ADMTNKB   = "admtnkb";
        static String JUMLAH    = "jumlah";
        static String DATE      = "date";
        // date + time
        static String NOTIF     = "notif";
        static String DATEPIC   = "datepic";
        static String TIMEPIC   = "timepic";

    }

    public static final String AUTHORITY = "com.belajar.rafi.pajaku";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NOTE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
