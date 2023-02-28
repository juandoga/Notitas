package com.example.notitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearNotaActivity extends AppCompatActivity {

    protected TextView label1;
    protected EditText caja1;
    private String contenidoCaja1 = "";
    protected Button btn_volver;
    protected Button btn_crear;

    protected DataBaseSQL db;
    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);
        getSupportActionBar().hide();

        label1 = (TextView) findViewById(R.id.label1_creaNota);
        caja1 = (EditText) findViewById(R.id.caja1_crearNota);
        btn_volver = (Button) findViewById(R.id.btn_volver_crearNota);
        btn_crear = (Button) findViewById(R.id.btn_crear_crearNota);

        db = new DataBaseSQL(this);

        //EVENTO PARA AÑADIR NOTA CON LA TECLA ENTER
        caja1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    contenidoCaja1 = caja1.getText().toString();
                    if (contenidoCaja1.equalsIgnoreCase("")) {
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crearNota_text), Toast.LENGTH_SHORT).show();
                    } else {
                        db.insertNota(contenidoCaja1);
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crearNota_text), Toast.LENGTH_SHORT).show();
                        caja1.setText("");
                        pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    }
                }
                return false;
            }
        });

        //BOTON CREAR NOTA
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equalsIgnoreCase("")) {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crearNota_text), Toast.LENGTH_SHORT).show();
                } else {
                    db.insertNota(contenidoCaja1);
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crearNota_text), Toast.LENGTH_SHORT).show();
                    caja1.setText("");
                    pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });


        //BOTON VOLVER AL LISTADO
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}