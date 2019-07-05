package local.menudialogo08;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arregloX;
    ListView lista ;
    EditText palabra;
    SharedPreferences preferences;
    Button btn;
     static  int position;
     private static  boolean editar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("datos" , Context.MODE_PRIVATE);

        lista = findViewById(R.id.lista);
        palabra = findViewById(R.id.palabra);
        arregloX = new ArrayList<>();
        btn = findViewById(R.id.boton);
        
        //para que funcione el menu contextual
        registerForContextMenu(lista);

        recuperarPreferencias();

        llenar();



    }


    private void llenar() {
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listadoPalabras);
        //lista.setAdapter(adapter);

        Collections.sort(arregloX);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arregloX);

        lista.setAdapter(adapter);

        SharedPreferences.Editor editor = preferences.edit();
        Set arregloPalabras = new HashSet();
        for(String texto : arregloX){
            arregloPalabras.add(texto);
        }
        editor.putStringSet("savepalabras", arregloPalabras);
        editor.commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.lista){
            ///para saber la posicion de la pablabra
            position = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            ///esto es para el encabezaddo, lo que non me gusta
           // menu.setHeaderTitle(lista.getAdapter().getItem(posicionListaPalabra).toString());
            ///inflamos el menu que creamos anteriormente
            this.getMenuInflater().inflate(R.menu.menu_main,menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
     //   AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.opModificar:
               //palabra.setText(adapter.getItem(posicionListaPalabra));
               palabra.setSelection(palabra.getText().length());
               editar = true;
                btn.setText("Editar");
                break;
            case R.id.opEliminar:
                msg("Deseaeliminar este registro","Eliminar");

                break;
        }
        return super.onContextItemSelected(item);
    }

    public void Agregar(View view) {
        if (btn.getText().toString().equals("Agregar")){
            if(palabra.getText().toString().equals("")){
                msg("Complete la palabra", "");
            }else {
                arregloX.add(palabra.getText().toString());
                palabra.setText("");
                palabra.requestFocus();
                llenar();
            }
        }
        if(btn.getText().toString().equals("Editar")){
            if (palabra.getText().toString().equals("")){
                msg("complete la palabra", "");
            }else {
                arregloX.set(position,palabra.getText().toString());
                palabra.setText("");
                palabra.requestFocus();
                btn.setText("Agregar");
                llenar();
            }
        }
    }

    private void msg(String texto, String opcion) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("TITULO DEL DIALOGO");
        dialogo.setMessage(texto);

        if (opcion.equals("")){
            dialogo.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ////aqui se pone la accion si da click en aceptar
                }
            });
        }
        if (opcion.equals("Eliminar")){
            dialogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    arregloX.remove(position);
                    llenar();
                }
            });
            dialogo.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ////aqui se pone la accion si da click en aceptar
                }
            });
        }
        dialogo.show();
    }

    private void recuperarPreferencias() {
        Set arregloPalabras = new HashSet();
        arregloPalabras = preferences.getStringSet("savepalabras",null);
        if(arregloPalabras!=null){
            for(Object movie: arregloPalabras){
                arregloPalabras.add(String.valueOf(movie));
            }
        }
    }

}
