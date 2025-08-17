package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sg.proyectofinal.R;

public class VideosEducativosFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_videos_educativos, container, false);

        // Configurar video de Reciclaje
        YouTubePlayerView videoReciclaje = root.findViewById(R.id.videoReciclaje);
        getLifecycle().addObserver(videoReciclaje);
        videoReciclaje.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "G3Vlm8abEfc";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // Configurar video de Conservación
        YouTubePlayerView videoConservacion = root.findViewById(R.id.videoConservacion);
        getLifecycle().addObserver(videoConservacion);
        videoConservacion.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "V2TD8DBtVxM";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // Configurar video de Cambio Climático
        YouTubePlayerView videoCambioClimatico = root.findViewById(R.id.videoCambioClimatico);
        getLifecycle().addObserver(videoCambioClimatico);
        videoCambioClimatico.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "bQTTphy2c-M";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // Configurar video de Biodiversidad
        YouTubePlayerView videoBiodiversidad = root.findViewById(R.id.videoBiodiversidad);
        getLifecycle().addObserver(videoBiodiversidad);
        videoBiodiversidad.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "VEx9KVgiqn0";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Limpieza opcional de recursos
    }
}