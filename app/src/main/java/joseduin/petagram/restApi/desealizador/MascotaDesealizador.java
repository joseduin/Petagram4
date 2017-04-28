package joseduin.petagram.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.restApi.JsonKeys;
import joseduin.petagram.restApi.modelo.MascotaMediaResponse;

/**
 * Created by Jose on 1/4/2017.
 */

public class MascotaDesealizador implements JsonDeserializer<MascotaMediaResponse> {

    @Override
    public MascotaMediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaMediaResponse mascotaResponse = gson.fromJson(json, MascotaMediaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(desealizarMascotaDeJson(mascotaResponseData));

        return mascotaResponse;
    }

    private ArrayList<Mascota> desealizarMascotaDeJson(JsonArray mascotaResponseData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
Log.d("Media", mascotaResponseDataObject.toString());
            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String full_name = userJson.get(JsonKeys.USER_FULLNAME).getAsString();


            JsonObject imagesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject standardJson = imagesJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String url_foto = standardJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();
            mascotas.add(new Mascota(id, full_name, url_foto, likes));
        }
        return mascotas;
    }
}
