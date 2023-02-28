package com.example.notitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    protected DataBaseSQL db;
    protected ListView lista1;
    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;

    private Intent pasarPantalla;
    private String contenidoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);


        lista1 = (ListView) findViewById(R.id.lista1_second);

        db = new DataBaseSQL(this);
        filas = db.getAllNotas();
        adaptador = new ArrayAdapter<>(ListadoActivity.this, android.R.layout.simple_list_item_1, filas);

        if (adaptador.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast1_second_text), Toast.LENGTH_SHORT).show();
        } else {
            lista1.setAdapter(adaptador);
        }

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem = parent.getItemAtPosition(position).toString();
                pasarPantalla = new Intent(ListadoActivity.this, VerNotasActivity.class);
                pasarPantalla.putExtra("NOTA", contenidoItem);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }


    //MENU:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_opciones_second:
                pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
                finish();
                startActivity(pasarPantalla);
                return true;
            case R.id.item_crear_second:
                pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                finish();
                startActivity(pasarPantalla);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}