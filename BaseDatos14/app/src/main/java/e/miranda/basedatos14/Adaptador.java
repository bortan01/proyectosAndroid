
package e.miranda.basedatos14;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Datos> {
    public Adaptador(Context context, int resource, ArrayList<Datos> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datos datos = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom,parent,false);
            TextView nombre = convertView.findViewById(R.id.nombre);
            TextView apellido = convertView.findViewById(R.id.apellido);
            TextView genero = convertView.findViewById(R.id.genero);
            TextView edad = convertView.findViewById(R.id.edad);
            TextView telefono = convertView.findViewById(R.id.telefono);
            ImageButton btnEdiitar = convertView.findViewById(R.id.btnEditar);

            nombre.setText(datos.getNombre());
            apellido.setText(datos.getApellido());
            genero.setText(datos.getGenero());
            edad.setText(String.valueOf(datos.getEdad()));
            telefono.setText(datos.getTelefono());
            btnEdiitar.setTag(datos.getIddatos());
            return  convertView;
        }
        return  convertView;
    }
}
