package com.example.miranda.comunicacionclase04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReceptorActivity extends AppCompatActivity {
    EditText nombre, apellido , direccion ,telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        direccion = findViewById(R.id.direccion);
        telefono = findViewById(R.id.telefono);

        Datos informacion =  (Datos) getIntent().getExtras().getSerializable("losDatos");
        nombre.setText(informacion.getNombre());
        direccion.setText(informacion.getDireccion());
        apellido.setText(informacion.getApellido());
        telefono.setText(informacion.getTelefono());

    }

    public void onVolver(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);




    }
}

