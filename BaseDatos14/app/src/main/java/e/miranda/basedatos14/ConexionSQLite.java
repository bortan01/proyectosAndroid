package e.miranda.basedatos14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import java.util.ArrayList;

public class ConexionSQLite extends SQLiteOpenHelper {

    public static final String TABLA = "tDatos";

    String sqlCreate = "CREATE TABLE " + TABLA + " ( iddatos INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, apellido TEXT,genero TEXT,edad INTEGER, telefono TEXT)";

    public ConexionSQLite( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA);
    }
    public  boolean insertar (Datos datos){
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLA + " VALUES " + " (NULL, '"+
                    datos.getNombre()+
                    "', '"+datos.getApellido()+
                    "', '"+datos.getGenero()+
                    "', '"+datos.getEdad()+
                    "', '"+datos.getTelefono()+"' )");
        db.close();
        return  true;
        }catch (Exception e){
            return false;
        }
    }
    public  boolean insertar2 (Datos datos){

        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",datos.getNombre());
            values.put("apellido",datos.getApellido());
            values.put("genero",datos.getGenero());
            values.put("edad",datos.getEdad());
            values.put("telefono",datos.getEdad());

            db.insert(TABLA,"nombre",values);
            return  true;


        }catch (Exception e){
            return  false;
        }


    }


    public ArrayList<Datos> consultar (){
        ArrayList<Datos> Id = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA + " ORDER BY nombre " , null);
           if(cursor.moveToFirst()){
                do{
                    Id.add(new Datos(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getString(5)));
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){
        }
        return  Id;
  }
    public  boolean modificar (Datos datos){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE "+TABLA+" set nombre='"+datos.getNombre()+"', apellido='"+datos.getApellido()+"', " +
                    " edad="+datos.getEdad()+", genero='"+datos.getGenero()+"', telefono='"+datos.getTelefono()+"'" +
                    "WHERE iddatos="+datos.getIddatos());
            db.close();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    public  Datos consultar (String id){
        Datos tempp = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from " +TABLA + " where iddatos= '"+id+"' order by nombre ", null);
            if(cursor.moveToFirst()){
                do{
                    tempp = new Datos(cursor.getInt(0),
                            cursor.getString(1),
                                    cursor.getString(2),
                                    cursor.getString(3),
                                    cursor.getInt(4),
                                    cursor.getString(5));
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){
        }
        return tempp;
    }
}
