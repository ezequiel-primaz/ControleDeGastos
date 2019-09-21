package com.example.controledegastos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GastosActivity extends AppCompatActivity {

    ListView gastosList;
    GastosCursorAdapter adapter;
    Activity context;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        context = this;
        gastosList = findViewById(R.id.gastosList);

        adapter = new GastosCursorAdapter(this, cursor);
        gastosList.setAdapter(adapter);

        gastosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);

                int cod = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.ID));
                float valor = cursor.getFloat(cursor.getColumnIndexOrThrow(DBHelper.VALOR));
                String data = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DATA));
                String detalhes = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.DETALHES));
                int categoria = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.CATEGORIA));

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Gasto gasto = new Gasto(cod, valor, data, detalhes, categoria);
                intent.putExtra("gasto", gasto);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GastoDao db = new GastoDao(this);
        cursor = db.carregaDados();
        adapter.changeCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.addActionButton) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
