package joseduin.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joseduin.petagram.modelo.Usuario;

public class ConfigurarCuenta extends AppCompatActivity {

    private TextView text_usuario;
    private Button botonGuardarCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        enlazarVistaControlador();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void enlazarVistaControlador() {
        text_usuario = (TextView) findViewById(R.id.text_usuario);
        botonGuardarCuenta = (Button) findViewById(R.id.botonGuardarCuenta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbarConfigurarCuenta);
        setSupportActionBar(toolbar);

        AppCompatImageView mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);
        mascotasFav.setVisibility(View.GONE);

        botonGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarConfiguracion();
            }
        });
    }

    public void guardarConfiguracion(){
        String username = text_usuario.getText().toString();
        if (username.equals("")) {
            Toast.makeText(this, "Ingrese un nombre de usuario", Toast.LENGTH_LONG).show();
            return;
        }

        Usuario usuario = Usuario.getUserByName(text_usuario.getText().toString());

        if (usuario != null){
            SharedPreferences pref = getSharedPreferences("usuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("nombre", usuario.getNombre());
            editor.putString("id", usuario.getId());
            editor.commit();

            Intent intentPrincipal = new Intent(this, MainActivity.class);
            startActivity(intentPrincipal);
        } else {
            Toast.makeText(this, "No se ha encontrado el usuario sandbox", Toast.LENGTH_LONG).show();
        }
    }

}
