package joseduin.petagram.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 5/2/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascolas = new ArrayList<>();
    private boolean like_validation = false;

    public MascotaAdaptador(ArrayList<Mascota> mascolas) {
        this.mascolas = mascolas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dog, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        Mascota mascota = mascolas.get(position);
        holder.cardImagen.setImageResource(mascota.getFoto());
        holder.cardNombre.setText(mascota.getNombre());
        holder.countLikes.setText(String.valueOf(mascota.getLikes()));

        holder.cardImagen.getLayoutParams().width = holder.v.getLayoutParams().width;

        holder.addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               like(holder);
            }
        });
    }

    private void like(MascotaViewHolder holder) {
        int likes = Integer.valueOf(holder.countLikes.getText().toString());
        likes += (like_validation) ? -1 : 1;
        holder.countLikes.setText(String.valueOf(likes));
        like_validation = !like_validation;
    }

    @Override
    public int getItemCount() {
        return mascolas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImagen;
        private ImageButton addFav, like;
        private TextView cardNombre, countLikes;
        private View v;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            cardImagen = (ImageView) itemView.findViewById(R.id.cardImagen);
            addFav = (ImageButton) itemView.findViewById(R.id.addFav);
            like = (ImageButton) itemView.findViewById(R.id.like);
            cardNombre = (TextView) itemView.findViewById(R.id.cardNombre);
            countLikes = (TextView) itemView.findViewById(R.id.countLikes);
        }
    }
}
