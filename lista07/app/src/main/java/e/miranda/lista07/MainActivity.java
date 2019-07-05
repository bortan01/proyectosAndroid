package e.miranda.lista07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] paises={"El Salvador", "Guatemala", "Honduras", "Nicaragua"};
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista=(ListView)findViewById(R.id.lista);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, paises);
        // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        lista.setAdapter(adapter);

        //darle evento
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),""+paises[i] + " seleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrar(View view){
        String seleccion="";
        try {
            for (int i=0; i<lista.getChildCount(); i++){
                CheckedTextView item=(CheckedTextView)lista.getChildAt(i);
                if (item.isChecked()){
                    seleccion+=item.getText().toString()+" ";
                }
            }
            Toast.makeText(getBaseContext(),""+seleccion,Toast.LENGTH_SHORT).show();
        }catch (Exception e){}
    }
}
