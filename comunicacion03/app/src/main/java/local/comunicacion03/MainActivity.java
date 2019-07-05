package local.comunicacion03;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nombre,apellido,celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        celular = findViewById(R.id.celular);

    }
    public void enviar(View view) {
        finish();
        Intent intent = new Intent(this, ReceptorActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("nombre",nombre.getText().toString());
        intent.putExtra("apellido",apellido.getText().toString());
        intent.putExtra("celular",celular.getText().toString());
        startActivity(intent);
    }

}
