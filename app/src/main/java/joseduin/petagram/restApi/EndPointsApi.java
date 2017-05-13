package joseduin.petagram.restApi;

import joseduin.petagram.restApi.modelo.FirebaseResponse;
import joseduin.petagram.restApi.modelo.InstagramFirebaseResponse;
import joseduin.petagram.restApi.modelo.LikeFirebaseResponse;
import joseduin.petagram.restApi.modelo.LikeInstagramResponse;
import joseduin.petagram.restApi.modelo.MascotaMediaResponse;
import joseduin.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jose on 30/3/2017.
 */

public interface EndPointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_SELF)
    Call<MascotaMediaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_INFORMATION_USER)
    Call<MascotaPerfilResponse> getUserInformation(@Path("user-id") String userId);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaMediaResponse> getRecentMediaUser(@Path("user-id") String userId);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<FirebaseResponse> registrarTokenId(@Field("token") String token);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_INSTAGRAM)
    Call<InstagramFirebaseResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo,
                                                     @Field("id_usuario_instagram") String id_usuario_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_REGISTRA_LIKE)
    Call<LikeFirebaseResponse> registrarLike(@Field("id_media_instagram") String id_media_instagram,
                                             @Field("id_instagram") String id_sender_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.URL_POST_LIKE)
    Call<LikeInstagramResponse> postLikeInstagram(@Path("media-id") String id_media_instagram,
                                                  @Field("access_token") String access_token);
}
