package local.usandolistaapi23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public  class myAdapter extends BaseAdapter {


    private Context context;
    private  int layout;
    private List<String> names ;


    public myAdapter(Context context, int layout, List<String> names) {
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
       return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return  this.names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

     viewHolder holder;

     if (convertView == null){
         //inclamos la vista que nos ha llegado con nueestro layout personalizado
         LayoutInflater layoutInflater = LayoutInflater.from(this.context);
         convertView = layoutInflater.inflate(R.layout.list_item,null);

         holder = new viewHolder();
         //referenciamos el elemento a modificar y lo llenamos
         holder.nameTexView = convertView.findViewById(R.id.textView);
         convertView.setTag(holder);

     }else{
         holder =(viewHolder) convertView.getTag();
     }

        // nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);

     ///referenciamos el elemnto a modificar y lo rellenamos
        holder.nameTexView.setText(currentName);

        //devolvemos la vista inflada y modificada con nuestros datos
        return convertView;

    }

    static  class viewHolder {
        ///tantos elementos como tenga nuestro item
        private  TextView nameTexView ;
    }
}