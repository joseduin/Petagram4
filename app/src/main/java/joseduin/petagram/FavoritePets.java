package joseduin.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.interfaz.ITimeline;
import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.presendador.FavoritePetsPresenter;
import joseduin.petagram.presendador.ITimelinePresenter;

public class FavoritePets extends AppCompatActivity implements ITimeline {

    private AppCompatImageView mascotasFav;
    private RecyclerView recyclerViewFav;
    private TextView favNotMacht;
    private ITimelinePresenter iTimelinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbarFav);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        enlazarVistaControlador(toolbar);

        iTimelinePresenter = new FavoritePetsPresenter(this, getApplicationContext());
    }

    private void enlazarVistaControlador(Toolbar toolbar) {
        favNotMacht = (TextView) findViewById(R.id.favNotMacht);
        recyclerViewFav = (RecyclerView) findViewById(R.id.recyclerViewFav);
        mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);

        favNotMacht.setVisibility(View.GONE);
        mascotasFav.setVisibility(View.GONE);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFav.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getApplicationContext());

        if (mascotas.isEmpty()) {
            favNotMacht.setVisibility(View.VISIBLE);
        }

        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador mascotaAdaptador) {
        recyclerViewFav.setAdapter(mascotaAdaptador);
    }

    @Override
    public void generarGrid() {

    }
}
