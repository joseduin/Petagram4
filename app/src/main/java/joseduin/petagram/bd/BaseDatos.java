package joseduin.petagram.bd;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 17/2/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_RAZA + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_EDAD + " INTEGER " +
                ")";

        String queryCrearTablaLikesMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikesMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + ConstantesBaseDatos.TABLE_MASCOTA + "'");
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMascotasFav5() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        // Obtener las ultimas 5 mascoras a las que se le dio raiting
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID + " DESC" +
                " LIMIT (5)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            Mascota mascota = new Mascota();

            String queryMascota = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_MASCOTA_ID + "=" + registros.getInt(1);
            Cursor reguistrosMascota =  db.rawQuery(queryMascota, null);

            if (reguistrosMascota.moveToNext()) {
                mascota.setId(reguistrosMascota.getInt(0));
                mascota.setFoto(reguistrosMascota.getInt(1));
                mascota.setNombre(reguistrosMascota.getString(2));
                mascota.setRaza(reguistrosMascota.getString(3));
                mascota.setEdad(reguistrosMascota.getInt(4));
            }

            mascota.setLikes(registros.getInt(2));
            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    public ArrayList<Mascota> obtenerTodosLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null); // 2do parametro filtro

        while (registros.moveToNext()) {
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setFoto(registros.getInt(1));
            mascota.setNombre(registros.getString(2));
            mascota.setRaza(registros.getString(3));
            mascota.setEdad(registros.getInt(4));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_LIKES + ") as likes" +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()) {
                mascota.setLikes(registrosLikes.getInt(0));
            } else {
                mascota.setLikes(0);
            }

            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    public void insertarMascotas(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascotas(Mascota mascota) {
        int likes = 0;
        String query = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_LIKES + ") " +
                "FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()) {
            likes = registros.getInt(0);
        }

        db.close();
        return likes;
    }
}
