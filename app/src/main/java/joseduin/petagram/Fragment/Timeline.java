package joseduin.petagram.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 10/2/2017.
 */

public class Timeline extends Fragment{

    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_timeline, container, false);

        enlazarVistaControlador(v);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        inicializarMascotas();
        inicializarAdaptador();

        return v;
    }

    private void enlazarVistaControlador(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
    }

    private void inicializarMascotas() {
        mascotas.add(new Mascota(R.drawable.pet_25_512, "Catty", "Mixto", 2, 5));
        mascotas.add(new Mascota(R.drawable.bulldog, "Zeus", "BullDog", 4, 6));
        mascotas.add(new Mascota(R.drawable.husky, "Tony", "Husky", 2, 10));
        mascotas.add(new Mascota(R.drawable.dog_512, "Scott", "Mixto", 4, 10));
        mascotas.add(new Mascota(R.drawable.dogbread, "Khan", "Terrier", 2, 3));
        mascotas.add(new Mascota(R.drawable.pet_04_512, "Ronny", "Mixto", 1, 7));
        mascotas.add(new Mascota(R.drawable.pet_20_512, "Amber", "Mixto", 6, 8));
    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        recyclerView.setAdapter(adaptador);
    }
}
