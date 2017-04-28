package joseduin.petagram.modelo;

/**
 * Created by Jose on 5/2/2017.
 */

public class Mascota {

    private int id;
    private int foto;
    private String nombre;
    private String raza;
    private int edad;
    private int likes;

    private String id_insta;
    private String url_foto;

    public Mascota(String id_insta, String nombre, String url_foto, int likes) {
        this.id_insta = id_insta;
        this.nombre = nombre;
        this.url_foto = url_foto;
        this.likes = likes;
    }

    public Mascota(int foto, String nombre, String raza, int edad, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.likes = likes;
    }

    public Mascota() {

    }

    public String getId_insta() {
        return id_insta;
    }

    public void setId_insta(String id_insta) {
        this.id_insta = id_insta;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
