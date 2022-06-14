package com.example.showtrack.ui.prf.profile.settings;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.databinding.FragmentSettingsBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.login.LoginActivity;
import com.example.showtrack.ui.prev.splash.SplashActivity;
import com.example.showtrack.utils.CommonUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Clase para el fragmento de Ajustes.
 *
 * Desde aqui podremos:
 *          - Cambiar la imagen de perfil
 *          - Cambiar el nombre de usuario
 *          - Cambiar nuestra contraseña
 *          - Cambiar el idioma de la aplicacion
 *          - Eliminar tu cuenta
 *          - Cerrar sesion
 *
 *
 *  Utiliza el MVP donde se controlan los errores y el resultado de las operaciones.
 */

public class SettingsFragment extends Fragment implements SettingsFragmentContract.View {

    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    private FragmentSettingsBinding binding;
    private SettingsFragmentPresenter presenter;
    private Spinner languages;

    private static Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SettingsFragmentPresenter(this);
        ShowTrackApplication.setContext(getContext());
        fragment = this.getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        languages = (Spinner) binding.getRoot().findViewById(R.id.spinnerLanguageSettingsFragment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.languages, R.layout.language_spinner_item);
        languages.setAdapter(adapter);
        languages.setSelection(0);

        binding.btnChangeNameSettingsFragment.setOnClickListener(v -> {
            presenter.changeName(binding.editTextNameSettingsFragment.getText().toString());
        });

        binding.btnChangePasswordSettingsFragment.setOnClickListener(v -> {
            presenter.changePassword(binding.editTextPasswordSettingsFragment.getText().toString(), binding.editTextActualPasswordSettingsFragment.getText().toString());
        });

        binding.btnLogOutProfileFragment.setOnClickListener(v -> {
            presenter.LogOut();
        });

        binding.btnDeleteAccountProfileFragment.setOnClickListener(v -> {
            presenter.DeleteAccount();
        });

        binding.imgAvatarSettingsFragment.setOnClickListener(v -> {
            pickImage();
        });

        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1)
                    setLocale("es");
                else if (position == 2)
                    setLocale("en");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            if (UserRepository.getInstance().getProfilePhoto() != null)
                binding.imgAvatarSettingsFragment.setImageBitmap(CommonUtils.StringToBitMap(UserRepository.getInstance().getProfilePhoto()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        presenter.LogOut();
    }

    @SuppressWarnings("deprecation")
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = ShowTrackApplication.context().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ShowTrackApplication.getUserTemp().setProfilePhoto(selectedImage);
                ShowTrackApplication.getUserTemp().setProfilePhotoRoom(CommonUtils.BitMapToString(selectedImage));
                UserRepository.getInstance().setProfilePhoto();
                binding.imgAvatarSettingsFragment.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), ShowTrackApplication.getContext().getString(R.string.erro_profilePhoto), Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getActivity(), ShowTrackApplication.getContext().getString(R.string.erro_profilePhotoNoPicked), Toast.LENGTH_LONG).show();
        }
    }



    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        @SuppressWarnings("deprecation")
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        Drawable d = (Drawable) bd;
        return d;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setNameEmptyError() {
        binding.editTextNameSettingsFragment.setError(getString(R.string.settings_NameEmpty));
    }

    @Override
    public void setNameError() {
        binding.editTextNameSettingsFragment.setError(getString(R.string.settings_NameError));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.editTextPasswordSettingsFragment.setError(getString(R.string.settings_NameEmpty));

    }

    @Override
    public void setPasswordError() {
        binding.editTextPasswordSettingsFragment.setError(getString(R.string.settings_PasswError));

    }

    @Override
    public void setActualPasswordEmptyError() {
        binding.editTextPasswordSettingsFragment.setError(getString(R.string.settings_NameEmpty));
    }

    @Override
    public void onSuccessChangeName(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessChangePassword(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureChangePassword(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessChangeLang(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessLogOut() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void onSuccessDeleteAccount() {
        startActivity(new Intent(getActivity(), SplashActivity.class));
    }




}