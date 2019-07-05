package e.miranda.afinal;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {


    private GoogleMap mMap;
    ArrayList<Datos> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listaDatos = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //if(MainActivity.opcion.equals("verdatos")){
            obtenerDatosJSON("");
        Toast.makeText(this,"dfadf", Toast.LENGTH_SHORT).show();

        for(Datos d: listaDatos){
                                LatLng ubicacion = new LatLng(d.getLa(),d.getLo());
                mMap.addMarker(new MarkerOptions()
                        .position(ubicacion)
                        .title(d.getNombre() + " " + d.getApellido()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,8));
            }
       /* }else{
            LatLng ubicacion = new LatLng(13.637397,-88.787026);
            mMap.addMarker(new MarkerOptions()
                    .position(ubicacion)
                    .title("mi ubicacion")
                    .draggable(true));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,8));
            mMap.setOnMarkerClickListener(this);
            mMap.setOnMarkerDragListener(this);
        }*/



    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "estas en onMarclick ",Toast.LENGTH_SHORT).show();
        MainActivity.latitud = marker.getPosition().latitude;
        MainActivity.longitud = marker.getPosition().longitude;
        setResult(RESULT_OK);
        finish();
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


    public void obtenerDatosJSON (String responde){
        listaDatos.clear();
        try {
            JSONArray jsonArray = new JSONArray(responde);
            for (int i = 0 ; i<jsonArray.length(); i++){
                listaDatos.add(new Datos(
                        jsonArray.getJSONObject(i).getInt("iddatos"),
                        jsonArray.getJSONObject(i).getString("nombre"),
                        jsonArray.getJSONObject(i).getString("apellido"),
                        jsonArray.getJSONObject(i).getDouble("la"),
                        jsonArray.getJSONObject(i).getDouble("lo")
                ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}


