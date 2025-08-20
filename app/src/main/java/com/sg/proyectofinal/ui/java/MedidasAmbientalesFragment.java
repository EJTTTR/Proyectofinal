package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sg.proyectofinal.databinding.FragmentMedidasAmbientalesBinding;
import org.json.JSONException;
import org.json.JSONObject;

public class MedidasAmbientalesFragment extends Fragment {

    // ViewBinding para acceder a las vistas de forma segura
    private FragmentMedidasAmbientalesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedidasAmbientalesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar el botón de reintentar
        binding.retryButton.setOnClickListener(v -> fetchMedidaData());

        // Iniciar la carga de datos desde la API
        fetchMedidaData();
    }

    private void fetchMedidaData() {
        // 1. Mostrar estado de carga
        showState(State.LOADING);

        String url = "https://adamix.net/medioambiente/medidas";
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        // Tomamos solo el primer objeto del array de la API
                        if (response.length() > 0) {
                            JSONObject medidaObject = response.getJSONObject(0);

                            String titulo = medidaObject.getString("titulo");
                            String descripcion = medidaObject.getString("descripcion");

                            // Usamos ViewBinding para popular la tarjeta
                            binding.titleMedida.setText(titulo);
                            binding.detailsMedida.setText(descripcion);

                            // Configuramos la funcionalidad de expandir/colapsar para la tarjeta
                            setupExpandableCard(binding.headerMedida, binding.detailsMedida, binding.iconExpand);

                            // 2. Mostrar estado de éxito
                            showState(State.SUCCESS);
                        } else {
                            // Si no hay datos, mostramos error
                            showState(State.ERROR);
                        }
                    } catch (JSONException e) {
                        Log.e("MedidasFragment", "JSON parsing error: " + e.getMessage());
                        showState(State.ERROR);
                    }
                },
                error -> {
                    Log.e("MedidasFragment", "Volley error: " + error.toString());
                    // 3. Mostrar estado de error en caso de fallo de red
                    showState(State.ERROR);
                }
        );

        queue.add(jsonArrayRequest);
    }

    private void setupExpandableCard(View header, TextView details, ImageView icon) {
        // La lógica de expandir/colapsar que ya tenías
        header.setOnClickListener(v -> {
            if (details.getVisibility() == View.GONE) {
                details.setVisibility(View.VISIBLE);
                icon.setRotation(180);
            } else {
                details.setVisibility(View.GONE);
                icon.setRotation(0);
            }
        });
    }

    // Enum para manejar los estados de la UI
    private enum State {
        LOADING,
        SUCCESS,
        ERROR
    }

    // Método para cambiar la visibilidad de las vistas según el estado
    private void showState(State state) {
        switch (state) {
            case LOADING:
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.contentScrollView.setVisibility(View.GONE);
                binding.errorLayout.setVisibility(View.GONE);
                break;
            case SUCCESS:
                binding.progressBar.setVisibility(View.GONE);
                binding.contentScrollView.setVisibility(View.VISIBLE);
                binding.errorLayout.setVisibility(View.GONE);
                break;
            case ERROR:
                binding.progressBar.setVisibility(View.GONE);
                binding.contentScrollView.setVisibility(View.GONE);
                binding.errorLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Liberar la referencia del binding
    }
}