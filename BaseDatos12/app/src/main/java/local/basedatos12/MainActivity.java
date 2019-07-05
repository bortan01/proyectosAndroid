package local.basedatos12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public  static  Usiarios usiarios;
public  static  boolean longin;
EditText elusuario,laclave;
public static  ConexionSQLite conexionSQLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "esto es el main activandoce", Toast.LENGTH_SHORT).show();

        elusuario=findViewById(R.id.usuario1);
        laclave =findViewById(R.id.clave1);
       // longin = false;
        conexionSQLite = new ConexionSQLite(this,"DBUsuario",null,1);
    }
    public void ingresar(View view) {
        if(elusuario.getText().toString().isEmpty() || laclave.getText().toString().isEmpty()){
            Toast.makeText(this, "INGRESA CREDENCIALES",Toast.LENGTH_SHORT).show();
        }else{
            usiarios = conexionSQLite.ConsultarUsuario(elusuario.getText().toString(),laclave.getText().toString());
            if(usiarios != null){
                longin =true;
                finish();
                Intent intent = new Intent(this,AddUsuarioActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "DATOS INCORRECTOS",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart() {
        /// ESTE METODO INICIA ANTES QUE EL MAIN
        super.onStart();
        Toast.makeText(this, "esto es el on star activandose activandoce", Toast.LENGTH_SHORT).show();
        if (longin){
            Toast.makeText(this, "SI",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,AddUsuarioActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "NO",Toast.LENGTH_SHORT).show();
        }
    }
}
