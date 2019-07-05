package e.miranda.futobol;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {

    Spinner comboNumero;
    Spinner comboPartido;
    ArrayList<String>numero;
    ArrayList<String>numeroList;
    ArrayList<ClaseJornada> jornadaList;
    int ID_JORNADA;
    EditText gollocal;
    EditText golvicitante;


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
        setContentView(R.layout.activity_resultados);

        consultarListaJornadas ();
        comboPartido =findViewById(R.id.partidos);
        comboNumero = findViewById(R.id.numero);
        gollocal = findViewById(R.id.golcasa);
        golvicitante = findViewById(R.id.golvicita);


        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,numeroList);
        comboNumero.setAdapter(adapter2);


      /*  ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,jornadaList);
        comboPartido.setAdapter(adapter);*/


        comboNumero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ids = Integer.toString(position+1);

                llenarcomboPartido(position+1);
              //  Toast.makeText(getBaseContext(), ids,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboPartido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                establecerIdPartido(jornadaList.get(position).getIdjornada());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void establecerIdPartido(int idjornada) {
    ID_JORNADA = idjornada;
    //Toast.makeText(getBaseContext(), Integer.toString(idjornada),Toast.LENGTH_SHORT).show();


    }

    private void llenarcomboPartido(int id) {
        jornadaList = MainActivity.conexionSQLite.consultarPartido(id);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,jornadaList);
        comboPartido.setAdapter(adapter);
    }

    private void consultarListaJornadas() {
        numeroList = MainActivity.conexionSQLite.numeroJornadas();

    }

    public void guardarResultado(View view) {
        if (golvicitante.getText().toString().isEmpty() || gollocal.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "complete los campos", Toast.LENGTH_LONG).show();

        } else {




        }
    }

    }

