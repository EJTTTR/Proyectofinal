package com.sg.proyectofinal.ui.java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.databinding.FragmentSobreNosotrosBinding;

public class SobreNosotrosFragment extends Fragment {
    private FragmentSobreNosotrosBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSobreNosotrosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.videoContainer.setOnClickListener(v -> {
            openYouTubeVideo();
        });
    }

    private void openYouTubeVideo() {
        String videoUrl = getString(R.string.ministry_video_url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}