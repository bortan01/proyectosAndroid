package e.miranda.basedatos13;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Frase> listaFrases;
    TextInputLayout texto;
    ListView lista;
    public  static  ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        lista = findViewById(R.id.lista);
        listaFrases  = new ArrayList<>();
        conexionSQLite = new ConexionSQLite(this, "DB", null ,1);
        llenarLista();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "MANTEN PRESIONADO ", Toast.LENGTH_SHORT).show();
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Frase f =  (Frase) parent.getAdapter().getItem(position);
                conexionSQLite.eliminar(f.getIdfrase());
                llenarLista();
                return false;
            }
        });
    }

    public void agregar(View view) {
        if(texto.getEditText().getText().toString().isEmpty()){
            texto.setError("complete este campo");
            texto.setErrorEnabled(true);
        }else{

            texto.setErrorEnabled(false);
            if(MainActivity.conexionSQLite.insertar(
                    new Frase(0 ,texto.getEditText().getText().toString()))){
                    Toast.makeText(getBaseContext(),"datos almacenados" , Toast.LENGTH_LONG).show();
                    texto.getEditText().setText("");
                    llenarLista();
            }
        }
    }
    private void llenarLista() {
        conexionSQLite.consultar();
        ArrayAdapter<Frase> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaFrases);
        lista.setAdapter(adapter);
    }
}
