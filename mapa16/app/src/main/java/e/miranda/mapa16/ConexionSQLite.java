package e.miranda.mapa16;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class ConexionSQLite extends SQLiteOpenHelper {

    public  static  final String TABLA = "datos";

    String sqlCreate = "CREATE TABLE datos"  + "  (iddatos INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, la REAL, lo REAL)";

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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

    public ArrayList<Datos> Consultar(Context context){
        ArrayList<Datos> listaDatos = new ArrayList<>();
        MainActivity.listaDatos.clear();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA + " order by nombre",null);
            if(cursor.moveToFirst()){
                do {
                    MainActivity.listaDatos.add(new Datos(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getDouble(3),
                            cursor.getDouble(4)));
                }while (cursor.moveToNext());
            }
            db.close();
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    return listaDatos;

    }

    public  boolean insertar(Datos datos, Context context){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO datos"   + " VALUES (NULL, '"+datos.getNombre()+"', '"+datos.getApellido()+"' , '"+datos.getLa()+"', '"+datos.getLo() +"' )"  );
            db.close();
            return  true;
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public  boolean eliminar(int id){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLA  + " WHERE iddatos="+ id);
            db.close();
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}

