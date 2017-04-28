package joseduin.petagram.presendador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import joseduin.petagram.interfaz.IPerfil;
import joseduin.petagram.modelo.MascotaPerfil;
import joseduin.petagram.restApi.EndPointsApi;
import joseduin.petagram.restApi.adapter.RestApiAdapter;
import joseduin.petagram.restApi.modelo.MascotaMediaResponse;
import joseduin.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 27/4/2017.
 */

public class PerfilPresenter implements IPerfilPresenter {

    private final IPerfil iPerfilMascotaFragmentView;
    private final Context context;
    private MascotaPerfil perfil;

    public PerfilPresenter(IPerfil iPerfilMascotaFragmentView, Context context) {
        this.iPerfilMascotaFragmentView = iPerfilMascotaFragmentView;
        this.context = context;
        obtenerInformacionUsuario();
    }

    @Override
    public void obtenerInformacionUsuario() {

        SharedPreferences pref = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
        final String userId = pref.getString("id",  "1563600885");

        RestApiAdapter restInstagramApiAdapter = new RestApiAdapter();

        Gson gsonPerfil = restInstagramApiAdapter.construyeGsonDeserializadorPerfil();
        EndPointsApi endPointApi = restInstagramApiAdapter.establecerConexionRestApiInstagram(gsonPerfil);

        Call<MascotaPerfilResponse> perfilResponseCall = endPointApi.getUserInformation(userId);
        perfilResponseCall.enqueue(new Callback<MascotaPerfilResponse>() {

            @Override
            public void onResponse(Call<MascotaPerfilResponse> call, Response<MascotaPerfilResponse> response) {
                MascotaPerfilResponse perfilResponse = response.body();
                perfil = perfilResponse.getMascotaPerfil();
                iPerfilMascotaFragmentView.mostrarPerfil(perfil);
                buscarFotos(userId);
            }

            @Override
            public void onFailure(Call<MascotaPerfilResponse> call, Throwable t) {
                Toast.makeText(context, "Problemas en la conexión al servicio intenta de nuevo", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                Log.e("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }

    private void buscarFotos(String userId) {
        RestApiAdapter restInstagramApiAdapter = new RestApiAdapter();
        Gson gsonImagenesPerfil = restInstagramApiAdapter.construyeunGsonDesealizadorMediaRecent();
        EndPointsApi endPointApiPerfil = restInstagramApiAdapter.establecerConexionRestApiInstagram(gsonImagenesPerfil);
        Call<MascotaMediaResponse> mediaResponseCall = endPointApiPerfil.getRecentMediaUser(userId);
        mediaResponseCall.enqueue(new Callback<MascotaMediaResponse>() {
            @Override
            public void onResponse(Call<MascotaMediaResponse> call, Response<MascotaMediaResponse> response) {
                MascotaMediaResponse mediaResponse = response.body();
                perfil.setMascotas(mediaResponse.getMascotas());
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaMediaResponse> call, Throwable t) {
                Toast.makeText(context, "Problemas en la conexión al servicio intenta de nuevo", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                Log.e("FALLO LA CONEXIÓN", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilMascotaFragmentView.inicializarAdaptadorRV(iPerfilMascotaFragmentView.crearAdaptador(this.perfil));
        iPerfilMascotaFragmentView.generarGridLayout();
    }

}
