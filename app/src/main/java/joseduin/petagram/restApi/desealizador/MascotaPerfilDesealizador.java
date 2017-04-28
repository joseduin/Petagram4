package joseduin.petagram.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import joseduin.petagram.modelo.MascotaPerfil;
import joseduin.petagram.restApi.JsonKeys;
import joseduin.petagram.restApi.modelo.MascotaPerfilResponse;

/**
 * Created by Jose on 27/4/2017.
 */

public class MascotaPerfilDesealizador  implements JsonDeserializer<MascotaPerfilResponse> {

    @Override
    public MascotaPerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaPerfilResponse mascotaPerfilResponse = gson.fromJson(json, MascotaPerfilResponse.class);

        JsonObject userData  = json.getAsJsonObject().getAsJsonObject(JsonKeys.USER_DATA);
        Log.d("Perfil", userData.toString());
        String nombre = userData.get(JsonKeys.USER_FULLNAME).getAsString();
        String id = userData.get(JsonKeys.USER_ID).getAsString();
        String urlFoto = userData.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

        MascotaPerfil mascotaPerfil = new MascotaPerfil(nombre, id, urlFoto, null);
        mascotaPerfilResponse.setMascotaPerfil(mascotaPerfil);

        return mascotaPerfilResponse;
    }

}
