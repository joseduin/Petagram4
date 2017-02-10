package joseduin.petagram;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import joseduin.petagram.JavaMail.SendMail;

public class Contacto extends AppCompatActivity implements View.OnClickListener {

    private Button enviarComentario;
    private TextInputLayout inputLayoutNombre, inputLayoutCorreo, inputLayoutMensaje;
    private EditText textNombre, textCorreo, textMensaje;
    private boolean camposVacios = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        enlazarVistaControlador();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void enlazarVistaControlador() {
        enviarComentario = (Button) findViewById(R.id.enviarComentario);

        inputLayoutNombre = (TextInputLayout) findViewById(R.id.inputLayoutNombre);
        inputLayoutCorreo = (TextInputLayout) findViewById(R.id.inputLayoutCorreo);
        inputLayoutMensaje = (TextInputLayout) findViewById(R.id.inputLayoutMensaje);

        textNombre = (EditText) findViewById(R.id.textNombre);
        textCorreo = (EditText) findViewById(R.id.textCorreo);
        textMensaje = (EditText) findViewById(R.id.textMensaje);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionbarContacto);
        setSupportActionBar(toolbar);

        AppCompatImageView mascotasFav = (AppCompatImageView) toolbar.findViewById(R.id.mascotasFav);
        mascotasFav.setVisibility(View.GONE);

        enviarComentario.setOnClickListener(this);
    }

    private void enviarMensaje() {

        String to = textCorreo.getText().toString().trim();
        String subject = textNombre.getText().toString().trim();
        String msgText = textMensaje.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, to, subject, msgText);

        //Executing sendmail to send email
        sm.execute();
    }

    private void validarCampos() {
        camposVacios = false;
        validarCampo(inputLayoutNombre, textNombre);
        validarCampo(inputLayoutCorreo, textCorreo);
        validarCampo(inputLayoutMensaje, textMensaje);
        if (!camposVacios) {
            enviarMensaje();
        }
    }

    private void validarCampo(TextInputLayout textInput, EditText editText) {
        String obligatorio = TextUtils.isEmpty(editText.getText()) ?
                getResources().getString(R.string.campo_obligatorio) : null;
        toggleTextInputLayoutError(textInput, obligatorio);
    }

    private void toggleTextInputLayoutError(TextInputLayout textInputLayout, String msg) {
        textInputLayout.setError(msg);
        textInputLayout.setErrorEnabled((msg == null) ? false : true);
        if (msg != null) {
            camposVacios = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enviarComentario:
                validarCampos();
                break;
        }
    }
}
