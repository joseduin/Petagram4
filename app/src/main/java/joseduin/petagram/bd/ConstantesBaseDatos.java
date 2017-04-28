package joseduin.petagram.bd;

/**
 * Created by Jose on 17/2/2017.
 */

// final
public final class ConstantesBaseDatos {

    public final static String DATABASE_NAME = "Mascotas";
    public final static int DATABASE_VERSION = 4;

    public final static String TABLE_MASCOTA = "mascota";
    public final static String TABLE_MASCOTA_ID = "id";
    public final static String TABLE_MASCOTA_FOTO = "foto";
    public final static String TABLE_MASCOTA_NOMBRE = "nombre";
    public final static String TABLE_MASCOTA_RAZA = "raza";
    public final static String TABLE_MASCOTA_EDAD = "edad";

    public final static String TABLE_LIKES_MASCOTA = "likes";
    public final static String TABLE_LIKES_MASCOTA_ID = "id";
    public final static String TABLE_LIKES_MASCOTA_ID_MASCOTA = "id_mascota";
    public final static String TABLE_LIKES_MASCOTA_LIKES = "like";

}
