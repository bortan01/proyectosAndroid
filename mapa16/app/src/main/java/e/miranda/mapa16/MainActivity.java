package e.miranda.mapa16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Datos> listaDatos;
    public static double latitud;
    public static double longitud;
    public static String opcion;
    public static  ConexionSQLite conexionSQLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexionSQLite = new ConexionSQLite(this, "DBDatos", null,3);
        listaDatos = new ArrayList<>();
    }

    public void nuevo(View view) {
        MainActivity.opcion = "";
        Intent intent = new Intent(this,NuevoActivity.class);
        startActivity(intent);
    }

    public void salir(View view) {
        finish();
    }

    public void mapa(View view) {
        MainActivity.opcion = "verdatos";
        Intent intent = new  Intent(this, MapsActivity.class);
          startActivity(intent);
    }

}
