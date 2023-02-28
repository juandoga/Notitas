package com.example.notitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerNotasActivity extends AppCompatActivity {

    protected DataBaseSQL db;
    protected TextView label1;
    protected TextView label2;
    protected TextView label3;
    protected TextView label4;
    protected Button btn1_volver;
    protected Button btn2_borrar;

    private Bundle extras;
    private String paquete1;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);
        getSupportActionBar().hide();

        label1 = (TextView) findViewById(R.id.label1_verNota);
        label2 = (TextView) findViewById(R.id.label2_verNota);
        label3 = (TextView) findViewById(R.id.label3_verNota);
        label4 = (TextView) findViewById(R.id.label4_verNota);
        btn1_volver = (Button) findViewById(R.id.btn1_volver_verNota);
        btn2_borrar = (Button) findViewById(R.id.btn2_borrar_verNota);

        db = new DataBaseSQL(this);

        extras = getIntent().getExtras();
        if (extras != null) {
            paquete1 = extras.getString("NOTA");
            String[] partes = paquete1.split(".-");
            label2.setText(partes[0]);
            label4.setText(partes[1]);
        }


        btn1_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(VerNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        btn2_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extras = getIntent().getExtras();
                if (extras != null) {
                    paquete1 = extras.getString("NOTA");
                    String[] partes = paquete1.split(".-");
                    db.borrarNota(Integer.parseInt(partes[0]));
                    Toast.makeText(VerNotasActivity.this, getString(R.string.toast1_verNota_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(VerNotasActivity.this, ListadoActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });
    }
}