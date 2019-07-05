package local.preferencias09;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario,clave;
    Switch recordar;
    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usuario);
        clave = findViewById(R.id.clave);
        recordar = findViewById(R.id.recordar);

        ///el nombre de la preferencia datos se encuentra en privado por lo tanto
        //solo mi aplicacion los podra leer
        preferencias = getSharedPreferences("datos",Context.MODE_PRIVATE);
        recuperarPreferencias();

    }

    public void GuardarPreferencias(View view) {

        if (recordar.isChecked()) {
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("usuario", usuario.getText().toString());
            editor.putString("clave", clave.getText().toString());
            editor.putBoolean("estado", recordar.isChecked());
            editor.commit();
            Toast.makeText(getBaseContext(), "PREFERENCIAS GUARDADAS", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "PREFERENCIAS NO GUARDADAS", Toast.LENGTH_SHORT).show();
        }
    }
    private void recuperarPreferencias() {
        String txtUsuario = preferencias.getString("usuario","");
        String txtClave = preferencias.getString("clave","");
        boolean estado = preferencias.getBoolean("estado",false);

        if(estado){
            usuario.setText(txtUsuario);
            clave.setText(txtClave);
            recordar.setChecked(true);
        }
    }
}
