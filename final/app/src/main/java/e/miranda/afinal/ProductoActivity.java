package e.miranda.afinal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ProductoActivity extends AppCompatActivity {

    TextInputLayout cantidad,precio;
    ListView lista;
    ProgressDialog progress;
    RequestParams parametros;
    ArrayList<producto> listado;
    Spinner comboProducto ;
    String codigo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        comboProducto = findViewById(R.id.producto);
        listado = new ArrayList<>();
        parametros = new RequestParams();
        progress = new ProgressDialog(this);
        progress.setTitle("Cargando Datos");
        progress.setMessage("espere...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(true);
        ObtenerDatos("","consultar");
        codigo = "";
        cantidad = findViewById(R.id.codigo);
        precio = findViewById(R.id.apellido);
    }

    public void guardar(View view) {

        if(cantidad.getEditText().getText().toString().equals("")){
            cantidad.setError("digite una cantidad");
        }else if(precio.getEditText().getText().toString().equals("")){
            precio.setError("digite un codigo");
            precio.setErrorEnabled(false);
        }else {
            String combo =  comboProducto.getSelectedItem().toString();

            parametros.put("codigo" ,  combo.substring(0,6));
            parametros.put("cantidad" , cantidad.getEditText().getText().toString());
            parametros.put("precio" , precio.getEditText().getText().toString());


           ObtenerDatos("", "guardarcompra");
            msg("datos almacenados");
            cantidad.setError("");
            precio.setError("");
            limpiar();

        }

    }

    private void ObtenerDatos(String criterio, String opcion) {
    //    progress.show();
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "https://www.ma14049.comues.com/accesoremoto/conexion.php";
        parametros.put("criterio" , criterio);
        parametros.put("opcion",opcion);


        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode ==200){
                    ///llamar a funcion
                    llenarlista(obtenerDatosJSON(new String(responseBody)));
  //                  progress.dismiss();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void  llenarlista(ArrayList<producto> datos){

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);
        comboProducto.setAdapter(adapter);
    }

    public  ArrayList<producto> obtenerDatosJSON (String responde){
        listado.clear();

        try {
            JSONArray jsonArray = new JSONArray(responde);
            for (int i = 0 ; i<jsonArray.length(); i++){
                listado.add(new producto(
                        jsonArray.getJSONObject(i).getString("codigo"),
                        jsonArray.getJSONObject(i).getString("nombre")));

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  listado;
    }

    private void limpiar() {

        cantidad.getEditText().setText("");
        precio.getEditText().setText("");

    }

    private void msg(String texto) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("INFORMACION");
        dialogo.setMessage(texto);


        dialogo.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ////aqui se pone la accion si da click en aceptar
            }
        });

        dialogo.show();
    }

}
