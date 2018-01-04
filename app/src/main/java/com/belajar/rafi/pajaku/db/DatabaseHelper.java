package com.belajar.rafi.pajaku.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Penciptaan database, table, dan handle skema perbedaan versi
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbpajak";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," + // id
                    " %s TEXT NOT NULL," +  // vehicle
                    " %s TEXT NOT NULL," +  // bbnkb
                    " %s TEXT NOT NULL," +  // pkb
                    " %s TEXT NOT NULL," +  // swdkllaj
                    " %s TEXT NOT NULL," +  // admstnk
                    " %s TEXT NOT NULL," +  // admtnkb
                    " %s TEXT NOT NULL," +  // jumlah
                    " %s TEXT NOT NULL," +  // date
                    // date + time
                    " %s TEXT NOT NULL," +  // notif
                    " %s TEXT NOT NULL," +  // datepic
                    " %s TEXT NOT NULL)",   // timepic

            DatabaseContract.TABLE_NOTE,
            DatabaseContract.NoteColumns._ID,
            DatabaseContract.NoteColumns.VEHICLE,
            DatabaseContract.NoteColumns.BBNKB,
            DatabaseContract.NoteColumns.PKB,
            DatabaseContract.NoteColumns.SWDKLLAJ,
            DatabaseContract.NoteColumns.ADMSTNK,
            DatabaseContract.NoteColumns.ADMTNKB,
            DatabaseContract.NoteColumns.JUMLAH,
            DatabaseContract.NoteColumns.DATE,
            DatabaseContract.NoteColumns.NOTIF,
            DatabaseContract.NoteColumns.DATEPIC,
            DatabaseContract.NoteColumns.TIMEPIC
    );

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    //onUpgrade dipanggil apabila ada perbedaan Versi database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NOTE);
        onCreate(sqLiteDatabase);
    }
}
