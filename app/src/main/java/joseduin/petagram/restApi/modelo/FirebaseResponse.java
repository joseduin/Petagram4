package joseduin.petagram.restApi.modelo;

/**
 * Created by Jose on 9/5/2017.
 */

public class FirebaseResponse {

    private String id;
    private String token;

    public FirebaseResponse(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
