package com.example.controledegastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "gastos";
    private static final String ID = "_id";
    private static final String VALOR = "valor";
    private static final String DETALHES = "detalhes";
    private static final String DATA = "data";
    private static final String CATEGORIA = "categoria";
    private static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getID() {
        return ID;
    }

    public static String getVALOR() {
        return VALOR;
    }

    public static String getDETALHES() {
        return DETALHES;
    }

    public static String getDATA() {
        return DATA;
    }

    public static String getCATEGORIA() {
        return CATEGORIA;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + VALOR + " double,"
                + DETALHES + " text,"
                + DATA + " date,"
                + CATEGORIA + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
