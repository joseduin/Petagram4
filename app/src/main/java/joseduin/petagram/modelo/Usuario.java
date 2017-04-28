package joseduin.petagram.modelo;

import java.util.ArrayList;

/**
 * Created by Jose on 27/4/2017.
 */

public class Usuario {

    private String id;
    private String nombre;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static ArrayList<Usuario> usuariosSandbox() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("1563600885", "jseduin"));
        //usuarios.add(new Usuario("801027550", "patriciafreitezduarte"));
        usuarios.add(new Usuario("4801986435", "juanadeveloper"));

        return usuarios;
    }

    public static Usuario getUserByName(String username) {
        Usuario usuario = new Usuario();
        for (Usuario usu : usuariosSandbox()) {
            if (usu.getNombre().equalsIgnoreCase(username)) {
                usuario = usu;
                break;
            }
        }
        return usuario;
    }
}
