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
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextInputLayout nombre, apellido, telefono;
    EditText edad;
    ToggleButton genero ;
    ///CREAMOS UNA VARIABLE CONEXION PARA PODER ACCEDER A LOS METODOS SQL
    public  static  ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        telefono = findViewById(R.id.telefono);
        edad = findViewById(R.id.edad);
        genero = findViewById(R.id.genero);
        conexionSQLite = new ConexionSQLite(this, "DBdatos", null, 1 );
    }
    public void onGuaardar(View view) {
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
                }else{
                    telefono.setErrorEnabled(false);
                    if(edad.getText().toString().isEmpty()){
                        edad.setError("INGRESA EDAD ");
                        edad.requestFocus();
                    }else{
                        if(conexionSQLite.insertar2(new Datos(0,
                                nombre.getEditText().getText().toString(),
                                apellido.getEditText().getText().toString(),
                                genero.getText().toString(),
                                Integer.parseInt(edad.getText().toString()),
                                telefono.getEditText().getText().toString()))){
                                msg("datos almacenados","");
                            limpiar();
                        }else{
                            msg("datos no almacenados","");
                        }
                    }
                }
            }
        }
    }
    public void msg(String texto, String opcion){
        AlertDialog.Builder dalogo = new AlertDialog.Builder(this);
        dalogo.setTitle("INFORMACION");
        dalogo.setMessage(texto);
        if(opcion.equals("")){
            dalogo.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dalogo.show();
        }
    }
    public void  limpiar(){
        nombre.getEditText().setText("");
        apellido.getEditText().setText("");
        telefono.getEditText().setText("");
        edad.setText("");
        genero.setChecked(false);
    }
    public void onCancelar(View view) {
        limpiar();
    }
    public void onLista(View view) {
        Intent intent = new Intent(this,ListaActivity.class);
        startActivity(intent);
    }
    public void onModificar(View view) {

    }
}
