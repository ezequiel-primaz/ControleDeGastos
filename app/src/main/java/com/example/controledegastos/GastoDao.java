package com.example.controledegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GastoDao {

    private SQLiteDatabase db;
    private DBHelper banco;
    private Context context;

    public GastoDao(Context context){
        banco = new DBHelper(context);
        this.context = context;
    }

    public String insereDado(Gasto gasto){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(DBHelper.VALOR, gasto.getValor());
        valores.put(DBHelper.DETALHES, gasto.getDetalhes());
        valores.put(DBHelper.DATA, gasto.getData());
        valores.put(DBHelper.CATEGORIA, gasto.getCategoria());

        resultado = db.insert(DBHelper.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return this.context.getResources().getString(R.string.app_insert_error_messege);
        else
            return this.context.getResources().getString(R.string.app_insert_success_messege);

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID, banco.VALOR, banco.DETALHES, banco.DATA, banco.CATEGORIA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    protected String alteraRegistro(Gasto gasto){
        ContentValues valores;
        String where;
        long resultado;

        db = banco.getWritableDatabase();

        where = DBHelper.ID + "=" + gasto.get_id();

        valores = new ContentValues();
        valores.put(DBHelper.VALOR, gasto.getValor());
        valores.put(DBHelper.DETALHES, gasto.getDetalhes());
        valores.put(DBHelper.DATA, gasto.getData());
        valores.put(DBHelper.CATEGORIA, gasto.getCategoria());

        resultado = db.update(DBHelper.TABELA, valores, where,null);
        db.close();

        if (resultado ==-1)
            return this.context.getResources().getString(R.string.app_update_error_messege);
        else
            return this.context.getResources().getString(R.string.app_update_success_messege);
    }

    public String deletaRegistro(int id){
        long resultado;
        String where = DBHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete(DBHelper.TABELA,where,null);
        db.close();

        if (resultado ==-1)
            return this.context.getResources().getString(R.string.app_delete_error_messege);
        else
            return this.context.getResources().getString(R.string.app_delete_success_messege);
    }
}
