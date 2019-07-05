package local.comunicacion03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReceptorActivity extends AppCompatActivity {

    EditText nombre,apellido,celular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor);
        nombre      = findViewById(R.id.nombre);
        apellido    = findViewById(R.id.apellido);
        celular     = findViewById(R.id.celular);

        Bundle parametros = this.getIntent().getExtras();
        String snombre = parametros.getString("nombre");
        String sapellido = parametros.getString("apellido");
        String scelular = parametros.getString("celular");

        nombre.setText(snombre);
        apellido.setText(sapellido);
        celular.setText(scelular);

    }

    public void volver(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
