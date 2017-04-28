package joseduin.petagram.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.interfaz.ITimeline;
import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.presendador.ITimelinePresenter;
import joseduin.petagram.presendador.TimelinePresenter;

/**
 * Created by Jose on 10/2/2017.
 */

public class Timeline extends Fragment implements ITimeline {

    private RecyclerView recyclerView;
    private ITimelinePresenter iTimelinePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_timeline, container, false);

        enlazarVistaControlador(v);
        iTimelinePresenter = new TimelinePresenter(this, getContext());
        return v;
    }

    private void enlazarVistaControlador(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador mascotaAdaptador) {
        recyclerView.setAdapter(mascotaAdaptador);
    }

    @Override
    public void generarGrid() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
