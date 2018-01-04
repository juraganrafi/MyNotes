package com.belajar.rafi.pajaku.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.belajar.rafi.pajaku.entity.Note;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.ADMSTNK;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.ADMTNKB;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.BBNKB;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.DATE;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.DATEPIC;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.JUMLAH;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.NOTIF;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.PKB;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.SWDKLLAJ;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.TIMEPIC;
import static com.belajar.rafi.pajaku.db.DatabaseContract.NoteColumns.VEHICLE;
import static com.belajar.rafi.pajaku.db.DatabaseContract.TABLE_NOTE;

/**
 * Tempat manipulasi data
 */

public class NoteHelper {
    private static String DATABASE_TABLE = TABLE_NOTE;
    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public NoteHelper(Context context){
        this.context = context;
    }

    public NoteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public ArrayList<Note> query(){
        ArrayList<Note> arrayList = new ArrayList<Note>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null,null, null, null, _ID +" DESC", null);
        cursor.moveToFirst();
        Note note;
        if (cursor.getCount()>0){
            do {
                note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                note.setVehicle(cursor.getString(cursor.getColumnIndexOrThrow(VEHICLE)));
                note.setBbnkb(cursor.getString(cursor.getColumnIndexOrThrow(BBNKB)));
                note.setPkb(cursor.getString(cursor.getColumnIndexOrThrow(PKB)));
                note.setSwdkllaj(cursor.getString(cursor.getColumnIndexOrThrow(SWDKLLAJ)));
                note.setAdmSTNK(cursor.getString(cursor.getColumnIndexOrThrow(ADMSTNK)));
                note.setAdmTNKB(cursor.getString(cursor.getColumnIndexOrThrow(ADMTNKB)));
                note.setJumlah(cursor.getString(cursor.getColumnIndexOrThrow(JUMLAH)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                note.setNotif(cursor.getString(cursor.getColumnIndexOrThrow(NOTIF)));
                note.setDatePic(cursor.getInt(cursor.getColumnIndexOrThrow(DATEPIC)));
                note.setTimePic(cursor.getInt(cursor.getColumnIndexOrThrow(TIMEPIC)));

                arrayList.add(note);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Note note){
        ContentValues initialValues = new ContentValues();
        initialValues.put(VEHICLE, note.getVehicle());
        initialValues.put(BBNKB, note.getBbnkb());
        initialValues.put(PKB, note.getPkb());
        initialValues.put(SWDKLLAJ, note.getSwdkllaj());
        initialValues.put(ADMSTNK, note.getAdmSTNK());
        initialValues.put(ADMTNKB, note.getAdmTNKB());
        initialValues.put(JUMLAH, note.getJumlah());
        initialValues.put(DATE, note.getDate());
        initialValues.put(NOTIF, note.getNotif());
        initialValues.put(DATEPIC, note.getDatePic());
        initialValues.put(TIMEPIC, note.getTimePic());

        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Note note){
        ContentValues args = new ContentValues();
        args.put(VEHICLE, note.getVehicle());
        args.put(BBNKB, note.getBbnkb());
        args.put(PKB, note.getPkb());
        args.put(SWDKLLAJ, note.getSwdkllaj());
        args.put(ADMSTNK, note.getAdmSTNK());
        args.put(ADMTNKB, note.getAdmTNKB());
        args.put(JUMLAH, note.getJumlah());
        args.put(DATE, note.getDate());
        args.put(NOTIF, note.getNotif());
        args.put(DATEPIC, note.getDatePic());
        args.put(TIMEPIC, note.getTimePic());

        return database.update(DATABASE_TABLE, args, _ID + "= '" + note.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_NOTE, _ID + " = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE, null
                , _ID + "=?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long inserProvider(ContentValues values){
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, _ID +"=?", new String[]{id});
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE, _ID +"=?", new String[]{id});
    }

}
