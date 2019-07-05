package e.miranda.mapa16;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NuevoActivity extends AppCompatActivity {
    TextInputLayout nombre,apellido,latitud,longitud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        latitud = findViewById(R.id.latitud);
        longitud = findViewById(R.id.longitud);
    }

    public void obtener(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivityForResult(intent,1);
    }

    public void guardar(View view) {
        if(nombre.getEditText().getText().toString().equals("")){
            nombre.setError("digite un nombre");
        }else if(apellido.getEditText().getText().toString().equals("")){
            latitud.setError("digite un apellido");
            nombre.setErrorEnabled(false);
        }else if(latitud.getEditText().getText().toString().equals("")){
            latitud.setError("digite la latitud");
            apellido.setErrorEnabled(false);
        }else if(longitud.getEditText().getText().toString().equals("")){
            longitud.setError("digite la longitud");
            latitud.setErrorEnabled(false);
        }else {
            Datos datos = new Datos(nombre.getEditText().getText().toString(),
                    apellido.getEditText().getText().toString(),
                    Double.parseDouble(latitud.getEditText().getText().toString()),
                    Double.parseDouble(longitud.getEditText().getText().toString()));
            if(MainActivity.conexionSQLite.insertar(datos,this)){
                msg("datos almacenados");
                limpiar();
            }else{
                msg("datos no almacenados");
            }

        }
    }

    private void limpiar() {
        latitud.getEditText().setText("");
        longitud.getEditText().setText("");
        nombre.getEditText().setText("");
        apellido.getEditText().setText("");
    }


    boolean verificar(TextInputLayout input, String error ){
        if(input.getEditText().getText().toString().equals("")){
            input.setError(error);
            return false;
        }else{
            input.setError("");
            return  true;
        }

    }
    private void msg(String texto) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("INFORMACION");
        dialogo.setMessage(texto);


            dialogo.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ////aqui se pone la accion si da click en aceptar
                }
            });

        dialogo.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1){
            if(resultCode == RESULT_OK){
                latitud.getEditText().setText("" + MainActivity.latitud);
                longitud.getEditText().setText("" + MainActivity.longitud);
            }
        }

    }

    public  void menu(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }
}



