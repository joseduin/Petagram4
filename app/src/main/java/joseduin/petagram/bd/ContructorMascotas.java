package joseduin.petagram.bd;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 17/2/2017.
 */

// Interactor
public class ContructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ContructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerMascotasFav5() {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFav5();
    }

    public ArrayList<Mascota> obtenerMascotas() {
        BaseDatos db = new BaseDatos(context);

        // Se carguen los datos 'dummy' solo 1 vez
        if (db.obtenerTodosLasMascotas().isEmpty()) {
            insertarMascotas(db);
        }
        return db.obtenerTodosLasMascotas();
    }

    public void insertarMascotas(BaseDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.pet_25_512);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Zeus");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "BullDog");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 4);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.bulldog);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Catty");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Mixto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.husky);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Tony");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Husky");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dog_512);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Scott");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Mixto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 4);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.dogbread);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Khan");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Terrier");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 2);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.pet_04_512);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Ronny");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Mixto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 1);
        db.insertarMascotas(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.pet_20_512);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, "Amber");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_RAZA, "Mixto");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_EDAD, 6);
        db.insertarMascotas(contentValues);
    }

    public void darLikeMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota) {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotas(mascota);
    }
}
