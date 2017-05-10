package joseduin.petagram.restApi.modelo;

/**
 * Created by Jose on 10/5/2017.
 */

public class InstagramFirebaseResponse {

    private String id_dispositivo;
    private String id_usuario_instagram;

    public InstagramFirebaseResponse(String id_dispositivo, String id_usuario_instagram) {
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
