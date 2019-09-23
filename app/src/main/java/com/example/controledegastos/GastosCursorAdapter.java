package com.example.controledegastos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GastosCursorAdapter extends CursorAdapter {
    private final LayoutInflater inflater;

    public GastosCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, false);
        // Get inflater
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Use the inflater to inflate the listview layout and return it
        return inflater.inflate(R.layout.list_gasto, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        View listItemView = view;

        TextView txtValor = listItemView.findViewById(R.id.txtValor);
        float valor = cursor.getFloat(cursor.getColumnIndex(DBHelper.VALOR));
        txtValor.setText(Float.toString(valor));

        TextView txtData = listItemView.findViewById(R.id.txtData);
        String data = cursor.getString(cursor.getColumnIndex(DBHelper.DATA)).replaceAll("-","/");
        txtData.setText(data);

        TextView txtDetalhes = listItemView.findViewById(R.id.txtDetalhes);
        String detalhes = cursor.getString(cursor.getColumnIndex(DBHelper.DETALHES));
        txtDetalhes.setText(detalhes);

        ImageView imgCategoria = listItemView.findViewById(R.id.imgCategoria);
        int categoria = cursor.getInt(cursor.getColumnIndex(DBHelper.CATEGORIA));

        switch (categoria){
            case 0: {
                imgCategoria.setImageResource(R.drawable.mobility);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_mobility));
                break;
            }

            case 1: {
                imgCategoria.setImageResource(R.drawable.health);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_health));
                break;
            }

            case 2: {
                imgCategoria.setImageResource(R.drawable.drink);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_drink));
                break;
            }

            case 3: {
                imgCategoria.setImageResource(R.drawable.home);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_home));
                break;
            }

            case 4: {
                imgCategoria.setImageResource(R.drawable.shop);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_shop));
                break;
            }

            default: {
                imgCategoria.setImageResource(R.drawable.other);
                imgCategoria.setContentDescription(view.getResources().getString(R.string.app_category_content_description_other));
                break;
            }
        }
    }
}
