package local.preferencias10;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText frase;
    Spinner spinner;
    ListView lista;
    ArrayList<String>frases;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("frases",Context.MODE_PRIVATE);
        frase = findViewById(R.id.frase);
        spinner = findViewById(R.id.spinner);
        lista = findViewById(R.id.lista);
        frases = new ArrayList<>();
        llenarCombo();
        recuperarPreferencias();
        llenarLista();
    }

    private void llenarLista() {
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,frases);
        lista.setAdapter(adapter);
    }

    private void recuperarPreferencias() {
        Set arregloPalabras = new HashSet();
        arregloPalabras = preferences.getStringSet("frases",null);
        if(arregloPalabras!=null){
            for(Object movie: arregloPalabras){
                frases.add(String.valueOf(movie));
            }
        }
    }

    private void llenarCombo() {
        String[] opciones = {"Minuscula","Mayuscula"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,opciones);
        spinner.setAdapter(adapter);


    }

    public void Agregar(View view) {
        if (frase.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"ingrese la frase",Toast.LENGTH_SHORT).show();
        }else{
            String op =(String) spinner.getSelectedItem().toString();
            if (op.equals("Minuscula")){
                frases.add(frase.getText().toString().toLowerCase());
            }else{
                frases.add(frase.getText().toString().toUpperCase());
            }
            llenarLista();
            guardarPreferencias();
        }
    }

    private void guardarPreferencias() {
        SharedPreferences.Editor editor = preferences.edit();
        Set arregloFrase = new HashSet();
        for (String texto:frases){
            arregloFrase.add(texto);
        }
        editor.putStringSet("frases",arregloFrase);
        editor.commit();
    }

    public void clear(View view) {
        frases.clear();
        guardarPreferencias();
        llenarLista();
    }
}
