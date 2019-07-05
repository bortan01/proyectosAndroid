package local.interfazgrafica02;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity


    implements NavigationView.OnNavigationItemSelectedListener {
    EditText texto;
    Spinner spinner;
    ArrayList<String> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Resources res = this.getResources();

        texto = findViewById(R.id.texto);
        spinner = findViewById(R.id.spiner);
        lista = new ArrayList<>();


        lista.add("Enero");
        lista.add("Febrero");
        lista.add("Marzo");
        lista.add("Abril");
        lista.add("Mayo");
        lista.add("Junio");
        lista.add("Julio");
        lista.add("Agosto");
        lista.add("Septiembre");
        lista.add("Octubre");
        lista.add("Noviembre");
        lista.add("Diciembre");

        llenarSpiner();
        }

    private void llenarSpiner() {

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.meses,  android.R.layout.simple_list_item_1 );
        spinner.setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id  == R.id.nav_Agregar){
            finish();
            startActivity(new Intent(getBaseContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP  ));
        }else if (id == R.id.nav_recurso){
            finish();
            startActivity(new Intent(getBaseContext(),RecursoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP  ));
        }else if (id == R.id.nav_salir){
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void Agregar(View view) {

        if (texto.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(),"INGRESAR TEXTO", Toast.LENGTH_LONG).show();
            Toast.makeText(this,"EL ELEMENTO ESTA VACIO",Toast.LENGTH_SHORT).show();
        }else{


            lista.add(texto.getText().toString());
           // llenarSpiner();
            ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1,lista);
            spinner.setAdapter(adapter);

            texto.setText("");
            texto.requestFocus();
            Toast.makeText(this,"EL ELEMENTO AGREGADO A LA LISTA",Toast.LENGTH_SHORT).show();

        }


    }
}