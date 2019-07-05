package local.listapaises23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Pais> paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        paises = new ArrayList<>();
        paises.add (new Pais("Argentina","Buenos Aires", R.drawable.argentina));
        paises.add (new Pais("Belgica","San belgica", R.drawable.belgica));
        paises.add (new Pais("Portugal","Lisboa", R.drawable.portugal));
        paises.add (new Pais("Argentina","Buenos Aires", R.drawable.argentina));
        paises.add (new Pais("Belgica","San belgica", R.drawable.belgica));
        paises.add (new Pais("Portugal","Lisboa", R.drawable.portugal));
        paises.add (new Pais("Argentina","Buenos Aires", R.drawable.argentina));
        paises.add (new Pais("Belgica","San belgica", R.drawable.belgica));
        paises.add (new Pais("Portugal","Lisboa", R.drawable.portugal));




        Adaptador adaptador = new Adaptador(this,R.layout.activity_custom, paises);
        ListView lista  = findViewById(R.id.lista);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(getBaseContext(),paises.get(position).getNombre(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("obj",paises.get(position));
        startActivity(intent);
    }
}
