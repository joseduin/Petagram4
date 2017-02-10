package joseduin.petagram.modelo;

/**
 * Created by Jose on 10/2/2017.
 */

public class MascotaPerfil {

    private int foto;
    private int likes;

    public MascotaPerfil(int foto, int likes) {
        this.foto = foto;
        this.likes = likes;
    }

    public MascotaPerfil() {

    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFoto() {
        return foto;
    }

    public int getLikes() {
        return likes;
    }
}
