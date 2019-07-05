package local.miselaniaa11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PreferenciasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText nombre,clave,correo,telefono;
    CheckBox chRecordar;
    RadioGroup rg;
    RadioButton administradorR,gerenteR,invitadoR;
    Button btnCrear;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
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

        preferences = getPreferences(MODE_PRIVATE);
        nombre = findViewById(R.id.nombre);
        clave = findViewById(R.id.clave);
        telefono = findViewById(R.id.telefono);
        correo = findViewById(R.id.correo);
        administradorR = findViewById(R.id.administradorR);
        gerenteR = findViewById(R.id.gerenteR);
        invitadoR = findViewById(R.id.invitadoR);
        rg = findViewById(R.id.radioGroup);
        btnCrear = findViewById(R.id.btnCancelar);
        chRecordar = findViewById(R.id.checkRecordar);
        chRecordar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v ).isChecked()){
                    btnCrear.setBackgroundColor(Color.YELLOW);
                    ////cambia el color del boton segun si esta seleccionado o no
                }else{
                    btnCrear.setBackgroundColor(Color.BLUE);
                }
            }
        });
        recuperarPreferencias();


    }
    private void recuperarPreferencias() {
        nombre.setText(preferences.getString("nombre",""));
        clave.setText(preferences.getString("clave",""));
        correo.setText(preferences.getString("correo",""));
        telefono.setText(preferences.getString("telefono","77777777"));
        administradorR.setChecked(preferences.getBoolean("ra",false));
        gerenteR.setChecked(preferences.getBoolean("rge",false));
        invitadoR.setChecked(preferences.getBoolean("ri",false));
        chRecordar.setChecked(preferences.getBoolean("recordar",false));
    }
    public void guardarPreferencias (boolean opcion){
        SharedPreferences.Editor editor = preferences.edit();
        if (opcion){
            editor.putString("nombre",nombre.getText().toString());
            editor.putString("clave",clave.getText().toString());
            editor.putString("telefono ",telefono.getText().toString());
            editor.putString("correo",correo.getText().toString());
            editor.putBoolean("ra",administradorR.isChecked());
            editor.putBoolean("rge",gerenteR.isChecked());
            editor.putBoolean("ri",invitadoR.isChecked());
            editor.putBoolean("recordar",chRecordar.isChecked());
        }else{
            editor.putString("nombre","");
            editor.putString("clave","");
            editor.putString("telefono ","");
            editor.putString("correo","");
            editor.putBoolean("ra",false);
            editor.putBoolean("rge",false);
            editor.putBoolean("ri",false);
            editor.putBoolean("recordar",false);
        }
        editor.commit();
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
        getMenuInflater().inflate(R.menu.preferencias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.guardar) {
            if(nombre.getText().toString().isEmpty() ||
                    clave.getText().toString().isEmpty() ||
                    telefono.getText().toString().isEmpty() ||
                    correo.getText().toString().isEmpty() ||
                    (!administradorR.isChecked() &&
                            !gerenteR.isChecked() &&
                            !invitadoR.isChecked()))
            {
                Toast.makeText(getBaseContext(),"ALGUNOS CAMPOS ESTAN VACIOS",Toast.LENGTH_SHORT).show();
            }else {
                guardarPreferencias(chRecordar.isChecked());
                Toast.makeText(getBaseContext(),"DATOS GUARDADOS",Toast.LENGTH_SHORT).show();
                limpiar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void limpiar() {

        nombre.setText("");
        clave.setText("");
        telefono.setText("");
        correo.setText("");
        rg.clearCheck();
        chRecordar.setChecked(false);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== R.id.Inicio){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
