package local.salarioapi23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CalculoActivity extends AppCompatActivity {
    EditText Salariow, afp , isss ,renta, neto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        Salariow = findViewById(R.id.txtSalario);
        afp = findViewById(R.id.txtafp);
        isss = findViewById(R.id.txtisss);
        renta = findViewById(R.id.txtrenta);
        neto = findViewById(R.id.txtneto);


        salario datos =  (salario) getIntent().getExtras().getSerializable("losDatos");
        Salariow.setText( Float.toString(datos.getSalario()));
        afp.setText( Float.toString(datos.getAfp()));
        isss.setText( Float.toString(datos.getIsss()));
        renta.setText( Float.toString(datos.getRenta()));
        neto.setText( Float.toString(datos.getSalario_neto()));
    }

    public void nuevo(View view) {
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
