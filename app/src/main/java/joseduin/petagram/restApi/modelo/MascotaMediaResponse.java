package joseduin.petagram.restApi.modelo;

import java.util.ArrayList;

import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 30/3/2017.
 */

public class MascotaMediaResponse {

    private ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}
