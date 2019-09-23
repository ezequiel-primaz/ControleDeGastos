package com.example.controledegastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Gasto gasto = null;

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

        gasto = (Gasto) getIntent().getSerializableExtra("gasto");

        if (gasto != null) {
            spinner.setSelection(gasto.getCategoria());
            edtValor.setText(Float.toString(gasto.getValor()));
            edtDetalhes.setText(gasto.getDetalhes());
            edtDate.setText(gasto.getData());
            btnCadastrar.setText(getResources().getString(R.string.app_salvar));
        }

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

                    GastoDao db = new GastoDao(getBaseContext());
                    if (gasto != null) {
                        if (valor != gasto.getValor() || !data.equals(gasto.getData().replaceAll("/", "-")) || !detalhes.equals(gasto.getDetalhes()) || categoria != gasto.getCategoria()){
                            Gasto newGasto = new Gasto(gasto.get_id(),valor,data,detalhes,categoria);
                            String result = db.alteraRegistro(newGasto);
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),GastosActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.app_objeto_n_modificado), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Gasto newGasto = new Gasto(valor,data,detalhes,categoria);
                        String resultado = db.insereDado(newGasto);
                        Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),GastosActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        });

    }

    public boolean checkValidEntries (float valor, String data, String detalhes, int categoria) {
        if(valor > 0F && !detalhes.isEmpty()) {
            if (data.matches("\\d{2}-\\d{2}-\\d{4}")) {
                return true;
            } else {
                Toast.makeText(this, getResources().getString(R.string.app_data_invalida), Toast.LENGTH_LONG).show();
                return false;
            }

        } else {
            if (valor <= 0F) {
                Toast.makeText(this, getResources().getString(R.string.app_valor_invalido), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.app_detalhe_invalido), Toast.LENGTH_LONG).show();
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
