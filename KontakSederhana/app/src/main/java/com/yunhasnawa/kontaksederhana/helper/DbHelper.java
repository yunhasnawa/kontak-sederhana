package com.yunhasnawa.kontaksederhana.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper
{

    public DbHelper(@Nullable Context context)
    {
        super(context, "kontak.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Kode untuk membuat tabel
        String sql = "CREATE TABLE kontak (/*0*/id INTEGER PRIMARY KEY AUTOINCREMENT, /*1*/nama VARCHAR(255), /*2*/nomor_telepon VARCHAR(50));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Dibutuhkan apabila ada perubahan struktur data
        // Abaikan untuk saat ini..
    }

    // Menulis data ke database
    public void tulisData(String sql)
    {
        SQLiteDatabase db = this.getWritableDatabase(); // Database yang untuk menulis data --> INSERT, UPDATE, DELETE

        db.execSQL(sql);
    }

    // Membaca data dari database
    public Cursor bacaData(String sql)
    {
        SQLiteDatabase db = this.getReadableDatabase(); // Database untuk me READ --> SELECT

        Cursor hasil = db.rawQuery(sql, null);

        return hasil;
    }
}
