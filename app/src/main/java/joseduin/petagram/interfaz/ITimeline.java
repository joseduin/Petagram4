package joseduin.petagram.interfaz;

import java.util.ArrayList;

import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 17/2/2017.
 */

public interface ITimeline {

    public void generarLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador mascotaAdaptador);

    public void generarGrid();
}
