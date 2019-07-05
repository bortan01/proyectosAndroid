package local.basedatos12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUsuarioActivity extends AppCompatActivity {
    EditText usuario,clave,reclave;
    TextView usuariorecuperado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario);

        usuario = findViewById(R.id.usuario);
        clave = findViewById(R.id.clave);
        reclave = findViewById(R.id.reclave);
        usuariorecuperado = findViewById(R.id.usuariorecuperado);
        usuariorecuperado.setText("hola" + MainActivity.usiarios.getUser());

    }
    public void cerrar(View view) {
        MainActivity.longin =false;
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /// PARA AGREGAR EL MENU QUE CREAMOS EN EL ARCHIVO MENU_MAIN
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ///PARA SABER SI HIZO TOUCH EN EL BOTON GUARDAR DEL MENU
        int id = item.getItemId();
        if(id == R.id.opguardar){
            if(usuario.getText().toString().isEmpty() || clave.getText().toString().isEmpty()){
                Toast.makeText(this, "INGRES CREDENCIALES",Toast.LENGTH_SHORT).show();
            }else{
                if(!clave.getText().toString().equals(reclave.getText().toString())){
                    Toast.makeText(this, "LA CLAVE NO CONINCIDE",Toast.LENGTH_SHORT).show();
                }else {
                    if(MainActivity.conexionSQLite.insertar(new Usiarios(0,usuario.getText().toString(),clave.getText().toString()))){
                        Toast.makeText(this, "DATOS ALMACENADOS",Toast.LENGTH_SHORT).show();
                        usuario.setText("");
                        clave.setText("");
                        reclave.setText("");
                    }else{
                        Toast.makeText(this, "ERROR",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        MainActivity.longin =true;
        super.onDestroy();
    }
}
