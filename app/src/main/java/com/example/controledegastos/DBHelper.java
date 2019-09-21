package com.example.controledegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final String ID = "_id";
    public static final String TABELA = "gastos";
    public static final String VALOR = "valor";
    public static final String DETALHES = "detalhes";
    public static final String DATA = "data";
    public static final String CATEGORIA = "categoria";
    public static final int VERSAO = 1;

    public DBHelper(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + VALOR + " double,"
                + DETALHES + " text not null,"
                + DATA + " text,"
                + CATEGORIA + " int"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
