package com.example.controledegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edtDate;
    EditText edtValor;
    EditText edtDetalhes;
    Button btnCadastrar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDate = findViewById(R.id.edtDate);
        edtValor = findViewById(R.id.edtValor);
        edtDetalhes = findViewById(R.id.edtDetalhes);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        spinner = findViewById(R.id.spinner_categoria);

        new DateInputMask(edtDate);

        final Spinner spinner = findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_categorias_valores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data;
                float valor;
                String detalhes;
                int categoria;

                // Verifica quais valores foram inseridos e prepara o sistema para tratar os não inseridos
                if (TextUtils.isEmpty(edtValor.getText().toString())) {
                    valor = 0F;
                } else {
                    valor = Float.parseFloat(edtValor.getText().toString().trim());
                }

                if (TextUtils.isEmpty(edtDate.getText().toString())) {
                    data = "";
                } else {
                    data = edtDate.getText().toString().trim();
                }

                if (TextUtils.isEmpty(edtDetalhes.getText().toString())) {
                    detalhes = "";
                } else {
                    detalhes = edtDetalhes.getText().toString().trim();
                }

                categoria = Integer.parseInt(getResources().getStringArray(R.array.array_categorias_ids)[spinner.getSelectedItemPosition()]);

                data = data.replaceAll("/","-");
                if(checkValidEntries(valor, data, detalhes, categoria)){
                    Gasto gasto = new Gasto(valor,data,detalhes,categoria);
                    GastoDao db = new GastoDao(getBaseContext());
                    String resultado = db.insereDado(gasto);
                    Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean checkValidEntries (float valor, String data, String detalhes, int categoria) {
        if(valor > 0F && !detalhes.isEmpty()) {
            if (data.matches("\\d{2}-\\d{2}-\\d{4}")) {
                return true;
            } else {
                Toast.makeText(this, "Data Inválida", Toast.LENGTH_LONG).show();
                return false;
            }

        } else {
            if (valor <= 0F) {
                Toast.makeText(this, "Valor Inválido", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Insira alguns detalhes sobre este lançamento", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do something
    }
}
