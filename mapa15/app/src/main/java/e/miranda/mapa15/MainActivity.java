package e.miranda.mapa15;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView txtlatitud, txtlongitud, estado;
    Button btnIniciar, btnParar, btnEliminar;
    ListView lista;

    public static ArrayList<Coordenada> coordenadas;
    LocationManager locationManager;

    private Handler handler;
    private Timer temporizador;
    private TimerTask tarea;
    long delay = 5000;
    long periodo = 5000;

    public static ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexionSQLite = new ConexionSQLite(this, "DBC", null, 3);

        txtlatitud = findViewById(R.id.txtlatitud);
        txtlongitud = findViewById(R.id.txtlongitud);
        estado = findViewById(R.id.estado);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnParar = findViewById(R.id.btnParar);
        btnEliminar = findViewById(R.id.btnEliminar);
        lista = findViewById(R.id.lista);
        coordenadas = new ArrayList<>();
        handler = new Handler();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        llenarLista();
    }

    public void eliminar(View view){
        msg("Desea vaciar la base?");
    }
    public void msg(String texto){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Informacion");
        dialogo.setMessage(texto);
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.conexionSQLite.eliminar();
            }
        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogo.show();
    }
    public void inicio (View view){
        btnIniciar.setEnabled(false);
        btnParar.setEnabled(true);

        temporizador = new Timer();
        tarea = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.conexionSQLite.insertar(new Coordenada(Double.parseDouble(txtlatitud.getText().toString()),
                                Double.parseDouble(txtlongitud.getText().toString())));
                        llenarLista();
                    }
                });
            }
        };
        temporizador.schedule(tarea, delay, periodo);
    }

    public void verMapa(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public  void parar(View view){
        temporizador.cancel();
        btnIniciar.setEnabled(true);
        btnParar.setEnabled(false);
    }

    public void llenarLista(){
        MainActivity.conexionSQLite.consultar();
        ArrayAdapter<Coordenada> adapter = new ArrayAdapter<Coordenada>(this, android.R.layout.simple_list_item_1, coordenadas);
        lista.setAdapter(adapter);
    }

    @Override
    public void onLocationChanged(Location location) {
        txtlatitud.setText(String.valueOf(location.getLatitude()));
        txtlongitud.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuffer stringBuffer = new StringBuffer();
        Criteria criteria = new Criteria();

        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        List<String> enabledProviders = locationManager.getProviders(criteria, true);

        if (enabledProviders.isEmpty()){
            estado.setText("Ningun Proovedor Disponible");
        }else {
            for (String enable : enabledProviders){
                stringBuffer.append(enable).append(" ");

             try {

                 locationManager.requestLocationUpdates(enable, 10, 0, this);
             }catch (SecurityException e){
                 Toast.makeText(this,"PEDOS DE SEGURIDAD",Toast.LENGTH_SHORT).show();
             }
            }
            estado.setText(stringBuffer);
        }
        txtlatitud.setText("");
        txtlongitud.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
}
