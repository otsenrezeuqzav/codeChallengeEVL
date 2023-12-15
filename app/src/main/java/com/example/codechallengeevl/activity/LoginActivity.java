package com.example.codechallengeevl.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.codechallengeevl.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilCorreo;
    private TextInputEditText etCorreo;
    private TextInputLayout tilContrasena;
    private TextInputEditText etContrasena;
    private CheckBox cbGuardar;
    private Button btAcceder;

    // Se asume la funcionalidad de inicio de sesión al utilizar de forma predeterminada un correo y contraseña correctas
    String correoOk = "correo@gmail.com";
    String contrasenaOk = "123456";

    // Usamos shared preferences para guardar el correo
    // para que la próxima vez los usuarios no necesiten ingresarlo nuevamente si así se desea
    SharedPreferences sharedPreferences;
    public void login (View view) {
        String correo = etCorreo.getText().toString();
        String contrasena = etContrasena.getText().toString();
        // comprobamos si el inicio de sesión es exitoso
        if ( correo.equals(correoOk) && contrasena.equals(contrasenaOk) ) {
            Toast.makeText(this,LoginActivity.this.getResources().getString(R.string.accesoOk),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), BeersActivity.class);
            startActivity(intent);
            // verificamos si la casilla está marcada, almacenamos el correo y la casilla de estado
            // no queremos guardarlo, ¿qué debemos hacer?
            // simplemente lo eliminamos
            if (cbGuardar.isChecked()) {
                sharedPreferences.edit().putString(LoginActivity.this.getResources().getString(R.string.putStrCorreo),correo).commit();
                sharedPreferences.edit().putBoolean(LoginActivity.this.getResources().getString(R.string.putStrGuardar),cbGuardar.isChecked()).commit();
            } else {
                sharedPreferences.edit().remove(LoginActivity.this.getResources().getString(R.string.putStrCorreo)).commit();
                sharedPreferences.edit().remove(LoginActivity.this.getResources().getString(R.string.putStrGuardar)).commit();
            }
        } else {
            Toast.makeText(this,LoginActivity.this.getResources().getString(R.string.accesoNoOk),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // finaliza la actividad
        if (getIntent().getBooleanExtra(LoginActivity.this.getResources().getString(R.string.exit), false)) {
            finish();
        }


        // se oculta el actionbar
        getSupportActionBar().hide();

        // se crear un callback para manejar la pulsación del botón Atrás
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // se personaliza el comportamiento cuando se presiona el botón Atrás
                //finish();
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(LoginActivity.this.getResources().getString(R.string.alertDlgTitulo))
                        .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();

            }
        };

        // se agrega el callback a OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);

        configView();
    }

    private void configView(){

        // instancias de los objetos de la vista
        tilCorreo = (TextInputLayout) findViewById(R.id.correoTIL);
        etCorreo = (TextInputEditText) findViewById(R.id.correoTIET);
        tilContrasena = (TextInputLayout) findViewById(R.id.contrasenaTIL);
        etContrasena = (TextInputEditText) findViewById(R.id.contrasenaTIET);
        cbGuardar = (CheckBox) findViewById(R.id.cbGuardar);
        btAcceder = findViewById(R.id.buttonLogin);

        // acceso al archivo de shared preferences identificado por el string Acceso
        // de una forma privada para que solo el app pueda acceder al mismo
        sharedPreferences = getApplication().getSharedPreferences(LoginActivity.this.getResources().getString(R.string.shpAcceso),MODE_PRIVATE);

        // obtenemos datos de las shared preferences
        // aquí la clave que tenemos que ingresar es la misma que guardamos
        // proporcionar a defValue cualquier parámetro porque si no se guarda en las shared preferences
        etCorreo.setText(sharedPreferences.getString(LoginActivity.this.getResources().getString(R.string.putStrCorreo),""));
        cbGuardar.setChecked(sharedPreferences.getBoolean(LoginActivity.this.getResources().getString(R.string.putStrGuardar),false));
    }
}