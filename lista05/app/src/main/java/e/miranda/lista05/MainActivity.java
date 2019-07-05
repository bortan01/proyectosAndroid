package e.miranda.lista05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner);
        txt = findViewById(R.id.textView);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.meses, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }

    public void guardar(View view) {
        String texto = spinner.getSelectedItem().toString();
        txt.setText(texto);

    }

    public void salir(View view) {
        finish();
    }
}
