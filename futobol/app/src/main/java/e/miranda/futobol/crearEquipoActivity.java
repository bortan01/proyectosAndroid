package e.miranda.futobol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class crearEquipoActivity extends AppCompatActivity {

    public static ArrayList<ClaseEquipo> listaEquipo;
    EditText texto;
    ListView lista;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu){
            Intent intent = new Intent(this,opcionesActivity.class);
            startActivity(intent);
        }
        if(id == R.id.salir){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);

        texto = findViewById(R.id.frase);
        lista = findViewById(R.id.lista);
        listaEquipo = new ArrayList<>();

        llenarLista();

    }

    private void llenarLista() {
        listaEquipo = MainActivity.conexionSQLite.consultarEquipo();
        if(listaEquipo != null){
            ArrayAdapter<ClaseEquipo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEquipo);
            lista.setAdapter(adapter);
            Toast.makeText(this,"es distinto de nulo " , Toast.LENGTH_SHORT).show();
      }
    }
    public void guardar(View view) {

        if (texto.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "complete este campo", Toast.LENGTH_LONG).show();

        } else {

            if (MainActivity.conexionSQLite.insertarEquipo(
                    new ClaseEquipo(0, texto.getText().toString(), 0))) {
                Toast.makeText(getBaseContext(), "datos almacenados", Toast.LENGTH_LONG).show();
                texto.setText("");
                llenarLista();
            }


        }
    }
}
