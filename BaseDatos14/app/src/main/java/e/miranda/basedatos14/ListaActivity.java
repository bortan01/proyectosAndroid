package e.miranda.basedatos14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class ListaActivity extends AppCompatActivity {
ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = findViewById(R.id.lista);
        llenarLista();
   }
    private void llenarLista() {
        final Adaptador adaptador = new Adaptador(this, R.layout.activity_custom,MainActivity.conexionSQLite.consultar());
        lista.setAdapter(adaptador);
            }
    public void onVolver(View view) {
        finish();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
    public void onEditar(View view) {
        ///recuperamos los datos de la lista usando el tag, de el boton donde se
        //selecciono, en su interior tiene el id del elemento, el cual
        // ocuparemos para hacer la consulta a la base de datos
        Datos d = MainActivity.conexionSQLite.consultar(String.valueOf(view.getTag()));
        Intent intent = new Intent(getBaseContext(),EditarActivity.class);
        intent.putExtra("clase", d);
        startActivity(intent);
    }
}
