package joseduin.petagram.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import joseduin.petagram.R;
import joseduin.petagram.bd.ContructorMascotas;
import joseduin.petagram.modelo.Mascota;

/**
 * Created by Jose on 5/2/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascolas = new ArrayList<>();
    private Context context;

    public MascotaAdaptador(ArrayList<Mascota> mascolas, Context context) {
        this.mascolas = mascolas;
        this.context = context;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_grid_contacto, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascolas.get(position);
        Picasso.with(context)
                .load(mascota.getUrl_foto())
                .into(holder.cardImagen);

//        holder.cardNombre.setText(mascota.getNombre());
        holder.countLikes.setText(String.valueOf(mascota.getLikes()));

        holder.cardImagen.getLayoutParams().width = holder.v.getLayoutParams().width;

        /*holder.addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               like(mascota, holder);
            }
        });*/
    }

    private void like(Mascota mascota, MascotaViewHolder holder) {
        ContructorMascotas constructor = new ContructorMascotas(context);
        constructor.darLikeMascota(mascota);

        holder.countLikes.setText(String.valueOf(constructor.obtenerLikesMascota(mascota)));
    }

    @Override
    public int getItemCount() {
        return mascolas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView cardImagen;
        //private ImageButton addFav, like;
        private TextView cardNombre, countLikes;
        private View v;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            cardImagen = (ImageView) itemView.findViewById(R.id.cardImagen);
            //addFav = (ImageButton) itemView.findViewById(R.id.addFav);
            //like = (ImageButton) itemView.findViewById(R.id.like);
            //cardNombre = (TextView) itemView.findViewById(R.id.cardNombre);
            countLikes = (TextView) itemView.findViewById(R.id.countLikes);
        }
    }
}
