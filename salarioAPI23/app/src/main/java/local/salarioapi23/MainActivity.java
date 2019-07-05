package local.salarioapi23;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText salario ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salario = findViewById(R.id.txtSalario);
    }

    public void enviar(View view) {

        if (salario.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"INGRESA TU SALARIO", Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(this, CalculoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
           float number = Float.valueOf(salario.getText().toString());

            salario salario = new salario(number);
            salario.calcular_afp();
            salario.calcular_isss();
            salario.calcular_renta();
            salario.calcular_neto();
            intent.putExtra("losDatos", salario);
            startActivity(intent);
        }
    }
}
