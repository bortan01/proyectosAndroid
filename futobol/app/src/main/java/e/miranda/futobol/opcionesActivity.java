package e.miranda.futobol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class opcionesActivity extends AppCompatActivity {
    public static ArrayList<ClaseEquipo> listaEquipo;
    public static ArrayList<ClaseJornada> listaJornada;
    Button btnjornada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        listaEquipo = new ArrayList<>();
        listaJornada = new ArrayList<>();
        btnjornada  = findViewById(R.id.jornada);

        if(MainActivity.jornada){
            btnjornada.setEnabled(true);
        }else{
            btnjornada.setEnabled(false);
        }

    }

    public void crearEquipo(View view) {
        Intent intent = new Intent(this, crearEquipoActivity.class);

        startActivity(intent);


    }

    public void generarJornada(View view) {
        MainActivity.conexionSQLite.eliminar_jornadas();
        int numeroEquipos = MainActivity.conexionSQLite.consultarEquipo().size();
        mostrarPartidos(calcularLiga(numeroEquipos));

        btnjornada.setEnabled(false);

    }

    public void Resultados(View view) {
        Intent intent = new Intent(this,ResultadosActivity.class);
        startActivity(intent);
    }

    public void prueba(View view) {
        finish();
    }


    private  Liga.Partido[][] calcularLigaNumEquiposPar(int numEquipos)
    {
        int numRondas = numEquipos - 1;
        int numPartidosPorRonda = numEquipos / 2;

        Liga.Partido[][] rondas = new Liga.Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i ++)
        {
            for (int j = 0; j < numPartidosPorRonda; j ++)
            {
                rondas[i][j] = new Liga.Partido();

                rondas[i][j].local = k;

                k ++;

                if (k == numRondas)
                    k = 0;
            }
        }

        for (int i = 0; i < numRondas; i ++)
        {
            if (i % 2 == 0)
            {
                rondas[i][0].visitante = numEquipos - 1;
            }
            else
            {
                rondas[i][0].visitante = rondas[i][0].local;
                rondas[i][0].local = numEquipos - 1;
            }
        }

        int equipoMasAlto = numEquipos - 1;
        int equipoImparMasAlto = equipoMasAlto - 1;

        for (int i = 0, k = equipoImparMasAlto; i < numRondas; i ++)
        {
            for (int j = 1; j < numPartidosPorRonda; j ++)
            {
                rondas[i][j].visitante = k;

                k --;

                if (k == -1)
                    k = equipoImparMasAlto;
            }
        }

        return rondas;
    }

    private  Liga.Partido[][] calcularLigaNumEquiposImpar(int numEquipos)
    {
        int numRondas = numEquipos;
        int numPartidosPorRonda = numEquipos / 2;

        Liga.Partido[][] rondas = new Liga.Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i ++)
        {
            for (int j = -1; j < numPartidosPorRonda; j ++)
            {
                if (j >= 0)
                {
                    rondas[i][j] = new Liga.Partido();

                    rondas[i][j].local = k;
                }

                k ++;

                if (k == numRondas)
                    k = 0;
            }
        }

        int equipoMasAlto = numEquipos - 1;

        for (int i = 0, k = equipoMasAlto; i < numRondas; i ++)
        {
            for (int j = 0; j < numPartidosPorRonda; j ++)
            {
                rondas[i][j].visitante = k;

                k --;

                if (k == -1)
                    k = equipoMasAlto;
            }
        }

        return rondas;
    }

     public Liga.Partido[][] calcularLiga(int numEquipos)
    {
        if (numEquipos % 2 == 0)
            return calcularLigaNumEquiposPar(numEquipos);
        else
            return calcularLigaNumEquiposImpar(numEquipos);
    }

     public void mostrarPartidos(Liga.Partido[][] rondas)
    {
        Toast.makeText(this, "IDA", Toast.LENGTH_SHORT).show();
        int auxRonda =0;
        for (int i = 0; i < rondas.length; i ++)
        {
            auxRonda = (i+1);
            Toast.makeText(this, "RONDA "+ auxRonda +": ", Toast.LENGTH_SHORT).show();

            for (int j = 0; j < rondas[i].length; j ++)
            {

                String local = MainActivity.conexionSQLite.obtenerNombreEquipo((1 + rondas[i][j].local));
                String visitante = MainActivity.conexionSQLite.obtenerNombreEquipo((1 + rondas[i][j].visitante));
                Toast.makeText(this, local +" vs " + visitante, Toast.LENGTH_SHORT).show();
                MainActivity.conexionSQLite.insertarJornada(new ClaseJornada(0,auxRonda,local,visitante,0,0));
            }
        }
       Toast.makeText(this, "vuelta", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < rondas.length; i ++)
        {
            Toast.makeText(this, "Ronda " + (i+1+auxRonda)+ ": ", Toast.LENGTH_SHORT).show();
            for (int j = 0; j < rondas[i].length; j ++)
            {
                String local = MainActivity.conexionSQLite.obtenerNombreEquipo((1 + rondas[i][j].visitante));
                String visitante = MainActivity.conexionSQLite.obtenerNombreEquipo((1 + rondas[i][j].local));
                Toast.makeText(this, local +" vs " + visitante, Toast.LENGTH_SHORT).show();
                MainActivity.conexionSQLite.insertarJornada(new ClaseJornada(0,(i+1+auxRonda),local,visitante,0,0));
            }
        }
    }


    public void borrarjornadas(View view) {
        AlertDialog.Builder dalogo = new AlertDialog.Builder(this);
        dalogo.setTitle("DESEA ELIMINAR LAS JORNADAS?");

            dalogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.conexionSQLite.eliminar_jornadas();
                }
            });

            dalogo.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dalogo.show();


    }

    public void borrarequipos(View view) {
        AlertDialog.Builder dalogo = new AlertDialog.Builder(this);
        dalogo.setTitle("DESEA ELIMINAR LOS EQUIPOS?");

        dalogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.conexionSQLite.eliminar_equipo();

            }
        });

        dalogo.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dalogo.show();

    }
    public void msg(String texto, String opcion){
    }

}
