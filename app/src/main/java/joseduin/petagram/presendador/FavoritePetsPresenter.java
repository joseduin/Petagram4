package joseduin.petagram.presendador;

import android.content.Context;

import java.util.ArrayList;

import joseduin.petagram.bd.ContructorMascotas;
import joseduin.petagram.interfaz.ITimeline;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 17/2/2017.
 */

public class FavoritePetsPresenter implements ITimelinePresenter {

    private ITimeline iTimeline;
    private Context context;
    private ContructorMascotas contructorMascotas;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public FavoritePetsPresenter(ITimeline iTimeline, Context context) {
        this.iTimeline = iTimeline;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        contructorMascotas = new ContructorMascotas(context);
        mascotas = contructorMascotas.obtenerMascotasFav5();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iTimeline.inicializarAdaptadorRV(iTimeline.crearAdaptador(mascotas));
        iTimeline.generarLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {

    }
}
