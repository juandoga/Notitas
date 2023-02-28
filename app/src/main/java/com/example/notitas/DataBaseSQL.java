package com.example.notitas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public DataBaseSQL(Context context) {
        super(context, "notitas", null, 1);
    }

    //CREAR LA TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas (id integer primary key autoincrement NOT NULL, title text)");
    }

    //AL HACER UNA NUEVA TABLA
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notas");
    }

    //INSERTAR EN LA TABLA:
    public void insertNota(String title) {
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (title) VALUES ('" + title + "')");
    }

    public void borrarAllNotas() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void borrarNota(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id =" + id);
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNotas() {
        ArrayList<String> filas = new ArrayList<String>();
        Cursor res = null;
        String contenido = "";
        if (numNotas() > 0) {
            db = this.getReadableDatabase();
            res = db.rawQuery("SELECT * FROM notas ORDER by id ASC", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
                filas.add(contenido);
                res.moveToNext();
            }
        }

        return filas;
    }

    public int numNotas() {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "notas");
        return num;
    }

    public void close() {
        db.close();
    }
}
