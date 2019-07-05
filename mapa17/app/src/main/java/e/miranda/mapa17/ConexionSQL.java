package e.miranda.mapa17;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQL extends SQLiteOpenHelper {
public static  final String TABLA = "informacion";

//String sqlCreate = " create table " + TABLA + " (ID integer primary key autoincrement, nombre text , apellido text, la real , lo real , fecha text)";

    String sqlCreate = "create table " + TABLA + " (id integer primary key autoincrement,nombre text , apellido text, la real,lo real, fecha text )";
    public ConexionSQL(Context context,String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLA);
    }

    public  void consultar (){
      //  MainActivity.l


    }


}
