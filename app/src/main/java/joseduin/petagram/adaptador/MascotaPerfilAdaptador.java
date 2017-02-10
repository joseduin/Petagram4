package joseduin.petagram.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.modelo.MascotaPerfil;

/**
 * Created by Jose on 10/2/2017.
 */

public class MascotaPerfilAdaptador extends RecyclerView.Adapter<MascotaPerfilAdaptador.MascotaPerfilAdaptadorViewHolder> {

    private ArrayList<MascotaPerfil> mascotas = new ArrayList<>();

    public MascotaPerfilAdaptador(ArrayList<MascotaPerfil> mascolas) {
        this.mascotas = mascolas;
    }

    @Override
    public MascotaPerfilAdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perfil, parent, false);
        return new MascotaPerfilAdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaPerfilAdaptadorViewHolder holder, int position) {
        MascotaPerfil mascota = mascotas.get(position);
        holder.cardImagenPerfil.setImageResource(mascota.getFoto());
        holder.countLikesPerfil.setText(String.valueOf(mascota.getLikes()));

        holder.cardImagenPerfil.getLayoutParams().width = holder.v.getLayoutParams().width;
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
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
