package joseduin.petagram.restApi.modelo;

/**
 * Created by Jose on 11/5/2017.
 */

public class LikeFirebaseResponse {

    private String id;
    private String id_dispositivo;
    private String id_foto_instagram;
    private String id_instagram;

    public LikeFirebaseResponse(String id, String id_dispositivo, String id_foto_instagram, String id_instagram) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_foto_instagram = id_foto_instagram;
        this.id_instagram = id_instagram;
    }

    public LikeFirebaseResponse() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }

    public String getId_instagram() {
        return id_instagram;
    }

    public void setId_instagram(String id_instagram) {
        this.id_instagram = id_instagram;
    }
}
