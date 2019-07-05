package local.vocal_consonante23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner combo;
    TextView tvSeleccion;
    EditText frase,tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        combo =findViewById(R.id.combo);
        tvResultado = findViewById(R.id.tvResultado);
        tvSeleccion =  findViewById(R.id.tvSeleccion);
        frase=  findViewById(R.id.frase);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_list_item_1);

        combo.setAdapter(adapter);
        combo.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tvSeleccion.setText(combo.getSelectedItem().toString());
        proceso();
    }

    public void proceso() {
        String seleccion = combo.getSelectedItem().toString();
        String cadenaFrase = frase.getText().toString().toUpperCase();
        String cadenaVocales="", cadenaConsonantes="";
        for (int i = 0; i < cadenaFrase.length(); i++) {
            char c = cadenaFrase.charAt(i);
            switch(c) {
                case 'A': case 'E': case 'I': case 'O': case 'U':
                    cadenaVocales += c;
                    break;
                default:
                    cadenaConsonantes += c;

            }
        }
        if(seleccion.equalsIgnoreCase("Vocales")) {
            tvResultado.setText(cadenaVocales);
        } else {
            tvResultado.setText(cadenaConsonantes);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
