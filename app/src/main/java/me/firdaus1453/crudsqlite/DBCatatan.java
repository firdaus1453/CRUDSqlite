package me.firdaus1453.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBCatatan extends SQLiteOpenHelper {

    static abstract class MyColumns implements BaseColumns{
        static final String namaTabel = "Catatan";
        static final String id_judul = "ID_judul";
        static final String judul = "Judul";
        static final String isi = "Isi";
    }

    private static final String namaDatabase = "catatan.db";
    private static final int versiDatabase = 1;

    // Query untuk membuat table
    private static final String SQL_CREATE_TABLE = "CREATE TABLE "+ MyColumns.namaTabel +
            "("+MyColumns.id_judul+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +MyColumns.judul+" TEXT NOT NULL, "
            +MyColumns.isi+" TEXT NOT NULL)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+MyColumns.namaTabel;

    public DBCatatan(Context context){
        super(context, namaDatabase, null, versiDatabase);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
