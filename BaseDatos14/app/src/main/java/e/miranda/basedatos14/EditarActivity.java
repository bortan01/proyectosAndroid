package e.miranda.basedatos14;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class EditarActivity extends AppCompatActivity {
    TextInputLayout nombre, apellido, telefono;
    EditText edad;
    ToggleButton genero ;
    Datos datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        telefono = findViewById(R.id.telefono);
        edad = findViewById(R.id.edad);
        genero = findViewById(R.id.genero);
        //RECUPERRAMOS EL ELEMENTO SELECCIONADO EN LISTA ACTIVITY
        datos = (Datos) getIntent().getExtras().getSerializable("clase");

        nombre.getEditText().setText(datos.getNombre());
        apellido.getEditText().setText(datos.getApellido());
        edad.setText(String.valueOf(datos.getEdad()));
        telefono.getEditText().setText(datos.getTelefono());

        //si el genero es masgulino el texto pasa a
        //masculino, y el chequet pasa a true, de lo contrario
        //habria la posibilidad de dar un click y no ver cambio
        if(datos.getGenero().equals("Masculino")){
            genero.setText(datos.getGenero());
            genero.setChecked(true);
        }else{
            genero.setText(datos.getGenero());
            genero.setChecked(false);
        }
    }
    public void onModificar(View view) {
        if (nombre.getEditText().getText().toString().isEmpty()){
            nombre.setError("INGRESA NOMBRE");
            nombre.requestFocus();
        }else{
            nombre.setErrorEnabled(false);
            if(apellido.getEditText().getText().toString().isEmpty()){
                apellido.setError("INGRESA APELLIDO");
                apellido.requestFocus();
            }else{
                apellido.setErrorEnabled(false);
                if (telefono.getEditText().getText().toString().isEmpty()){
                    telefono.setError("INGRESA TELEFONO ");
                    telefono.requestFocus();
                }else {
                    telefono.setErrorEnabled(false);
                    if (edad.getText().toString().isEmpty()) {
                        edad.setError("INGRESA EDAD ");
                        edad.requestFocus();
                    } else {
                        if(MainActivity.conexionSQLite.modificar(new Datos(  datos.getIddatos(),
                                nombre.getEditText().getText().toString(),
                                apellido.getEditText().getText().toString(),
                                apellido.getEditText().getText().toString(),
                                Integer.parseInt(edad.getText().toString()),
                                telefono.getEditText().getText().toString()))) {
                                msg("DATOS ALMACENADOS", "");

                        }else{
                            msg("DATOS NO ALMACENADOS" , "");
                        }

                        }
                    }
                }
            }
        }
    public void msg(final String texto, String opcion){
        AlertDialog.Builder dalogo = new AlertDialog.Builder(this);
        dalogo.setTitle("INFORMACION");
        dalogo.setMessage(texto);
        if(opcion.equals("")){
            dalogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (texto.equals("DATOS ALMACENADOS")){
                        finish();
                        Intent intent = new Intent(getBaseContext(),ListaActivity.class);
                        startActivity(intent);
                    }
                }
            });
            dalogo.show();
        }
    }
    public void onCancelar(View view) {
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }
}





