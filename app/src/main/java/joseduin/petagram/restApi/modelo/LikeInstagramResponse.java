package joseduin.petagram.restApi.modelo;

/**
 * Created by Jose on 11/5/2017.
 */

public class LikeInstagramResponse {

    private int codigo;
    private String tipo_error;
    private String mensaje_error;

    public LikeInstagramResponse(int codigo, String tipo_error, String mensaje_error) {
        this.codigo = codigo;
        this.tipo_error = tipo_error;
        this.mensaje_error = mensaje_error;
    }

    public LikeInstagramResponse() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo_error() {
        return tipo_error;
    }

    public void setTipo_error(String tipo_error) {
        this.tipo_error = tipo_error;
    }

    public String getMensaje_error() {
        return mensaje_error;
    }

    public void setMensaje_error(String mensaje_error) {
        this.mensaje_error = mensaje_error;
    }

}
