package joseduin.petagram.restApi;

import joseduin.petagram.restApi.modelo.MascotaMediaResponse;
import joseduin.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Call;
import retrofit2.http.GET;
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

}
