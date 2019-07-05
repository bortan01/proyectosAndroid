package e.miranda.futobol;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ConexionSQLite extends SQLiteOpenHelper {

    public static final String TABLA_EQUIPO = "tequipo";
    public static final String TABLA_JORNADA = "jornada";

    String sqlCreateEquipo = "CREATE TABLE " +TABLA_EQUIPO + " (idequipo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, puntos INT)";
    String sqlCreateJornada = "CREATE TABLE " +TABLA_JORNADA + " (idjornada INTEGER PRIMARY KEY AUTOINCREMENT, numero INT, equipoc TEXT, equipov TEXT, golc int,golv int)";


    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateJornada);
        db.execSQL(sqlCreateEquipo);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertarEquipo(ClaseEquipo equipo){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLA_EQUIPO + " VALUES "+ "(null,'"+equipo.getNombre().toString()+"',0 ) ");
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public  boolean insertarJornada(ClaseJornada jornada){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLA_JORNADA + " VALUES "+ "(null,'"+jornada.getNumero()+"' , '"+jornada.getIdquipoc().toString()+"', '"+jornada.getEquipov().toString()+"', 0,0 ) ");
            db.close();
            return true;
        }catch (Exception e){
            Log.i("id",e.getMessage());
            return false;
        }
    }


    public ArrayList<ClaseEquipo> consultarEquipo() {

    ArrayList<ClaseEquipo> lista = new ArrayList<>();
        try {
            SQLiteDatabase base = getReadableDatabase();
            Cursor cursor = base.rawQuery("SELECT * FROM " + TABLA_EQUIPO, null);
            if(cursor.moveToFirst()){
                do{
                    lista.add(new ClaseEquipo(cursor.getInt(0), cursor.getString(1),cursor.getInt(2)));
                }while (cursor.moveToNext());
            }
            base.close();
            return  lista;
        }catch (Exception e){
            return  null;
        }
    }

    public String obtenerNombreEquipo(int id) {

        String nombre ="";
        try {
            SQLiteDatabase base = getReadableDatabase();
            Cursor cursor = base.rawQuery("SELECT nombre FROM " + TABLA_EQUIPO + " where idequipo = '"+id+"'", null);
            if(cursor.moveToFirst()){
                do{
                    nombre = cursor.getString(0);
                }while (cursor.moveToNext());
            }
            base.close();
            return  nombre;
        }catch (Exception e){
            return  null;
        }
    }



    public ArrayList<ClaseJornada> consultarJornada() {

        ArrayList<ClaseJornada> lista = new ArrayList<>();
        try {
            SQLiteDatabase base = getReadableDatabase();
            Cursor cursor = base.rawQuery("SELECT * FROM " + TABLA_JORNADA , null);
            if(cursor.moveToFirst()){
                do{
                    lista.add(new ClaseJornada(cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5)));

                }while (cursor.moveToNext());
            }
            base.close();
            return  lista;
        }catch (Exception e){
            return  null;
        }
    }

    public ArrayList<ClaseJornada> consultarPartido(int id) {

        ArrayList<ClaseJornada> lista = new ArrayList<>();
        try {
            SQLiteDatabase base = getReadableDatabase();
            Cursor cursor = base.rawQuery("SELECT * FROM  jornada where numero = '"+id+"' " , null);
            if(cursor.moveToFirst()){
                do{
                    lista.add(new ClaseJornada(cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5)));

                }while (cursor.moveToNext());
            }
            base.close();
            return  lista;
        }catch (Exception e){
            return  null;
        }
    }



    public ArrayList<String> numeroJornadas() {

        ArrayList<String> lista = new ArrayList<>();
        try {
            SQLiteDatabase base = getReadableDatabase();
            Cursor cursor = base.rawQuery("select numero from jornada group by numero", null);
            if(cursor.moveToFirst()){
                do{
                    lista.add((cursor.getString(0)));
                }while (cursor.moveToNext());
            }
            base.close();
            return  lista;
        }catch (Exception e){
            return  null;
        }
    }



    public boolean eliminar_jornadas(){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLA_JORNADA);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean eliminar_equipo(){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLA_EQUIPO);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean modificar (ClaseJornada jornada){
        try {
        int golcasa = jornada.getGolc();
        int golvisita = jornada.getGolv();

        if(golcasa>golvisita){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE "+TABLA_JORNADA+" set golc='"+jornada.getGolc()+"',golv='"+jornada.getGolv()+"'" + " WHERE idjornada="+jornada.getIdjornada());
            db.execSQL("UPDATE "+TABLA_EQUIPO+" set puntos= (puntos+3) WHERE nombre="+jornada.getIdquipoc());
            db.close();
            return true;
        }else if (golcasa>golvisita){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE "+TABLA_JORNADA+" set golc='"+jornada.getGolc()+"',golv='"+jornada.getGolv()+"'" + " WHERE idjornada="+jornada.getIdjornada());
            db.execSQL("UPDATE "+TABLA_EQUIPO+" set puntos= (puntos+3) WHERE nombre="+jornada.getEquipov());
            db.close();
            return true;
        }else{
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE "+TABLA_JORNADA+" set golc='"+jornada.getGolc()+"',golv='"+jornada.getGolv()+"'" + " WHERE idjornada="+jornada.getIdjornada());
            db.execSQL("UPDATE "+TABLA_EQUIPO+" set puntos= (puntos+1) WHERE nombre="+jornada.getEquipov());
            db.execSQL("UPDATE "+TABLA_EQUIPO+" set puntos= (puntos+1) WHERE nombre="+jornada.getIdquipoc());
        }
         }catch(Exception e) {
            return false;
        }
        return false;
    }
}

