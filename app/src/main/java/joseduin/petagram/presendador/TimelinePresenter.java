package joseduin.petagram.presendador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import joseduin.petagram.bd.ContructorMascotas;
import joseduin.petagram.interfaz.ITimeline;
import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.modelo.Usuario;
import joseduin.petagram.restApi.EndPointsApi;
import joseduin.petagram.restApi.adapter.RestApiAdapter;
import joseduin.petagram.restApi.modelo.MascotaMediaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 17/2/2017.
 */

public class TimelinePresenter implements ITimelinePresenter {

    private ITimeline iTimeline;
    private Context context;
    private ContructorMascotas contructorMascotas;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public TimelinePresenter(ITimeline iTimeline, Context context) {
        this.iTimeline = iTimeline;
        this.context = context;
        //obtenerMascotasBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        contructorMascotas = new ContructorMascotas(context);
        mascotas = contructorMascotas.obtenerMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iTimeline.inicializarAdaptadorRV(iTimeline.crearAdaptador(mascotas));
        //iTimeline.generarLayoutVertical();
        iTimeline.generarGrid();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyeunGsonDesealizadorMediaRecent();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gson);

        for (Usuario usuario : Usuario.usuariosSandbox()) {
            Call<MascotaMediaResponse> mascotaResponseCall = endPointsApi.getRecentMediaUser(usuario.getId());
Log.d("User_id", usuario.getId());
            mascotaResponseCall.enqueue(new Callback<MascotaMediaResponse>() {
                @Override
                public void onResponse(Call<MascotaMediaResponse> call, Response<MascotaMediaResponse> response) {
                    MascotaMediaResponse mascotaResponse = response.body();
                    Log.d("Response", response.body().toString());
                    Log.d("Response", response.toString());

                    mascotas.addAll(mascotaResponse.getMascotas());
                    mostrarMascotasRV();
                }

                @Override
                public void onFailure(Call<MascotaMediaResponse> call, Throwable t) {
                    Toast.makeText(context, "Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_SHORT).show();
                    Log.e("ERROR CONEXION", t.toString());
                }
            });
        }
    }
}
