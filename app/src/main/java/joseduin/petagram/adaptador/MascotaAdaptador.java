package joseduin.petagram.adaptador;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.bd.ContructorMascotas;
import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.restApi.ConstantesRestApi;
import joseduin.petagram.restApi.EndPointsApi;
import joseduin.petagram.restApi.JsonKeys;
import joseduin.petagram.restApi.adapter.RestApiAdapter;
import joseduin.petagram.restApi.modelo.LikeFirebaseResponse;
import joseduin.petagram.restApi.modelo.LikeInstagramResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 5/2/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascolas = new ArrayList<>();
    private Context context;

    public MascotaAdaptador(ArrayList<Mascota> mascolas, Context context) {
        this.mascolas = mascolas;
        this.context = context;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_grid_contacto, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascolas.get(position);
        Picasso.with(context)
                .load(mascota.getUrl_foto())
                .into(holder.cardImagen);

//        holder.cardNombre.setText(mascota.getNombre());
        holder.countLikes.setText(String.valueOf(mascota.getLikes()));

        holder.cardImagen.getLayoutParams().width = holder.v.getLayoutParams().width;

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               like(mascota, holder);
            }
        });
    }

    private void like(final Mascota mascota, MascotaViewHolder holder) {
        //ContructorMascotas constructor = new ContructorMascotas(context);
        //constructor.darLikeMascota(mascota);

        //holder.countLikes.setText(String.valueOf(constructor.obtenerLikesMascota(mascota)));

        Toast.makeText(context, "Has dado like a " + mascota.getMedia_id() +
                                " del usuario " + mascota.getId(), Toast.LENGTH_SHORT).show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonLikeMedia = restApiAdapter.construyeGsonDeserializadorDarLike();
        EndPointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonLikeMedia);
        final Call<LikeInstagramResponse> likeResponseCall = endpointsApi.postLikeInstagram(mascota.getMedia_id(), ConstantesRestApi.ACCESS_TOKEN);

        likeResponseCall.enqueue(new Callback<LikeInstagramResponse>() {
            @Override
            public void onResponse(Call<LikeInstagramResponse> call, Response<LikeInstagramResponse> response) {
                LikeInstagramResponse informacionrespuestalike = response.body();

                if (informacionrespuestalike!=null) {
                    if (informacionrespuestalike.getCodigo() != JsonKeys.CODIGO_OK) {
                        Log.e("ERROR_DE_LIKE", "Codigo: " + informacionrespuestalike.getCodigo());
                        Log.e("TIPO_ERROR LIKE", informacionrespuestalike.getTipo_error());
                        Log.e("MENSAJE_ERROR_LIKE", informacionrespuestalike.getMensaje_error());
                    } else {

                        SharedPreferences pref = context.getSharedPreferences("usuario", Context.MODE_PRIVATE);
                        String id_usuario_instagram = pref.getString("id", "null");

                        RestApiAdapter restApiAdapter1 = new RestApiAdapter();
                        EndPointsApi endPointsApi = restApiAdapter1.establecerConexionRestApiFirebaseInstagram();
                        Call<LikeFirebaseResponse> registraHerokuLikeResponseCall = endPointsApi.registrarLike(
                                mascota.getMedia_id(), id_usuario_instagram);

                        registraHerokuLikeResponseCall.enqueue(new Callback<LikeFirebaseResponse>() {
                            @Override
                            public void onResponse(Call<LikeFirebaseResponse> call, Response<LikeFirebaseResponse> response) {
                                Log.d("AQUI", response.body().toString());
                                LikeFirebaseResponse likeFirebaseResponse = response.body();
                                Log.d("FIREBASE_ID", likeFirebaseResponse.getId());
                                Log.d("FIREBASE_DISPOSITIVO", likeFirebaseResponse.getId_dispositivo() + "");
                                Log.d("ID_MEDIA", likeFirebaseResponse.getId_foto_instagram());
                                Log.d("ID_USER_LIKE", likeFirebaseResponse.getId_foto_instagram());
                            }

                            @Override
                            public void onFailure(Call<LikeFirebaseResponse> call, Throwable t) {
                                Log.e("ERROR", t.toString());
                            }
                        });

                    }
                } else {
                    if (response.errorBody()!= null) {
                        Log.e("Error en Like",response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<LikeInstagramResponse> call, Throwable t) {
                Log.e("FALLO LA CONEXION2", t.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascolas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImagen;
        private ImageButton addFav, like;
        private TextView cardNombre, countLikes;
        private View v;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            cardImagen = (ImageView) itemView.findViewById(R.id.cardImagen);
            addFav = (ImageButton) itemView.findViewById(R.id.addFav);
            like = (ImageButton) itemView.findViewById(R.id.like);
            //cardNombre = (TextView) itemView.findViewById(R.id.cardNombre);
            countLikes = (TextView) itemView.findViewById(R.id.countLikes);
        }
    }
}
