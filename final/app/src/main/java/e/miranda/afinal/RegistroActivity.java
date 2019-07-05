package e.miranda.afinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.ProgressDialog;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class RegistroActivity extends AppCompatActivity {

    TextInputLayout codigo,nombre;
    ListView lista;
   // ProgressDialog progress;
    RequestParams parametros;
    ArrayList<Datos> listado;
    Button guardar ;
    Button generarCodigo ;
    public static  int cantidad = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.apellido);
        codigo = findViewById(R.id.codigo);
        parametros = new RequestParams();
        listado = new ArrayList<>();
        codigo.setEnabled(false);
        guardar = findViewById(R.id.boton5);
        generarCodigo = findViewById(R.id.boton6);

        guardar.setEnabled(false);

    }

    public void obtener(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivityForResult(intent,1);
    }

    public void guardar(View view) {
        if(nombre.getEditText().getText().toString().equals("")){
            nombre.setError("digite un nombre");
        }else if(codigo.getEditText().getText().toString().equals("")){
            codigo.setError("digite un codigo");
            nombre.setErrorEnabled(false);
        }else {


            parametros.put("nombre" , nombre.getEditText().getText().toString());
            parametros.put("codigo" , codigo.getEditText().getText().toString());


            ObtenerDatos("", "guardar");
            msg("datos almacenados");
            limpiar();
            guardar.setEnabled(false);
     //       finish();
     //       Intent intent = new  Intent(this, MainActivity.class);
     //       startActivity(intent);

        }
    }

    private void limpiar() {

        codigo.getEditText().setText("");
        nombre.getEditText().setText("");

    }


    boolean verificar(TextInputLayout input, String error ){
        if(input.getEditText().getText().toString().equals("")){
            input.setError(error);
            return false;
        }else{
            input.setError("");
            return  true;
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1){
            if(resultCode == RESULT_OK){
   //             latitud.getEditText().setText("" + MainActivity.latitud);
   //             longitud.getEditText().setText("" + MainActivity.longitud);
            }
        }

    }

    public  void menu(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }


    private void ObtenerDatos(String criterio, String opcion) {
      //  progress.show();
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "https://www.ma14049.comues.com/accesoremoto/conexion.php";
        parametros.put("criterio" , criterio);
        parametros.put("opcion",opcion);


        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode ==200){

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
     //   listado.clear();
        try {
            JSONArray jsonArray = new JSONArray(responde);
            for (int i = 0 ; i<jsonArray.length(); i++){
                listado.add(new Datos(
                        jsonArray.getJSONObject(i).getInt("iddatos"),
                        jsonArray.getJSONObject(i).getString("nombre"),
                        jsonArray.getJSONObject(i).getString("apellido"),
                        jsonArray.getJSONObject(i).getDouble("la"),
                        jsonArray.getJSONObject(i).getDouble("lo")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  listado;
    }

    public void onGenerar(View view) {
        String cadena = nombre.getEditText().getText().toString();
        if(cadena.length() <3){
            Toast.makeText(this, "nombre muy corto", Toast.LENGTH_SHORT).show();
        }else{
            String iniciales = cadena.substring(0,1) + cadena.substring(cadena.length()-1, cadena.length());
           ///verificamos cuantos codigos inician con la primera y ultima letra de nuesstro producto
            /// auntomaticamente se guardara la cantidad en la variable global cantidad
            consultaCantidad(iniciales,"consultarCantiidad");

             if(cantidad >999 ){
                iniciales = iniciales + (cantidad +1);
            }else if(cantidad > 99){
                iniciales = iniciales + "0" + (cantidad +1);
            }else if(cantidad >9){
                iniciales = iniciales + "00" + (cantidad+1);
            }else{
                iniciales = iniciales + "000" + (cantidad+1);
            }
            codigo.getEditText().setText(iniciales);
             guardar.setEnabled(true);
             cantidad = 0 ;

        }
    }


    private void consultaCantidad(String criterio, String opcion) {
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
                            cantidad = jsonArray.getJSONObject(i).getInt("cantidad");

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
