package local.usandolistaapi23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    List<String>names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ///datos a mostrar
        names = new ArrayList<String>();
        names.add("boris");
        names.add("ricardo");
        names.add("miranda");

        names.add("boris");
        names.add("ricardo");
        names.add("miranda");  names.add("boris");
        names.add("ricardo");
        names.add("miranda");  names.add("boris");
        names.add("ricardo");
        names.add("miranda");  names.add("boris");
        names.add("ricardo");
        names.add("miranda");


        accionClick();
       adaptadorComplejo();





    }

public void adaptadorSimple(){
    ///adaptador de la forma visual en que mostraremos nuestos datos
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);

    ///enlazamos el adaptador con nuesto List View
    listView.setAdapter(adapter);

}
public  void adaptadorComplejo (){
    /// enlazamos con nuestro adpatador personalizado
    myAdapter myAdapter = new myAdapter(this, R.layout.list_item, names);
    listView.setAdapter(myAdapter);
}
public  void accionClick(){
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "click en " + names.get(position),Toast.LENGTH_SHORT).show();
        }
    });
}

}
