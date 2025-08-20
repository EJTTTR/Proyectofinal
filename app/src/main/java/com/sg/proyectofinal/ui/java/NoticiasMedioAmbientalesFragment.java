package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sg.proyectofinal.R;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticiasMedioAmbientalesFragment extends Fragment {

    // Vistas de la noticia
    private TextView tvHeadline, tvSource, tvSummary;
    private ImageView ivImage;

    // Vistas para manejar los estados
    private ProgressBar progressBar;
    private ScrollView contentScrollView;
    private View errorLayout;
    private Button retryButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_noticias_medio_ambientales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar todas las vistas
        tvHeadline = view.findViewById(R.id.tv_news_headline_2);
        tvSource = view.findViewById(R.id.tv_news_source_2);
        tvSummary = view.findViewById(R.id.tv_news_summary_2);
        ivImage = view.findViewById(R.id.iv_news_image_2);

        progressBar = view.findViewById(R.id.progress_bar);
        contentScrollView = view.findViewById(R.id.content_scroll_view);
        errorLayout = view.findViewById(R.id.error_layout);
        retryButton = view.findViewById(R.id.retry_button);

        // Configurar el botón de reintentar
        retryButton.setOnClickListener(v -> fetchNewsData());

        // Iniciar la primera carga de datos
        fetchNewsData();
    }

    private void fetchNewsData() {
        // 1. Mostrar el loader y ocultar el contenido/error
        showLoadingState(true);

        String url = "https://adamix.net/medioambiente/noticias";
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        if (response.length() > 0) {
                            JSONObject newsObject = response.getJSONObject(0);

                            String title = newsObject.getString("titulo");
                            String summary = newsObject.getString("resumen");
                            String date = newsObject.getString("fecha");

                            // Popular los textos
                            tvHeadline.setText(title);
                            tvSummary.setText(summary);
                            tvSource.setText("Fuente: API - " + date.substring(0, 10));

                            // 2. ÉXITO: Cargar la imagen local AHORA y mostrar el contenido
                            ivImage.setImageResource(R.drawable.noticia_reforestacion);
                            showLoadingState(false); // Oculta el loader y muestra el contenido

                        } else {
                            // La respuesta está vacía, tratar como error
                            showErrorState();
                        }
                    } catch (JSONException e) {
                        Log.e("NoticiasFragment", "JSON parsing error: " + e.getMessage());
                        showErrorState();
                    }
                },
                error -> {
                    Log.e("NoticiasFragment", "Volley error: " + error.toString());
                    // 3. ERROR: Mostrar la vista de error
                    showErrorState();
                }
        );

        queue.add(jsonArrayRequest);
    }

    private void showLoadingState(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            contentScrollView.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            contentScrollView.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
        }
    }

    private void showErrorState() {
        progressBar.setVisibility(View.GONE);
        contentScrollView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
    }
}