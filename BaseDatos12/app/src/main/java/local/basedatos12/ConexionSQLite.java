package local.basedatos12;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ConexionSQLite extends SQLiteOpenHelper {
public  static  final String TABLA_USUARIO = "tusuario";

    String sqlCreateUsuario = "CREATE TABLE " +TABLA_USUARIO + " (idusuario INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT,  clave TEXT)";

    public ConexionSQLite(Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsuario);
        PoblarDB(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);

    }
    private  void PoblarDB(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + TABLA_USUARIO + " VALUES (NULL, 'root', 'root')"
        );
    }
    public  Usiarios ConsultarUsuario(String user, String pass){
        Usiarios usuario = null ;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_USUARIO + " WHERE USUARIO='"+user+"' AND CLAVE = '"+pass+"'",null);
            if(cursor.moveToFirst()){
                usuario = new Usiarios(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                return usuario;
            }
            db.close();
        }catch (Exception e){
        }
        return  usuario;

    }
    public  boolean insertar(Usiarios usiarios){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO " + TABLA_USUARIO  + " VALUES (NULL,'"+usiarios.getUser().toString()+"','"+usiarios.getUser().toString()+"' )"  );
            db.close();
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}
