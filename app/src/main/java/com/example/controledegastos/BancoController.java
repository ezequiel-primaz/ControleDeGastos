package com.example.controledegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(float valor, String detalhes, String data, int categoria){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.getVALOR(), valor);
        valores.put(CriaBanco.getDETALHES(), detalhes);
        valores.put(CriaBanco.getDATA(), data);
        valores.put(CriaBanco.getCATEGORIA(), categoria);

        resultado = db.insert(CriaBanco.getTABELA(), null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }
}
