package joseduin.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import joseduin.petagram.restApi.ConstantesRestApi;
import joseduin.petagram.restApi.EndPointsApi;
import joseduin.petagram.restApi.desealizador.MascotaDesealizador;
import joseduin.petagram.restApi.desealizador.MascotaPerfilDesealizador;
import joseduin.petagram.restApi.modelo.MascotaMediaResponse;
import joseduin.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 30/3/2017.
 */

public class RestApiAdapter {

    // Serializa el JSON
    public EndPointsApi establecerConexionRestApiInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson construyeunGsonDesealizadorMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaMediaResponse.class, new MascotaDesealizador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaPerfilResponse.class, new MascotaPerfilDesealizador());
        return gsonBuilder.create();
    }

}
