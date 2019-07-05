package local.listaapi23;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    List<String>lalista;
    ListView lista;
    EditText frace;
    int positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    lalista = new ArrayList<String>();
    lista = findViewById(R.id.lista);
    frace = findViewById(R.id.frace);


    //lista.setOnItemClickListener(this);
lista.setOnItemLongClickListener(this);

    llenar();

    }

    private void llenar() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lalista);
        lista.setAdapter(adapter);

    }

    public void onAscendente(View view) {
        Collections.sort(lalista);
        llenar();


    }

    public void onLimpiar(View view) {
    lalista.clear();
    llenar();
    }

    public void onDescendente(View view) {
        Collections.reverse(lalista);
        llenar();

    }

    public void onAgregar(View view) {

        final ProgressDialog dialog = ProgressDialog.show(this, "Agregabdi", "Espere");
        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    dialog.dismiss();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        lalista.add(frace.getText().toString());
        frace.setText("");
        frace.requestFocus();
        llenar();

    }



    private void msg(String texto) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Mensaje");
        dialogo.setMessage(texto);
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogo.show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        msg(lalista.get(position));
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //msg(lalista.get(position));
        frace.setText(lalista.get(position));
        positions = position;
        return false;
    }
}
