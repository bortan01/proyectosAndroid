package e.miranda.basedatos13;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {
    public static  final  String TABLA = "tfrase";

    String sqlCreate  = "CREATE TABLE " + TABLA + "(idfrase INTEGER PRIMARY KEY AUTOINCREMENT, texto TEXT)";
    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +TABLA);
    }
    public  void consultar (){
        MainActivity.listaFrases.clear();
        try {
            SQLiteDatabase  base = getReadableDatabase();
            Cursor cursor = base.rawQuery("SELECT * FROM " + TABLA, null);

            if(cursor.moveToFirst()){
                do{
                    MainActivity.listaFrases.add(new Frase(cursor.getInt(0), cursor.getString(1)));
                }while (cursor.moveToNext());
            }
            base.close();
        }catch (Exception e){
        }
    }

    public  boolean insertar(Frase frase){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLA + " VALUES "+ "(null,  '"+frase.getTexto().toString()+"' ) ");
            db.close();
            return true;
        }catch (Exception e){
            return  false;
        }
    }
    public  boolean eliminar(int id){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLA + " WHERE idfrase= " + id );
            db.close();
            return true;
        }catch (Exception e){
            return  false;
        }
    }

}
