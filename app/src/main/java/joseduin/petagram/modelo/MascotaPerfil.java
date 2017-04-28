package joseduin.petagram.modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jose on 10/2/2017.
 */

public class MascotaPerfil implements Serializable {

    private String nombre;
    private String id;
    private String urlFoto;
    private ArrayList<Mascota> mascotas;

    public MascotaPerfil(String nombre, String id, String urlFoto, ArrayList<Mascota> mascotas) {
        this.nombre = nombre;
        this.id = id;
        this.urlFoto = urlFoto;
        this.mascotas = mascotas;
    }

    public MascotaPerfil() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
