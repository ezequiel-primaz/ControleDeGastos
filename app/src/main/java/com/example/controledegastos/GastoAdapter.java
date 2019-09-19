package com.example.controledegastos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GastoAdapter extends ArrayAdapter<Gasto> {

    public GastoAdapter(@NonNull Context context, @NonNull List<Gasto> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if( listItemView == null ) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_gasto, parent, false);
        }

        Gasto current = getItem(position);

        TextView txtValor = listItemView.findViewById(R.id.txtValor);
        TextView txtData = listItemView.findViewById(R.id.txtData);
        TextView txtDetalhes = listItemView.findViewById(R.id.txtDetalhes);
        ImageView imgCategoria = listItemView.findViewById(R.id.imgCategoria);

        switch (current.getCategoria()){
            case 1: {
                imgCategoria.setImageResource(R.drawable.mobility);
                break;
            }

            case 2: {
                imgCategoria.setImageResource(R.drawable.health);
                break;
            }

            case 3: {
                imgCategoria.setImageResource(R.drawable.drink);
                break;
            }

            case 4: {
                imgCategoria.setImageResource(R.drawable.home);
                break;
            }

            case 5: {
                imgCategoria.setImageResource(R.drawable.shop);
                break;
            }

            default: {
                imgCategoria.setImageResource(R.drawable.other);
                break;
            }
        }

        txtValor.setText(Float.toString(current.getValor()));
        txtDetalhes.setText(current.getDetalhes());
        txtData.setText(Helper.formatter.format(current.getData()));

        return listItemView;
    }

}
