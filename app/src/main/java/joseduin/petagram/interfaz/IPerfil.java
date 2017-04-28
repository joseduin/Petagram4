package joseduin.petagram.interfaz;

import joseduin.petagram.adaptador.MascotaPerfilAdaptador;
import joseduin.petagram.modelo.MascotaPerfil;

/**
 * Created by Jose on 27/4/2017.
 */

public interface IPerfil {

    public void generarGridLayout();

    public MascotaPerfilAdaptador crearAdaptador(MascotaPerfil perfil);

    public void inicializarAdaptadorRV(MascotaPerfilAdaptador perfilAdaptador);

    public void mostrarPerfil(MascotaPerfil perfil);

}
