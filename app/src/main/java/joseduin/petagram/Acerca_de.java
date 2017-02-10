package joseduin.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Acerca_de extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        enlazarVistaControlador();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void enlazarVistaControlador() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbarAcerca_de);
        setSupportActionBar(toolbar);

        AppCompatImageView mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);
        mascotasFav.setVisibility(View.GONE);
    }

}
