package joseduin.petagram.adaptador;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.modelo.Mascota;
import joseduin.petagram.modelo.MascotaPerfil;

/**
 * Created by Jose on 10/2/2017.
 */

public class MascotaPerfilAdaptador extends RecyclerView.Adapter<MascotaPerfilAdaptador.MascotaPerfilAdaptadorViewHolder> {

    private MascotaPerfil mascotaPerfil = new MascotaPerfil();
    private FragmentActivity activity;

    public MascotaPerfilAdaptador(MascotaPerfil mascotaPerfil, FragmentActivity activity) {
        this.mascotaPerfil = mascotaPerfil;
        this.activity = activity;
    }

    @Override
    public MascotaPerfilAdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perfil, parent, false);
        return new MascotaPerfilAdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaPerfilAdaptadorViewHolder holder, int position) {
        Mascota mascota = mascotaPerfil.getMascotas().get(position);
        holder.countLikesPerfil.setText(String.valueOf(mascota.getLikes()));

        Picasso.with(activity)
                .load(mascota.getUrl_foto())
                .placeholder(R.drawable.dog_512)
                .into(holder.cardImagenPerfil);
    }

    @Override
    public int getItemCount() {
        return mascotaPerfil.getMascotas().size();
    }


    public static class MascotaPerfilAdaptadorViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImagenPerfil;
        private TextView countLikesPerfil;
        private View v;

        public MascotaPerfilAdaptadorViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            cardImagenPerfil = (ImageView) itemView.findViewById(R.id.cardImagenPerfil);
            countLikesPerfil = (TextView) itemView.findViewById(R.id.countLikesPerfil);
        }
    }
}
