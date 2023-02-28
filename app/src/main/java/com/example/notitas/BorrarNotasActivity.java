package com.example.notitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BorrarNotasActivity extends AppCompatActivity {

    protected Button btn1_borrar;
    protected Button btn2_volver;

    protected DataBaseSQL db;
    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);
        getSupportActionBar().hide();

        btn1_borrar = (Button) findViewById(R.id.btn1_borrar_borrarNotas);
        btn2_volver = (Button) findViewById(R.id.btn2_volver_borrarNotas);

        db = new DataBaseSQL(this);

        btn1_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.borrarAllNotas();
                if (db.getAllNotas().isEmpty()) {
                    Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast1_borrarNota_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                } else {
                    Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast2_borrarNota_text), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}