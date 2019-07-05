package e.miranda.accesoremoto18;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ProgressDialog progress;


    ListView lista;
    EditText filtro, nombre, telefono;
    ArrayList<Datos> listado;
    RequestParams parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parametros = new RequestParams();
        
        lista = findViewById(R.id.lista);
        filtro = findViewById(R.id.filtro);
        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        lista.setOnItemLongClickListener(this);
        listado = new ArrayList<>();


        progress = new ProgressDialog(this);
        progress.setTitle("titulo");
        progress.setMessage("espere...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        ObtenerDatos("ricardo","consultar");
    }

    private void ObtenerDatos(String criterio, String opcion) {
        progress.show();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://www.ma14049.comues.com/accesoremoto/conexion.php";
        //  String url = "http://192.168.30.81/accesoremoto/conexion.php";

        parametros.put("criterio" , criterio);
        parametros.put("opcion",opcion);


        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if(statusCode ==200){
                        ///llamar a funcion
                        llenarlista(obtenerDatosJSON(new String(responseBody)));
                        progress.dismiss();
                    }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void  llenarlista(ArrayList<Datos> datos){
        ArrayAdapter<Datos> adapter = new ArrayAdapter<Datos>(this, android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adapter);
    }

    public  ArrayList<Datos> obtenerDatosJSON (String responde){
        listado.clear();
        try {
            JSONArray jsonArray = new JSONArray(responde);
            for (int i = 0 ; i<jsonArray.length(); i++){
                listado.add(new Datos(
                        jsonArray.getJSONObject(i).getInt("iddatos"),
                        jsonArray.getJSONObject(i).getString("nombre"),
                        jsonArray.getJSONObject(i).getString("telefono")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  listado;
    }

    public void recargar(View view) {
        ObtenerDatos(filtro.getText().toString(), "consultar");
    }

    public void Guardar(View view) {
        if(nombre.getText().toString().isEmpty() || telefono.getText().toString().isEmpty() ){
            Toast.makeText(this, "complete datos ",Toast.LENGTH_SHORT).show();
        }else{
            parametros.put("nombre" , nombre.getText().toString());
            parametros.put("telefono" , telefono.getText().toString());
            ObtenerDatos("", "guardar");

            nombre.setText("");
            telefono.setText("");
            filtro.requestFocus();

            ObtenerDatos(filtro.getText().toString(), "consultar");

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        eliminar(listado.get(position).getIddatos());
        return false;
    }

    public  void eliminar (int id){
        ObtenerDatos(""+ id, "eliminar");
    }
}
