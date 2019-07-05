package local.listapaises23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Pais pais = (Pais) getIntent().getSerializableExtra("obj");
        TextView tvNombre = findViewById(R.id.tvnombre);
        TextView tvCapital = findViewById(R.id.tvCapital);
        ImageView img = findViewById(R.id.imgBandera);

        tvNombre.setText(pais.getNombre());
        tvCapital.setText(pais.getCapital());
        img.setImageResource(pais.getBandera());

    }

    public void onVolver(View view) {
    finish();
    }
}
