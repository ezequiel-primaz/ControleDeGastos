package com.example.controledegastos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        gastosList = findViewById(R.id.gastosList);

        ArrayList<Gasto> gastos = new ArrayList<>();

        Date data1 = new Date();
        Date data2 = new Date();

        try{
            data1 = Helper.sdf.parse("27/08/1997");
            data2 = Helper.sdf.parse("26/09/2000");
        } catch(ParseException e) {
            e.printStackTrace();
        }

        gastos.add(new Gasto((float) 15.5, data1, "info", 1));
        gastos.add(new Gasto((float) 17.8, data2, "info2", 2));

        GastoAdapter adapter = new GastoAdapter(this, gastos);

        gastosList.setAdapter(adapter);

        gastosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            Gasto gasto = (Gasto) parent.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), gasto.toString(), Toast.LENGTH_LONG).show();
            }
        });
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
