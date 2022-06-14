package com.example.showtrack.ui.prf.aboutus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * Clase para el fragmento About Us.
 *
 * Esta interfaz se crea con la libreria: https://github.com/medyo/android-about-page
 *
 * Esta genera una interfaz mdoerna con enlaces personalizados.
 */
public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0");

        return new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.ic_profile_rafa)
                .addItem(versionElement)
                .setDescription(getString(R.string.description_AboutUs))
                .addGroup(getString(R.string.title_connectwithus))
                .addEmail(getString(R.string.email_AboutUs))
                .addWebsite(getString(R.string.linkedin_AboutUs))
                .addTwitter(getString(R.string.twitter_AboutUs))
                .addYoutube(getString(R.string.yt_AboutUs))
                .addGitHub(getString(R.string.github_AboutUs))
                .addInstagram(getString(R.string.ig_AboutUs))
                .create();

    }
}