package joseduin.petagram;

import android.content.Intent;
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

import java.util.ArrayList;

import joseduin.petagram.Fragment.Perfil;
import joseduin.petagram.Fragment.Timeline;
import joseduin.petagram.adaptador.PagerAdapter;

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
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), agregarFragment()) {
        });
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
            default:
                return super.onOptionsItemSelected(item);
        }
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
