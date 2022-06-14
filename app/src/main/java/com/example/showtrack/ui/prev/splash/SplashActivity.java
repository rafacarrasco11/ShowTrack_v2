package com.example.showtrack.ui.prev.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.showtrack.MainActivity;
import com.example.showtrack.R;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.login.LoginActivity;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

/**
 * Clase para el fragmento Splash de la aplicacion.
 *
 * Aqui se cargan algunas opciones antes de entrar a la app. Ademas se simula un tiempo de espera con un Handler
 */
public class SplashActivity extends AppCompatActivity {

    private static final long WAIT_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    /**
     * Metodo simulacion carga de la aplicacion. Ejecuta un metodo que va al login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> startLogin(), WAIT_TIME);

    }

    private void startLogin(){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}