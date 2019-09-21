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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        context = this;
        gastosList = findViewById(R.id.gastosList);

        // Set the adapter for the view. Now there is no Cursor object that contains data to show
        // so the Cursor should be null.
        adapter=new GastosCursorAdapter(this,null);
        gastosList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GastoDao db = new GastoDao(this);
        Cursor cursor = db.carregaDados();
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
