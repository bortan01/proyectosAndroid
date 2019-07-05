package e.miranda.mapa15;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {
    public static final String TABLA = "tcoordenadas";

    String sqlCreate = " Create TABLE " + TABLA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, la REAL, lo REAL )";

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLA);
    }

    public void consultar(){
        MainActivity.coordenadas.clear();
        try{
            SQLiteDatabase db=getReadableDatabase();
            Cursor cursor=db.rawQuery("select * from "+ TABLA + " ORDER BY id ",null);
            if (cursor.moveToFirst()){

                do {
                    MainActivity.coordenadas.add(
                            new Coordenada(
                                    cursor.getInt(0),
                                    cursor.getDouble(1),
                                    cursor.getDouble(2)));

                }while (cursor.moveToNext());

            }
            db.close();
        }catch (Exception e){}

    }

    public boolean insertar(Coordenada coordenada){
        try{
            SQLiteDatabase db=getWritableDatabase();
            db.execSQL("Insert into " + TABLA + " VALUES " + "(NULL, " + coordenada.getLa() + "," + coordenada.getLo() +")");

            db.close();
            return true;
        }catch (Exception e){}
        return false;
    }

    public boolean eliminar(){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(" DELETE FROM "+ TABLA);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
