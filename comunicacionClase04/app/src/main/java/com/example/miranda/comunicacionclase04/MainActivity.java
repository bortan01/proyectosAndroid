package com.example.miranda.comunicacionclase04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nombre, apellido , direccion ,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);
    }

    public void onEnviar(View view) {
        finish();
        Intent intent = new Intent(this, ReceptorActivity.class);
        intent.putExtra("losDatos", new Datos(nombre.getText().toString(),apellido.getText().toString(),direccion.getText().toString(),telefono.getText().toString()));
        startActivity(intent);
    }
}
