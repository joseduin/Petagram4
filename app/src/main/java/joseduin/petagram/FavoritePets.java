package joseduin.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.modelo.Mascota;

public class FavoritePets extends AppCompatActivity {

    private AppCompatImageView mascotasFav;
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView recyclerViewFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbarFav);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewFav = (RecyclerView) findViewById(R.id.recyclerViewFav);
        mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);
        mascotasFav.setVisibility(View.GONE);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFav.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();
    }

    private void inicializarMascotas() {
        mascotas = new ArrayList<>();

        mascotas.add(new Mascota(R.drawable.pet_25_512, "Catty", "Mixto", 2, 5));
        mascotas.add(new Mascota(R.drawable.dog_512, "Scott", "Mixto", 4, 10));
        mascotas.add(new Mascota(R.drawable.dogbread, "Khan", "Terrier", 2, 3));
        mascotas.add(new Mascota(R.drawable.pet_04_512, "Ronny", "Mixto", 1, 7));
        mascotas.add(new Mascota(R.drawable.pet_20_512, "Amber", "Mixto", 6, 8));
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        recyclerViewFav.setAdapter(adaptador);
    }

}
