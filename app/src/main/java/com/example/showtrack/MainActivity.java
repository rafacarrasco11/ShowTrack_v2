package com.example.showtrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.ActivityLoginBinding;
import com.example.showtrack.databinding.ActivityMainBinding;

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