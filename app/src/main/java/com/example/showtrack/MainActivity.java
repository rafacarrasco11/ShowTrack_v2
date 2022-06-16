package com.example.showtrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.databinding.ActivityLoginBinding;
import com.example.showtrack.databinding.ActivityMainBinding;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.Locale;

/**
 * Clase main de la aplicacion donde se cargan todos los fragmentos
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this, R.id.navHostFragment);

        appBarConfiguration = new AppBarConfiguration.Builder().build();

        NavigationUI.setupWithNavController(binding.toolbar, navController);

        FilmRepository.getInstance();
        SerieRepository.getInstance();

       if (PreferenceManager.getDefaultSharedPreferences(this).getString("lang", "0" ).equals("es"))
           setLocale("es");
       else if (PreferenceManager.getDefaultSharedPreferences(this).getString("lang", "0" ).equals("en"))
           setLocale("en");

    }
    public void setLocale(String lang) {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(lang);
        resources.updateConfiguration(configuration, displayMetrics);
        onConfigurationChanged(configuration);

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ShowTrackApplication.getContext()).edit();
        editor.putString(User.TAG, lang);
        editor.apply();
    }


    /**
     * Cuando se pulsa sobre el icono de la felcha debe ser el componente NAVIGATIONUI quien
     * gestione la navegacion hacia arriba
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }


}