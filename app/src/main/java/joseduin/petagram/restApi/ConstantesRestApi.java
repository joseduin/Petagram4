package joseduin.petagram.restApi;

/**
 * Created by Jose on 30/3/2017.
 */

// final
public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "1563600885.1b58a20.aff603d2a3d048c18de76290f7a627db";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";

    public static final String KEY_GET_RECENT_MEDIA_SELF = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_SELF = KEY_GET_RECENT_MEDIA_SELF + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/{user-id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_INFORMATION_USER = "users/{user-id}/";
    public static final String URL_GET_INFORMATION_USER = KEY_GET_INFORMATION_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}
