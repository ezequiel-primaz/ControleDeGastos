package com.example.controledegastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GastoDao {

    private SQLiteDatabase db;
    private DBHelper banco;

    public GastoDao(Context context){
        banco = new DBHelper(context);
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
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

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
            return "Erro ao atualizar registro " + resultado;
        else
            return "Registro atualizado com sucesso " + resultado;
    }

    public String deletaRegistro(int id){
        long resultado;
        String where = DBHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete(DBHelper.TABELA,where,null);
        db.close();

        if (resultado ==-1)
            return "Erro ao deletar registro";
        else
            return "Registro deletado com sucesso";
    }
}
