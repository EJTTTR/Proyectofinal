package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sg.proyectofinal.R;
import com.sg.proyectofinal.adapters.AreasProtegidasAdapter;
import com.sg.proyectofinal.models.AreaProtegidaList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AreasProtegidasFragment extends Fragment {

    private RecyclerView recyclerView;
    private AreasProtegidasAdapter adapter;
    private List<AreaProtegidaList> areasList;

    // Vistas para manejar los estados
    private ProgressBar progressBar;
    private View contentLayout;
    private View errorLayout;
    private Button retryButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_areas_protegidas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar todas las vistas
        recyclerView = view.findViewById(R.id.recycler_view_areas);
        progressBar = view.findViewById(R.id.progress_bar_areas);
        contentLayout = view.findViewById(R.id.content_layout_areas);
        errorLayout = view.findViewById(R.id.error_layout_areas);
        retryButton = view.findViewById(R.id.retry_button_areas);

        // Configurar RecyclerView
        areasList = new ArrayList<>();
        adapter = new AreasProtegidasAdapter(areasList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Configurar el botón de reintentar
        retryButton.setOnClickListener(v -> fetchAreasProtegidas());

        // Iniciar la primera carga de datos
        fetchAreasProtegidas();
    }

    private void fetchAreasProtegidas() {
        // 1. Mostrar estado de carga
        showState(State.LOADING);

        String url = "https://adamix.net/medioambiente/areas_protegidas";
        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        // Limpiar la lista antes de añadir nuevos datos (importante para reintentos)
                        areasList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject areaObject = response.getJSONObject(i);
                            String nombre = areaObject.getString("nombre");
                            String descripcion = areaObject.getString("descripcion");
                            String ubicacion = areaObject.getString("ubicacion");

                            AreaProtegidaList area = new AreaProtegidaList(nombre, descripcion, ubicacion);
                            areasList.add(area);
                        }
                        adapter.notifyDataSetChanged();
                        // 2. Mostrar estado de éxito
                        showState(State.SUCCESS);
                    } catch (JSONException e) {
                        Log.e("AreasFragment", "JSON parsing error: " + e.getMessage());
                        // 3. Mostrar estado de error si el JSON falla
                        showState(State.ERROR);
                    }
                },
                error -> {
                    Log.e("AreasFragment", "Volley error: " + error.toString());
                    // 3. Mostrar estado de error si la conexión falla
                    showState(State.ERROR);
                }
        );

        queue.add(jsonArrayRequest);
    }

    // Enum para manejar los estados de la UI de forma clara
    private enum State {
        LOADING,
        SUCCESS,
        ERROR
    }

    // Método centralizado para cambiar la visibilidad de las vistas
    private void showState(State state) {
        switch (state) {
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                contentLayout.setVisibility(View.GONE);
                errorLayout.setVisibility(View.GONE);
                break;
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                contentLayout.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                break;
            case ERROR:
                progressBar.setVisibility(View.GONE);
                contentLayout.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}