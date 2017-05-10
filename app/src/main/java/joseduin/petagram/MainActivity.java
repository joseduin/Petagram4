package joseduin.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import joseduin.petagram.Fragment.Perfil;
import joseduin.petagram.Fragment.Timeline;
import joseduin.petagram.adaptador.PagerAdapter;
import joseduin.petagram.restApi.EndPointsApi;
import joseduin.petagram.restApi.adapter.RestApiAdapter;
import joseduin.petagram.restApi.modelo.FirebaseResponse;
import joseduin.petagram.restApi.modelo.InstagramFirebaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView mascotasFav;
    private TabLayout tablayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(toolbar);

        enlazarVistaControlador(toolbar);
        setUpViewPager();

    }

    private void enlazarVistaControlador(Toolbar toolbar) {
        mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        mascotasFav.setOnClickListener(this);
    }

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Timeline());
        fragments.add(new Perfil());

        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), agregarFragment()) {});
        tablayout.setupWithViewPager(viewPager);

        tablayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.pet_house));
        tablayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.pet_face));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contacto:
                Log.d("ir", "contacto");
                iraA(Contacto.class);
                return true;
            case R.id.acerca_de:
                Log.d("ir", "acerca de");
                iraA(Acerca_de.class);
                return true;
            case R.id.configurar_cuenta:
                iraA(ConfigurarCuenta.class);
                return true;
            case R.id.recibir_notificaciones:
                getFirebaseToken();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getFirebaseToken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //enviarTokenRegistro(refreshedToken);
        registrarusuario(refreshedToken);
    }

    private void registrarusuario(String refreshedToken) {
        Toast.makeText(this, "Recibir Notificaciones", Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String id_usuario_instagram = pref.getString("id",  "null");

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiFirebaseInstagram();
        Call<InstagramFirebaseResponse> instagramFirebaseResponseCall =
                endPointsApi.registrarUsuario(refreshedToken, id_usuario_instagram);

        instagramFirebaseResponseCall.enqueue(new Callback<InstagramFirebaseResponse>() {
            @Override
            public void onResponse(Call<InstagramFirebaseResponse> call, Response<InstagramFirebaseResponse> response) {
                InstagramFirebaseResponse instagramFirebaseResponse = response.body();
                Log.d("RESPONSE", response.body().toString());
                Log.d("ID_DISPOSITIVO", instagramFirebaseResponse.getId_dispositivo());
                Log.d("ID_USUARIO_INSTAGRAM", instagramFirebaseResponse.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<InstagramFirebaseResponse> call, Throwable t) {

            }
        });
    }

    private void enviarTokenRegistro(String token) {
        Toast.makeText(this, "Recibir Notificaciones", Toast.LENGTH_SHORT).show();
        Log.d("Refreshed token", token +" .");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionRestApiFirebase();
        Call<FirebaseResponse> firebaseResponseCall = endPointsApi.registrarTokenId(token);

        firebaseResponseCall.enqueue(new Callback<FirebaseResponse>() {
            @Override
            public void onResponse(Call<FirebaseResponse> call, Response<FirebaseResponse> response) {
                FirebaseResponse firebaseResponse = response.body();
                Log.d("FIREBASE_ID", firebaseResponse.getId());
                Log.d("FIREBASE_TOKEN", firebaseResponse.getToken());
            }

            @Override
            public void onFailure(Call<FirebaseResponse> call, Throwable t) {

            }
        });
    }

    private void iraA(Class<?> clase) {
        Intent i = new Intent(MainActivity.this, clase);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mascotasFav:
                iraA(FavoritePets.class);
                break;
        }
    }

}
