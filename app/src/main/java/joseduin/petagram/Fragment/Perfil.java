package joseduin.petagram.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.adaptador.MascotaAdaptador;
import joseduin.petagram.adaptador.MascotaPerfilAdaptador;
import joseduin.petagram.interfaz.IPerfil;
import joseduin.petagram.modelo.MascotaPerfil;
import joseduin.petagram.presendador.IPerfilPresenter;
import joseduin.petagram.presendador.PerfilPresenter;

public class Perfil extends Fragment implements IPerfil {

    private CircularImageView fotoPerfil;
    private TextView nombrePerfil;
    private RecyclerView fotosPerfil;
    private IPerfilPresenter iPerfilPresenter;
    private ArrayList<MascotaPerfil> fotos_de_muro = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_perfil, container, false);

        enlazarVistaControlador(v);
        //cargarFotos();
        iPerfilPresenter = new PerfilPresenter(this, getContext());
        return v;
    }

    /*
    private void cargarFotos() {
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 10));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 5));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 7));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 9));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 12));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 1));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 5));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 2));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 10));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 96));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 27));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 8));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 13));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 32));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 24));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 82));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 16));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 21));
        fotos_de_muro.add(new MascotaPerfil(R.drawable.bulldog, 29));
    }*/

    private void enlazarVistaControlador(View v) {
        fotoPerfil = (CircularImageView) v.findViewById(R.id.fotoPerfil);
        nombrePerfil = (TextView) v.findViewById(R.id.nombrePerfil);
        fotosPerfil = (RecyclerView) v.findViewById(R.id.fotosPerfil);
    }

    @Override
    public void generarGridLayout() {
        fotosPerfil.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    @Override
    public MascotaPerfilAdaptador crearAdaptador(MascotaPerfil perfil) {
        return new MascotaPerfilAdaptador(perfil, getActivity());
    }

    @Override
    public void inicializarAdaptadorRV(MascotaPerfilAdaptador perfilAdaptador) {
        fotosPerfil.setAdapter(perfilAdaptador);
    }

    @Override
    public void mostrarPerfil(MascotaPerfil perfil) {
        nombrePerfil.setText(perfil.getNombre());
        Picasso.with(getContext())
                .load(perfil.getUrlFoto())
                .placeholder(R.drawable.dog_512)
                .into(fotoPerfil);

    }

}
