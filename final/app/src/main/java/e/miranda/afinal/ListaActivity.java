package e.miranda.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaActivity extends AppCompatActivity {
    RequestParams parametros;

    Tabla tabla ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        parametros = new RequestParams();

        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);

        llenarDatos("","consultarTodo");

    }



    private void llenarDatos(String criterio, String opcion) {
        //    progress.show();
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "https://www.ma14049.comues.com/accesoremoto/conexion.php";
        parametros.put("criterio" , criterio);
        parametros.put("opcion",opcion);


        client.post(url, parametros, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode ==200){
                   try {

                        JSONArray jsonArray = new JSONArray(new String( responseBody));
                        for (int i = 0 ; i<jsonArray.length(); i++){
                            ArrayList<String> elementos = new ArrayList<String>();

                           elementos.add( jsonArray.getJSONObject(i).getString("codigo"));
                            elementos.add( jsonArray.getJSONObject(i).getString("nombre"));
                            elementos.add( jsonArray.getJSONObject(i).getString("cantidad"));
                            elementos.add( jsonArray.getJSONObject(i).getString("precioMedio"));

                      //      elementos.add("1");

                            tabla.agregarFilaTabla(elementos);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
