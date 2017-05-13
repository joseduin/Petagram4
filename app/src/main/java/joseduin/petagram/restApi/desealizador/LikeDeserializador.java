package joseduin.petagram.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import joseduin.petagram.restApi.JsonKeys;
import joseduin.petagram.restApi.modelo.LikeInstagramResponse;

/**
 * Created by Jose on 11/5/2017.
 */

public class LikeDeserializador implements JsonDeserializer<LikeInstagramResponse> {

    @Override
    public LikeInstagramResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int codigo;
        String tipo_error=null;
        String mensaje_error=null;
        JsonObject likeResponseData = json.getAsJsonObject();
        JsonObject likecodigo       = likeResponseData.getAsJsonObject(JsonKeys.META);
        codigo = likecodigo.get(JsonKeys.CODIGO).getAsInt();
        if (codigo!=JsonKeys.CODIGO_OK) {
            tipo_error    = likecodigo.get(JsonKeys.TIPO_ERROR).getAsString();
            mensaje_error = likecodigo.get(JsonKeys.MENSAJE_ERROR).getAsString();
        }

        LikeInstagramResponse respuestaLike = new LikeInstagramResponse();
        respuestaLike.setCodigo(codigo);
        respuestaLike.setTipo_error(tipo_error);
        respuestaLike.setMensaje_error(mensaje_error);
        return respuestaLike;
    }

}
