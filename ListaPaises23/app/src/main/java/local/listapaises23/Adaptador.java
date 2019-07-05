package local.listapaises23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Pais> {
    public Adaptador(Context context, int resource, ArrayList<Pais> objects) {
        super(context, resource,objects);
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {


        Pais pais = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom, parent, false);

        }
        TextView tvPais = convertView.findViewById(R.id.tvPais);
        TextView tvCapital = convertView.findViewById(R.id.tvCapital);
        ImageView bandera = convertView.findViewById(R.id.img);

        tvPais.setText(pais.getNombre());
        tvCapital.setText(pais.getCapital());
        bandera.setImageResource(pais.getBandera());

        return  convertView;

    }
}
